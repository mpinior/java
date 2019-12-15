import java.awt.*;

public class Star implements XmasShape {
    //wygląd
    int x[]={50,65,100,65,50,35,0,35};
    int y[]={0,35,50,65,100,65,50,35};
    double scale;
    //położenie
    int x1;
    int y1;
    int rozmiar;
    Color lineColor;
    Color fillColor;

    Star(int x1, int y1, int rozmiar, double scale, Color tlo, Color linia){
        this.x1 = x1;
        this.y1 = y1;
        this.rozmiar = rozmiar;
        this.scale = scale;
        this.lineColor = linia;
        this.fillColor = tlo;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x1,y1);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillPolygon(this.x,this.y,this.x.length);
        g2d.setColor(lineColor);
        g2d.drawPolygon(this.x,this.y,this.x.length);
    }
}
