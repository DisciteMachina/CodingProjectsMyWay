import java.awt.*;
import java.util.Random;

public class Star {
    float x;
    float y;
    float z;

    float px;
    float py;

    int width;
    int height;

    boolean firstDraw;
    Random random = new Random();

    public Star(int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        x = random.nextInt(width) - width / 2;
        y = random.nextInt(height) - height / 2;
        z = random.nextInt(width);
        firstDraw = true;
    }

    public void update() {
        float sx = map(x / z, 0, 1, 0, width / 2);
        float sy = map(y / z, 0, 1, 0, height / 2);
        px = sx;
        py = sy;

        z -= 20;

        if (z <= 0) {
            init();
        }
    }

    public void draw(Graphics g2d) {
        g2d.setColor(Color.WHITE);

        float sx = map( x / z, 0, 1, 0, width / 2);
        float sy = map( y / z, 0, 1, 0, height / 2);

        int size = (int) map(z, 0, width, 5, 2);
        g2d.fillOval((int)sx, (int)sy, size, size);

        if (!firstDraw) {
            g2d.drawLine((int)px, (int)py, (int)sx, (int)sy);
        } else {
            firstDraw = false;
        }
    }

    public float map(float value, float oldMin, float oldMax, float newMin, float newMax) {
        return newMin + (value - oldMin) * (newMax - newMin) / (oldMax - oldMin);
    }
}
