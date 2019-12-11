import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        shapes.add(new Bubble(100, 100, 2.0, new Color(100,100,50), new Color(100,150,50)));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(200,150,50));
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

}
