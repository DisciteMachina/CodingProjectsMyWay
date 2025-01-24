import java.awt.*;
import java.util.Random;

public class Food {
    int x;
    int y;
    Color color;

    Random random = new Random();

    public Food(int windowHeight, int windowWidth) {
        this.x = random.nextInt(windowWidth / Window.scl) * Window.scl;
        this.y = random.nextInt(windowHeight / Window.scl) * Window.scl;

        this.color = Color.BLUE;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y, Window.scl, Window.scl);
    }
}
