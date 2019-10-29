import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;


public class MatrixTest {

    @org.junit.Test
    public void MatrixRC(){
        Random rand = new Random();
        int n = rand.nextInt(10);
        int k = rand.nextInt(10);
        Matrix test = new Matrix(n,k);
        assertEquals(k, test.cols);
        assertEquals(n, test.rows);
    }

    @org.junit.Test
    public void MatrixDouble(){
        Matrix test = new Matrix(new double[][] {{1,2}, {1,2}, {1,2,3}});
        assertEquals(3, test.cols);
        assertEquals(3, test.rows);
        double a = test.data[2];
        double b = test.data[5];
        assertEquals(0, a, 0.001);
        assertEquals(0, b, 0.001);
    }

    @org.junit.Test
    public void asArray() {
        Matrix test = new Matrix(new double[][] {{1,2}, {1,2}, {1,2,3}});
        assertEquals(test.rows, test.cols, 0.001);
    }

    @org.junit.Test
    public void get() {
        Matrix test = new Matrix(5,5);
        test.set(2,2,15);
        double a = test.get(2,2);
        assertEquals(15, a,0.001);
    }

    @org.junit.Test
    public void get2() {
        Random rand = new Random();
        Matrix test = new Matrix(5,5);
        for (int i=0; i<test.rows; i++){
            for (int j=0; j<test.cols; j++){
                int n = rand.nextInt(10);
                test.set(i,j, n);
                double result = test.get(i,j);
                assertEquals(n, result,0.001);
            }
        }
    }

    @org.junit.Test
    public void set() {
        Random rand = new Random();
        Matrix test = new Matrix(5,5);
        for (int i=0; i<test.rows; i++){
            for (int j=0; j<test.cols; j++){
                int n = rand.nextInt(10);
                test.set(i,j, n);
                double result = test.get(i,j);
                assertEquals(result, n,0.001);
            }
        }

    }

    @org.junit.Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.Test
    public void reshape() {
        Matrix test = new Matrix(2,2);
        test.reshape(1,4);
        try{
            test.reshape(1,3);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void shape() {
        Matrix test = new Matrix(2,2);
        int[] wynik = test.shape();
        int a = wynik[0];
        int b = wynik[1];
        assertEquals(2, a, 0.001);
        assertEquals(2, b, 0.001);
    }

    // Chciałam stworzyć @Before i w niej dwie macierze, żeby nie musieć powtarzać tego samego kodu w kółko, ale @Before byłoby wywoływane za każdym włączeniem
    // jakiejkolwiek metody, a nie we wszystkich używam Matrix, więc niepotrzebnie zużyłabym pamięć.
    @org.junit.Test
    public void add() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        Matrix test2 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
        Matrix result = test1.add(test2);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j)+ test2.get(i,j));
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
        try{
            Matrix test3 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3,4}});
            Matrix test4 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
            Matrix result2 = test3.add(test4);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void sub() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        Matrix test2 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
        Matrix result = test1.sub(test2);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) - test2.get(i,j));
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
        try{
            Matrix test3 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3,4}});
            Matrix test4 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
            Matrix result2 = test3.sub(test4);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void mul() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        Matrix test2 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
        Matrix result = test1.mul(test2);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) * test2.get(i,j));
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
        try{
            Matrix test3= new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3,4}});
            Matrix test4 = new Matrix(new double[][] {{0,0}, {1,2,3}, {1,2}});
            Matrix result2 = test3.mul(test4);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void div() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        Matrix test2 = new Matrix(new double[][] {{1,1,1}, {1,2,3}, {1,2,1}});
        Matrix result = test1.div(test2);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) / test2.get(i,j));
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
        try{
            Matrix test3 = new Matrix(new double[][] {{0,1,0}, {1,2}, {1,2,3,4}});
            Matrix test4 = new Matrix(new double[][] {{0,0}, {1,0,3}, {1,2}});
            Matrix result2 = test3.div(test4);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void testAdd() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        double w = 2;
        Matrix result = test1.add(w);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) + w);
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
    }

    @org.junit.Test
    public void testSub() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        double w = 2;
        Matrix result = test1.sub(w);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) - w);
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
    }

    @org.junit.Test
    public void testMul() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        double w = 2;
        Matrix result = test1.mul(w);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) * w);
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
    }

    @org.junit.Test
    public void testDiv() {
        Matrix test1 = new Matrix(new double[][] {{0,0,0}, {1,2}, {1,2,3}});
        double w = 2;
        Matrix result = test1.div(w);
        Matrix wynik = new Matrix(3,3);
        for (int i=0; i<wynik.rows; i++){
            for (int j=0; j<wynik.cols; j++){
                wynik.set(i,j, test1.get(i,j) / w);
                assertEquals(wynik.get(i,j), result.get(i,j), 0.000);
            }
        }
        try{
            double d =0;
            Matrix result2 = test1.div(d);
        }
        catch(Exception e) {
            return;
        }
        fail();
    }

    @org.junit.Test
    public void dot() {
        Matrix test1 = new Matrix(new double[][] {{0,0}, {1,1}});
        Matrix test2 = new Matrix(new double[][] {{0,0}, {1,0}});
        Matrix result = test1.dot(test2);
        assertEquals("[[0.0,0.0],[1.0,0.0]]", result.toString().replaceAll(" ", ""));
        try{
            Matrix test3 = new Matrix(new double[][] {{0,0}, {1,2}, {1,2}});
            Matrix test4 = new Matrix(new double[][] {{0,0}, {1,0}, {1,2}});
            Matrix result2 = test3.dot(test4);
        }
        catch(Exception e) {
            return;
        }
        fail();

    }

    @org.junit.Test
    public void frobenius() {
        Matrix test1 = new Matrix(new double[][] {{0,0}, {1,1}});
        double result = test1.frobenius();
        assertEquals(1.41, result, 0.01);

    }
}