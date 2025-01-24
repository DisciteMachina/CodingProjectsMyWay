import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {
    int x;
    int y;
    int width;
    int height;
    Color color;
    boolean hasCollided;

    public Snake(int x, int y, int dx, int dy) {
        this.x = x * Window.scl;
        this.y = y * Window.scl;

        this.width = Window.scl;
        this.height = Window.scl;

        color = Color.RED;
        this.hasCollided = false;

        Window.body.clear();
        Window.body.add(new Point(this.x,this.y));

    }

    public void update(int direction, int windowHeight, int windowWidth) {

        Point head = Window.body.get(0);
        int newX = head.x;
        int newY = head.y;

        switch (direction) {
            case 1 -> newY -= Window.scl; // Up
            case 2 -> newX += Window.scl; // Right
            case 3 -> newY += Window.scl; // Down
            case 4 -> newX -= Window.scl; // Left
        }

        if (newX < 0 || newY < 0 || newX + width > windowWidth || newY + height > windowHeight) {
            hasCollided = true;
            return;
        }

        for (Point segment : Window.body) {
            if (segment.x == newX && segment.y == newY) {
                hasCollided = true;
                return;
            }
        }

        Window.body.add(0, new Point(newX, newY));
        Window.body.remove(Window.body.size() - 1);
    }

    public void checkCollision(ArrayList<Food> foodList) {
        Point head = Window.body.get(0);

        for (Food food : foodList) {
            if (head.x == food.x && head.y == food.y) {
                foodList.remove(food);
                grow();
                break;
            }
        }
    }

    public void grow() {
        Point tail = Window.body.get(Window.body.size() - 1);
        Window.body.add(new Point(tail.x, tail.y));
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Point segment : Window.body) {
            g.fillRect(segment.x, segment.y, width, height);
        }
    }

}
