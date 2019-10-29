public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        int max = 0;
        int j = 0;
        for (int i = 0; i < d.length; i++) {
            for (j = 0; j < d[i].length; j++) {

            }
            if (j > max) {
                max = j;
            }
        }
        cols = max;
        rows = d.length;
        data = new double[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                if (k >= d[i].length) {
                    data[i * cols + k] = 0;
                } else {
                    data[i * cols + k] = d[i][k];
                }
            }
        }
    }

    double[][] asArray() {
        double[][] result = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = this.data[i*this.cols + j];
            }
        }
        return result;
    }
    double get(int r,int c){
        return this.data[r*this.cols + c];
    }
    void set (int r,int c, double value){
        this.data[r*this.cols + c] = value;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i=0;i<this.rows;i++){
            builder.append("[");
            for (int j=0; j<this.cols; j++){
                if (j==this.cols-1){
                    builder.append(this.data[i*this.cols + j]);
                    continue;
                }
                builder.append(this.data[i*this.cols + j]);
                builder.append(",");
            }
            builder.append("]");
            if(i==this.rows-1){
                continue;
            }
            builder.append(",");
        }
        builder.append("]");
        return builder.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        }
        else{
            this.rows = newRows;
            this.cols = newCols;
        }
    }

    int[] shape(){
        int[] ret = new int[2];
        ret[0] = this.rows;
        ret[1] = this.cols;
        return ret;
    }

    Matrix add(Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if (this.rows != m.rows && this.cols != m.cols){
            throw new RuntimeException(String.format("Nie mozna dodac macierzy o roznych wymiarach"));
        }
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*this.cols + j] = this.get(i,j) + m.get(i,j);
            }
        }
        return result;
    }

    Matrix sub(Matrix m){
        Matrix result = new Matrix(this.rows, this.cols);
        if (this.rows != m.rows && this.cols != m.cols){
            throw new RuntimeException(String.format("Nie mozna odjac macierzy o roznych wymiarach"));
        }
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*this.cols + j] = this.get(i,j) - m.get(i,j);
            }
        }
        return result;
    }

    Matrix mul(Matrix m){
        if (this.rows != m.rows && this.cols != m.cols){
            throw new RuntimeException(String.format("Nie mozna mnozyc macierzy o roznych wymiarach"));
        }
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*this.cols + j] = this.get(i,j) * m.get(i,j);
            }
        }
        return result;
    }

    Matrix div(Matrix m){
        if (this.rows != m.rows && this.cols != m.cols){
            throw new RuntimeException(String.format("Nie mozna dzielic macierzy o roznych wymiarach"));
        }
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                if(m.get(i,j) == 0){
                    throw new RuntimeException(String.format("Nie mozna dzielic przez zero!"));
                }
                result.data[i*this.cols + j] = this.get(i,j) / m.get(i,j);
            }
        }
        return result;
    }

    Matrix add(double w){
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*cols + j] = this.data[i*cols + j] + w;
            }
        }
        return result;
    }

    Matrix sub(double w){
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*cols + j] = this.data[i*cols + j] - w;
            }
        }
        return result;
    }

    Matrix mul(double w){
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                result.data[i*cols + j] = this.data[i*cols + j] * w;
            }
        }
        return result;
    }

    Matrix div(double w){
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                if(w == 0){
                    throw new RuntimeException(String.format("Nie mozna dzielic przez zero!"));
                }
                result.data[i*cols + j] = this.data[i*cols + j] / w;
            }
        }
        return result;
    }

    Matrix dot(Matrix m){
        Matrix result = new Matrix(this.cols, m.rows);
        if(this.cols != m.rows){
            throw new RuntimeException(String.format("Nie mozna mnozyc takiej macierzy"));
        }
        else{
            double suma=0;
            for (int i=0; i<m.rows; i++){
                for (int k=0; k<this.cols; k++) {
                    for (int j = 0; j < this.cols; j++) {
                        suma += this.get(i, j) * m.get(j,k);
                    }
                    result.data[i * cols + k] = suma;
                    suma = 0;
                }
            }
        }
        return result;
    }

    double frobenius(){
        double suma=0;
        double potega=0;
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.cols; j++){
                potega += this.get(i,j)*this.get(i,j);
            }
            suma+=potega;
        }
        double result = Math.sqrt(suma);
        return result;
    }

}