import java.awt.*;

public class Snake {
    int x;
    int y;
    int dx;
    int dy;
    int width;
    int height;

    boolean gameOver;

    Color color;

    public Snake(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.width = 45;
        this.height = 45;

        gameOver = false;
        color = Color.RED;
    }

    public void update(int direction, int windowHeight, int windowWidth) {

        if (x < 0 || y < 0 || x + width > windowWidth || y + height > windowHeight) {
            dx = 0;
            dy = 0;
            gameOver = true;
        }

        switch(direction) {

        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y, width, height);
    }
}
