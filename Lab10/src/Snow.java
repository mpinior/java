import java.awt.*;
import java.awt.geom.AffineTransform;

public class Snow implements XmasShape {
    //położenie
    int x1;
    int y1;
    double rozmiar;
    Color lineColor = new Color(255,255,255);

    Snow(int x1, int y1, double rozmiar){
        this.x1 = x1;
        this.y1 = y1;
        this.rozmiar = rozmiar;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x1,y1);
        g2d.scale(rozmiar,rozmiar);

    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform mat = g2d.getTransform();
        g2d.setColor(lineColor);
        for(int i=0;i<12;i++){
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        g2d.setTransform(mat);
    }
}

