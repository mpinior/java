import java.awt.*;

//Nie rozumiem znaczenia tej klasy w zasadzie
public class Light implements XmasShape {
    //wygląd
    int x[]={505,40,900};
    int y[]={-30,650,650};
    //położenie
    int x1;
    int y1;
    double rozmiar;
    Color lineColor = new Color(201,192,6,60);
    Color fillColor = new Color(235,224,21, 40);

    Light(int x1, int y1, double rozmiar){
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
        g2d.setColor(fillColor);
        g2d.fillPolygon(this.x,this.y,this.x.length);
        g2d.setColor(lineColor);
        g2d.drawPolygon(this.x,this.y,this.x.length);
    }

}
