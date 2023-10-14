import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
    private int carX = 0; // Start position of the car in x

    public Main() {
        Timer timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScene(g);
    }

    private void drawCar(Graphics g, int x, int y) {
        // Car body
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 30); // Main body
        g.fillArc(x, y - 15, 100, 30, 0, 180); // Car's roof with a curve

        // Windows
        g.setColor(Color.WHITE);
        g.fillRect(x + 15, y - 10, 30, 10); // Left window
        g.fillRect(x + 55, y - 10, 30, 10); // Right window

        // Wheels
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 20, 25, 25); // Left wheel
        g.fillOval(x + 65, y + 20, 25, 25); // Right wheel

        // Front grill/bumper
        g.fillRect(x, y + 5, 100, 5); // Car's bumper
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 40, 20);
        g.fillOval(x - 20, y - 10, 40, 20);
        g.fillOval(x + 20, y - 10, 40, 20);
        g.fillOval(x + 40, y, 40, 20);
    }

    private void drawScene(Graphics g) {

        // Paint the sky blue (upper half)
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight() / 2);

        // Paint the ground brown (lower half)
        g.setColor(new Color(139, 69, 19)); // RGB for brown
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw the road (black line)
        int roadY = getHeight() / 2 + 100; // Adjust the 100 if needed
        g.setColor(Color.BLACK);
        g.fillRect(0, roadY, getWidth(), 5); // 5 pixels thick for the road


        //houses
        for (int i = 0; i < 5; i++) {
            int x = 100 + i * 100;
            int y = 120;
            g.setColor(Color.GRAY);
            g.fillRect(x, y, 80, 60);  // house's body
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x + 20, y + 20, 40, 50); // door
            g.setColor(Color.ORANGE);
            int[] xPoints = {x, x + 80, x + 40};
            int[] yPoints = {y, y, y - 40};
            g.fillPolygon(xPoints, yPoints, 3); // roof
        }

        // Car
        drawCar(g, carX, roadY - 25);

        //trees
        for (int i = 0; i < 6; i++) {
            int x = 50 + i * 100;
            int y = 170;
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x + 35, y + 30, 10, 40);  // tree trunk
            g.setColor(Color.GREEN);
            g.fillOval(x, y, 80, 60); // tree canopy
        }

        //clouds
        drawCloud(g, 50, 30);
        drawCloud(g, 200, 60);
        drawCloud(g, 400, 20);

        // Title
        g.setColor(Color.BLACK);
        g.drawString("My animation", 10, 20);
        g.setColor(Color.BLACK);
        g.drawString("Moving car", 10, 40);
        g.setColor(Color.BLACK);
        g.drawString("Java Swing", 10, 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        carX += 1; // moving the car 1 pixel to the right
        if (carX > getWidth()) {
            carX = -100; // if the car is out of the screen, it'll start again
        }
        repaint(); // draw the panel again
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
