import java.awt.*;

public class Triangle implements XmasShape {
    int x[]={250,500,435,420,355,344,290,250,210,156,147,82,67,0};
    int y[]={0,100,100,130,120,160,145,190,145,160,120,130,100,100};
    double scalex;
    double scaley;
    int x1;
    int y1;

    Triangle(){}
    Triangle(double scalex, double scaley, int x1, int y1){
        this.scalex = scalex;
        this.scaley = scaley;
        this.x1 = x1;
        this.y1 = y1;

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x1,y1);
        g2d.scale(scalex,scaley);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(13,113,14),0,300, new Color(32,233,12));
        g2d.setPaint(grad);
        g2d.fillPolygon(this.x,this.y,this.x.length);
    }
}
