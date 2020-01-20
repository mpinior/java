import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i=start; i<end; i++){
                mean+=array[i];
            }
            mean=mean/(end-start);

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    static void parallelMean(int cnt){
        MeanCalc threads[]=new MeanCalc[cnt];
        for (int i=0; i<cnt; i++){
            threads[i] = new MeanCalc(i* (array.length/cnt), (i+1)*(array.length/cnt));
        }

        double t1 = System.nanoTime()/1e6;
        for (MeanCalc mc: threads){
            mc.start();
        }

        double t2 = System.nanoTime()/1e6;
        for(MeanCalc mc:threads) {
            try{
                mc.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }

        double t3 = System.nanoTime()/1e6;
        double mean=0;
        for(MeanCalc mc: threads){
            mean+=mc.mean;
        }
        mean=mean/threads.length;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    static void parallelMeanV2(int cnt) throws InterruptedException {
        double sum = 0;
        MeanCalc threads[] = new MeanCalc[cnt];

        int threadArraySize = array.length / cnt;
        results = new ArrayBlockingQueue<>(cnt);

        for (int i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(i * threadArraySize, (i + 1) * threadArraySize);
        }

        double t1 = System.nanoTime() / 1e6;
        Arrays.stream(threads).forEach(Thread::start);

        double t2 = System.nanoTime() / 1e6;
        for (int i = 0; i < cnt; i++) {
            sum += results.take();
        }

        double t3 = System.nanoTime() / 1e6;
        System.out.printf(
                Locale.US,
                "Parallel v2: size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                sum / cnt);
    }

    public static void main(String[] args) {
        initArray(100000000);
        parallelMean(50);
        try{
            parallelMeanV2(50);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
