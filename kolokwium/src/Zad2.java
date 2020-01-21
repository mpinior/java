import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Zad2 {
    static double[] array;

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= (Math.random() * (1e3 + 1e3 + 1)) -1e3;
        }
    }
    static double[] largest()
    {
        double max1 = array[0];
        double max2 = 0;
        double max3 = 0;
        double[] wynik = new double[3];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = array[i];
            }
        }
        wynik[0] = max1;
        wynik[1] = max2;
        wynik[3] = max3;

        return wynik;
    }

    static class Watek extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        Watek(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            largest();
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }


    }

    static void parallelMean(int cnt){
        Watek threads[]=new Watek[cnt];
        for (int i=0; i<cnt; i++){
            threads[i] = new Watek(i* (array.length/cnt), (i+1)*(array.length/cnt));
        }

        double t1 = System.nanoTime()/1e6;
        for (Watek mc: threads){
            mc.start();
        }
        double t2 = System.nanoTime()/1e6;
        for(Watek mc:threads) {
            try{
                mc.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        double t3 = System.nanoTime()/1e6;
        double mean=0;
        for(Watek mc: threads){
            largest();
        }
        mean=mean/threads.length;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    public static void main(String[] args) {
        initArray(100000000);
        parallelMean(4);
    }
}

