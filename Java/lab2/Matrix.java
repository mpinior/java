public class Matrix {
    double[]data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        System.out.println(d.length);
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
    }
}