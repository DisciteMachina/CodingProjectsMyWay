import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JPanel {
    JFrame frame;
    Snake snake;
    Timer timer;
    int direction = 0;

    public Window(int height, int width) {
        frame = new JFrame("Snake Game");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.DARK_GRAY);

        frame.add(this);
        frame.setVisible(true);

        snake = new Snake(0, 0, 3, 0);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Update direction based on arrow keys (or WASD)
                if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                    direction = 1;  // Up
                } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                    direction = 2;  // Right
                } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                    direction = 3;  // Down
                } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                    direction = 4;  // Left
                }
            }
        });

        timer = new Timer(30, e -> updateSnake());
        timer.start();
    }

    public void updateSnake() {
        snake.update(direction, getHeight(), getWidth());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
    }
}
