import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    //najpierw dodać choinkę od dołu do góry(done), następnie bombki(done) i gwiazdeczki, a potem napis i może śnieżynki
    DrawPanel() {
        shapes.add(new Triangle(1.5, 1, 100,450));
        shapes.add(new Triangle(1.25, 0.95, 165,400));
        shapes.add(new Triangle(1.05, 0.95, 220,340));
        shapes.add(new Triangle(0.95, 0.85, 245,290));
        shapes.add(new Triangle(0.78, 0.80, 290,240));
        shapes.add(new Triangle(0.60, 0.85, 336,180));
        shapes.add(new Triangle(0.50, 0.75, 362,145));
        shapes.add(new Triangle(0.40, 0.70, 388,100));
        shapes.add(new Bubble(520, 450, 30, 1.0, new Color(229,26,69)));
        shapes.add(new Bubble(700, 575, 30, 1.0, new Color(243,72,22)));
        shapes.add(new Bubble(560, 300, 25, 1.0, new Color(243,72,22)));
        shapes.add(new Bubble(200, 575, 35, 1.0, new Color(229,26,69)));
        shapes.add(new Bubble(520, 180, 25, 1.0, new Color(229,26,69)));
        shapes.add(new Bubble(400, 469, 35, 1.0, new Color(174,26,229)));
        shapes.add(new Bubble(500, 240, 25, 1.0, new Color(174,26,229)));
        shapes.add(new Bubble(450, 360, 28, 1.0, new Color(243,183,22)));
        shapes.add(new Bubble(388, 230, 20, 1.0, new Color(243,183,22)));
        shapes.add(new Bubble(600, 510, 30, 1.0, new Color(33,222,200)));
        shapes.add(new Bubble(320, 380, 23, 1.0, new Color(33,222,200)));
        shapes.add(new Bubble(640, 456, 30, 1.0, new Color(8,63,153)));
        shapes.add(new Bubble(450, 300, 24, 1.0, new Color(8,63,153)));
        shapes.add(new Bubble(480, 560, 35, 1.0, new Color(204,123,2)));
        shapes.add(new Bubble(572, 237, 20, 1.0, new Color(204,123,2)));
        shapes.add(new Bubble(335, 590, 32, 1.0, new Color(135,15,15)));
        shapes.add(new Bubble(430, 180, 22, 1.0, new Color(135,15,15)));
        shapes.add(new Bubble(310, 480, 35, 1.0, new Color(163,69,69)));
        shapes.add(new Bubble(588, 370, 32, 1.0, new Color(162,87,179)));
        shapes.add(new Star(490, 350, 10, 0.35, new Color(255,255,255, 0),new Color(184,162,0)));
        shapes.add(new Star(270, 570, 10, 0.35, new Color(255,255,255, 0),new Color(184,162,0)));
        shapes.add(new Star(650, 500, 10, 0.35, new Color(255,255,255, 0),new Color(184,162,0)));
        shapes.add(new Star(465, 180, 10, 0.35, new Color(255,255,255, 0),new Color(184,162,0)));
        shapes.add(new Star(470, 480, 10, 0.4, new Color(255,255,255, 0),new Color(184,162,0)));
        shapes.add(new Star(370, 300, 10, 0.4, new Color(255,255,255, 0),new Color(156,0,0)));
        shapes.add(new Star(420, 550, 10, 0.4, new Color(255,255,255, 0),new Color(156,0,0)));
        shapes.add(new Star(370, 300, 10, 0.4, new Color(255,255,255, 0),new Color(156,0,0)));
        shapes.add(new Star(600, 330, 10, 0.34, new Color(255,255,255, 0),new Color(156,0,0)));
        shapes.add(new Star(240, 470, 10, 0.34, new Color(255,255,255, 0),new Color(156,0,0)));
        shapes.add(new Star(360, 400, 10, 0.45, new Color(132,14,140, 40),new Color(132,14,138)));
        shapes.add(new Star(620, 560, 10, 0.4, new Color(132,14,140, 40),new Color(132,14,138)));
        shapes.add(new Star(400, 150, 10, 0.3, new Color(132,14,140, 40),new Color(132,14,138)));
        shapes.add(new Star(570, 440, 10, 0.35, new Color(132,14,140, 40),new Color(132,14,138)));
        shapes.add(new Star(455, 72, 10, 0.7, new Color(190,162,0),new Color(184,162,0)));
        shapes.add(new Snow(100,100,0.07));
        shapes.add(new Snow(150,200,0.08));
        shapes.add(new Snow(800,100,0.07));
        shapes.add(new Snow(820,160,0.06));
        shapes.add(new Snow(920,400,0.08));
        shapes.add(new Snow(730,40,0.08));
        shapes.add(new Snow(50,500,0.08));
        shapes.add(new Snow(90,420,0.06));
        shapes.add(new Snow(42,340,0.08));
        shapes.add(new Snow(180,270,0.06));
        shapes.add(new Snow(210,422,0.08));
        shapes.add(new Snow(850,342,0.06));
        shapes.add(new Snow(780,240,0.08));
        shapes.add(new Snow(670,230,0.07));
        shapes.add(new Snow(888,500,0.08));
        shapes.add(new Snow(740,368,0.06));
        shapes.add(new Snow(678,112,0.07));
        shapes.add(new Snow(223,128,0.06));
        shapes.add(new Snow(290,180,0.08));
        shapes.add(new Snow(340,125,0.06));
        shapes.add(new Snow(932,90,0.08));
        shapes.add(new Snow(924,254,0.07));
        shapes.add(new Snow(188,365,0.07));
        shapes.add(new Snow(266,277,0.08));
        shapes.add(new Snow(80,270,0.06));
        shapes.add(new Snow(810,450,0.06));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(108,130,202));
        g.setFont(new Font("KG Give You Glory", Font.BOLD, 50));
        g.drawString("Wesołych Świąt", 50, 80);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }

    }

}
