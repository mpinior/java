public class Variable extends Node {
    String name;
    Double value;

    Variable(String name){
        this.name = name;
    }
    void setValue(double d){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }


    @Override
    double evaluate() {
        return sign*value;
    }


    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }

}