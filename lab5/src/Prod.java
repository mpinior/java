import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        this.args.add(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }


    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        for (int i=0; i<getArgumentsCount(); i++){
            result *= this.args.get(i).evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        for (int i=0; i<getArgumentsCount()-1; i++){
            b.append(this.args.get(i).toString());
            b.append("*");
        }
        b.append(this.args.get(getArgumentsCount()-1).toString());
        return b.toString();
    }


    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            if (!m.isZero()){
                r.add(m);
            }
        }
        return r;
    }
    @Override
    boolean isZero() {
        for (Node n : args) {
            if(n.isZero()){
                return true;
            }
        }
        return false;
    }

    Prod simplify(){
        Constant result = new Constant(1);
        for (Node n : args) {
            if (n instanceof Constant){
                result.value = result.value*((Constant) n).value;
            }
        }
        Prod answer = new Prod();
        answer.mul(result);
        for (Node n : args) {
            if (!(n instanceof Constant)){
                answer.mul(n);
            }
        }
        return answer;
    }
}