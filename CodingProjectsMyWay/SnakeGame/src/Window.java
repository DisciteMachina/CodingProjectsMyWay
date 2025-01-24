import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Window extends JPanel {
    JFrame frame;
    Snake snake;
    static ArrayList<Point> body;
    Timer timer;
    Timer foodTimer;
    int FPS = 10;
    int direction = 0;
    static int scl = 40;

    boolean gameOver;

    Random random;
    static ArrayList<Food> foodList = new ArrayList<>();

    public Window(int height, int width) {
        frame = new JFrame("Snake Game");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.DARK_GRAY);

        frame.add(this);
        frame.setVisible(true);

        direction = 0;
        body = new ArrayList<>();
        snake = new Snake(getWidth() / (2 * Window.scl), getHeight() / (2 * Window.scl), 1, 0);

        this.gameOver = false;
        random = new Random();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (!gameOver) {
                    // Update direction based on arrow keys
                    switch (keyCode) {
                        case KeyEvent.VK_W, KeyEvent.VK_UP -> direction = 1; // Up
                        case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> direction = 2; // Right
                        case KeyEvent.VK_S, KeyEvent.VK_DOWN -> direction = 3; // Down
                        case KeyEvent.VK_A, KeyEvent.VK_LEFT -> direction = 4; // Left
                    }
                } else if (keyCode == KeyEvent.VK_R) {
                    restartGame();
                }

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

        timer = new Timer(1000 / FPS, e -> updateSnake());
        timer.start();

        foodTimer = new Timer(random.nextInt(5000), e -> spawnFood(body));
        foodTimer.start();
    }

    public void updateSnake() {
         if (direction != 0) {
             snake.update(direction, getHeight(), getWidth());

             if (snake.hasCollided) {
                 gameOver = true;
                 timer.stop();
                 foodTimer.stop();
                 repaint();
                 return;
             }
         }
        snake.checkCollision(foodList);
        repaint();
    }

    public void spawnFood(ArrayList<Point> snakeBody) {
        while (true) {
            Food food = new Food(getHeight(), getWidth());
            boolean overlapsSnake = snakeBody.stream().anyMatch(p -> p.x == food.x && p.y == food.y);

            if (!overlapsSnake) {
                foodList.add(food);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            snake.draw(g);

            for (Food food : foodList) {
                food.draw(g);
            }
        } else {
            gameOverScreen(g);
        }
    }

    public void gameOverScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        String gameOverText = "GAME OVER";
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(gameOverText)) / 2;
        int y = getHeight() / 2 - 50;
        g.drawString(gameOverText, x, y);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        String restartText = "Press 'R' to Restart";
        x = (getWidth() - metrics.stringWidth(restartText)) / 2;
        y += 100;
        g.drawString(restartText, x, y);
    }

    public void restartGame() {
        body.clear();
        snake = new Snake(getWidth() / (2 * Window.scl), getHeight() / (2 * Window.scl), 1, 0);
        foodList.clear();
        spawnFood(body);
        direction = 0;
        gameOver = false;
        timer.start();
        foodTimer.start();
        repaint();
    }
}
