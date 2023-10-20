import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Car {
    private int x, y;

    public Car(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveRight() {
        x += 1;
    }

    public void resetPosition(int width) {
        if (x > width) {
            x = -100;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 30);
        g.fillArc(x, y - 15, 100, 30, 0, 180);
        g.setColor(Color.WHITE);
        g.fillRect(x + 15, y - 10, 30, 10);
        g.fillRect(x + 55, y - 10, 30, 10);
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 20, 25, 25);
        g.fillOval(x + 65, y + 20, 25, 25);
        g.fillRect(x, y + 5, 100, 5);
    }
}

class Cloud {
    private int x, y;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 40, 20);
        g.fillOval(x - 20, y - 10, 40, 20);
        g.fillOval(x + 20, y - 10, 40, 20);
        g.fillOval(x + 40, y, 40, 20);
    }
}

class House {
    private int x, y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, 80, 60);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + 20, y + 20, 40, 50);
        g.setColor(Color.ORANGE);
        int[] xPoints = {x, x + 80, x + 40};
        int[] yPoints = {y, y, y - 40};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}

class Tree {
    private int x, y;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + 35, y + 30, 10, 40);
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 80, 60);
    }
}

public class Main extends JPanel implements ActionListener {
    private final Car car;
    private final Cloud[] clouds;
    private final House[] houses;
    private final Tree[] trees;

    public Main() {
        Timer timer = new Timer(10, this);
        timer.start();

        int roadY = getHeight() / 2 + 100;
        car = new Car(0, roadY - 25);
        clouds = new Cloud[]{new Cloud(50, 30), new Cloud(200, 60), new Cloud(400, 20)};
        houses = new House[5];
        trees = new Tree[6];

        for (int i = 0; i < 5; i++) {
            houses[i] = new House(100 + i * 100, 120);
        }
        for (int i = 0; i < 6; i++) {
            trees[i] = new Tree(50 + i * 100, 170);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ... rest of the drawing logic ...
        car.draw(g);
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
        for (House house : houses) {
            house.draw(g);
        }
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        car.moveRight();
        car.resetPosition(getWidth());
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
