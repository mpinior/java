import java.awt.*;

public class Bubble implements XmasShape {
    //położenie koła
    int x;
    int y;
    //rozmiar bombki
    int rozmiar;
    double scale;
    Color lineColor;
    Color fillColor;

    Bubble(){}
    Bubble(int x, int y, int rozmiar, double scale, Color tlo){
        this.rozmiar = rozmiar;
        this.x = x;
        this.y = y;
        this.lineColor = tlo;
        this.fillColor = tlo;
        this.scale = scale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(0,0,rozmiar,rozmiar);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0,rozmiar,rozmiar);
    }


}


