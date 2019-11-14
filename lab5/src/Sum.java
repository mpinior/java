import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for (int i=0; i<getArgumentsCount(); i++){
            result += this.args.get(i).evaluate();
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");
        for(int i=0; i<getArgumentsCount()-1; i++){
           b.append(this.args.get(i).toString());
           b.append("+");
        }
        b.append(this.args.get(getArgumentsCount()-1).toString());
        if(sign<0)b.append(")");
        return b.toString();
    }


    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            if(!n.diff(var).isZero()){
                r.add(n.diff(var));
            }
        }
        return r;
    }
    boolean isZero(){
        for (int i=0; i<getArgumentsCount(); i++){
            if (!this.args.get(i).isZero()){
                return false;
            }
        }
        return true;
    }
}