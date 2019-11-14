import java.net.PortUnreachableException;
import java.util.Locale;
import java.util.Random;

public class main {
    //test1
    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }

    //test2
    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    //test3
    static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

//Pana kod
//        double xv = 100*(Math.random()-.5);
//        double yv = 100*(Math.random()-.5);
//        x.setValue(xv);
//        y.setValue(yv);
//        double fv = circle.evaluate();
//        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));

        int number=1;
        while (number < 101){
            Random rand = new Random();
            double xv1 = 5*(rand.nextDouble())-5;
            double yv1 = 5*(rand.nextDouble())-5;
            x.setValue(xv1);
            y.setValue(yv1);
            double fv1 = circle.evaluate();
            if (fv1<0) {
                System.out.print(number+". ");
                System.out.println(String.format("Punkt (%f,%f) leży wewnątrz koła", xv1, yv1));
                number += 1;
            }
        }
    }


    //Testy pochodnych
    //Test1
    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    //Test2
    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());

    }
    public static void main(String[] args) {
        buildAndPrint();
        buildAndEvaluate();
        defineCircle();
        diffPoly();
        diffCircle();
        //Testowanie simplify
        Prod test = new Prod();
        test.mul(new Variable("x")).mul(new Variable("y")).mul(new Constant(8)).mul(new Constant(16));
        System.out.println(test.toString());
        System.out.println(test.simplify().toString());
    }
}
