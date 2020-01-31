import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {
    AnimationThread thread = new AnimationThread();
    static Random r = new Random();

    static class Ball{
        int x;
        int y;
        int d;
        double radius;
        double vx;
        double vy;
        Color color;

        public Ball() {
            x = r.nextInt(700);
            y = r.nextInt(700);
            d = r.nextInt(10)+20;
            vx = r.nextInt(5)+15;
            vy = r.nextInt(5)+15;
            color = new Color(r.nextInt(255), r.nextInt(255),r.nextInt(255));
            radius = d/2;
        }

        public void draw(Graphics2D g2d){
            AffineTransform saveAT = g2d.getTransform();
            this.render(g2d);
            g2d.transform(saveAT);
        }
        public void render(Graphics2D g2d){
            g2d.setColor(this.color);
            g2d.fillOval(this.x, this.y, this.d, this.d);
        }
        double distance(Ball ball) {
            double centerX1 = this.x+this.radius;
            double centerY1 = this.y+this.radius;
            double centerX2 = ball.x+ball.radius;
            double centerY2 = ball.y+ball.radius;

            return Math.sqrt(Math.pow(centerX1-centerX2,2)+Math.pow(centerY1-centerY2,2));
        }
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{
        boolean paused = true;
        public void run(){
            for(;;){
                synchronized (this){
                    if(paused) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                balls.forEach(ball -> {
                    ball.x += ball.vx;
                    ball.y += ball.vy;
                    if(ball.x + ball.d >= 700 || ball.x <= 0)ball.vx *= -1;
                    if(ball.y + ball.d >= 700 || ball.y <= 0)ball.vy *= -1;
                });
                repaint();
                for (int i = 0; i < balls.size(); i++) {
                    for (int j = i+1; j < balls.size(); j++) {
                        Ball ball1 = balls.get(i);
                        Ball ball2 = balls.get(j);
                        double distance = ball1.distance(ball2);
                        if(distance<=ball1.radius+ball2.radius){
                            double ball1CentreX = ball1.x+ball1.radius;
                            double ball1CentreY = ball1.y+ball1.radius;
                            double ball2CentreX = ball2.x+ball2.radius;
                            double ball2CentreY = ball2.y+ball2.radius;
                            double modifierForBall1 = ((ball1.vx-ball2.vx)*(ball1CentreX-ball2CentreX)+(ball1.vy-ball2.vy)*(ball1CentreY-ball2CentreY))/(Math.pow((ball1CentreX-ball2CentreX),2)+(Math.pow(ball1CentreY-ball2CentreY,2)));
                            double modifierForBall2 = ((ball2.vx-ball1.vx)*(ball2CentreX-ball1CentreX)+(ball2.vy-ball1.vy)*(ball2CentreY-ball1CentreY))/(Math.pow((ball2CentreX-ball1CentreX),2)+(Math.pow(ball2CentreY-ball1CentreY,2)));
                            ball1.vx=ball1.vx-modifierForBall1*(ball1CentreX-ball2CentreX);
                            ball1.vy=ball1.vy-modifierForBall1*(ball1CentreY-ball2CentreY);
                            ball2.vx=ball2.vx-modifierForBall2*(ball2CentreX-ball1CentreX);
                            ball2.vy=ball2.vy-modifierForBall2*(ball2CentreY-ball1CentreY);
                        }
                    }
                }
                try {
                    sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        BufferedImage buffer = new BufferedImage(700,700,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics bufferGraphics = buffer.getGraphics();
        balls.forEach(ball -> ball.draw((Graphics2D) bufferGraphics));
        g.drawImage(buffer,0,0,700,700, this);
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        thread.start();
    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        if(balls.isEmpty()){
            for (int i = 0; i < 10; i++) {
                balls.add(new Ball());
            }
        }
        thread.paused = false;
        synchronized (thread) {
            thread.notify();
        }
    }

    void onStop(){
        System.out.println("Suspend animation thread");
        thread.paused = true;
    }

    void onPlus(){
        System.out.println("Add a ball");
        balls.add(new Ball());
    }

    void onMinus(){
        if(!balls.isEmpty()){
            System.out.println("Remove a ball");
            balls.remove(r.nextInt(balls.size()));
        }
        else System.out.println("Nothing to remove");
    }
}