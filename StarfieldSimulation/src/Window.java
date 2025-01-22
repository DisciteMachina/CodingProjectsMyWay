import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
    JFrame frame;
    Star[] stars = new Star[500];
    Timer timer;

    public Window(int height, int width) {
        frame = new JFrame("Starfield Simulation");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(width, height);
        }

        frame.add(this);
        frame.setVisible(true);

        timer = new Timer(30, e -> updateStars());
        timer.start();
    }

    public void updateStars() {
        for (Star star : stars){
            star.update();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);

        for (Star star : stars) {
            star.draw(g2d);
        }
    }
}
