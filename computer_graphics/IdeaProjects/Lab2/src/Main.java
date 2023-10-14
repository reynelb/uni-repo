import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {
    private int startX = 100, startY = 100;
    private int endX = 400, endY = 400;

    public Main() {
        setTitle("Bresenham Demo");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Простое управление для демонстрации
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) startX += 10;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) startX -= 10;
                if (e.getKeyCode() == KeyEvent.VK_UP) startY -= 10;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) startY += 10;
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                repaint();
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawLine(g, startX, startY, endX, endY);
        drawCircle(g, 300, 300, 50); // circle at (300, 300) with radius 50
        drawEllipse(g, 300, 300, 100, 50); // ellipse at (300, 300) with Rx=100, Ry=50
    }


    public void drawLine(Graphics g, int x0, int y0, int x1, int y1) {
        // Algorithm for the line
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2;

        while (true) {
            g.drawRect(x0, y0, 1, 1);

            if (x0 == x1 && y0 == y1) break;

            e2 = err * 2;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void drawCircle(Graphics g, int x_center, int y_center, int r) {
        int x = r, y = 0;
        g.drawRect(x_center + r, y_center, 1, 1);
        if (r > 0) {
            g.drawRect(x_center - r, y_center, 1, 1);
            g.drawRect(x_center, y_center + r, 1, 1);
            g.drawRect(x_center, y_center - r, 1, 1);
        }
        int P = 1 - r;
        while (x > y) {
            y++;
            if (P <= 0) P = P + 2 * y + 1;
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }
            if (x < y) break;
            g.drawRect(x_center + x, y_center - y, 1, 1);
            g.drawRect(x_center - x, y_center - y, 1, 1);
            g.drawRect(x_center + x, y_center + y, 1, 1);
            g.drawRect(x_center - x, y_center + y, 1, 1);
            if (x != y) {
                g.drawRect(x_center + y, y_center - x, 1, 1);
                g.drawRect(x_center - y, y_center - x, 1, 1);
                g.drawRect(x_center + y, y_center + x, 1, 1);
                g.drawRect(x_center - y, y_center + x, 1, 1);
            }
        }
    }

    public void drawEllipse(Graphics g, int x_center, int y_center, int Rx, int Ry) {
        float dx, dy, d1, d2, x, y;
        x = 0;
        y = Ry;
        d1 = (Ry * Ry) - (Rx * Rx * Ry) + (0.25f * Rx * Rx);
        dx = 2 * Ry * Ry * x;
        dy = 2 * Rx * Rx * y;
        while (dx < dy) {
            g.drawRect((int) (x_center + x), (int) (y_center - y), 1, 1);
            g.drawRect((int) (x_center - x), (int) (y_center + y), 1, 1);
            g.drawRect((int) (x_center + x), (int) (y_center + y), 1, 1);
            g.drawRect((int) (x_center - x), (int) (y_center - y), 1, 1);
            if (d1 < 0) {
                x++;
                dx = dx + (2 * Ry * Ry);
                d1 = d1 + dx + (Ry * Ry);
            } else {
                x++;
                y--;
                dx = dx + (2 * Ry * Ry);
                dy = dy - (2 * Rx * Rx);
                d1 = d1 + dx - dy + (Ry * Ry);
            }
        }
        d2 = ((Ry * Ry) * ((x + 0.5f) * (x + 0.5f))) + ((Rx * Rx) * ((y - 1) * (y - 1))) - (Rx * Rx * Ry * Ry);
        while (y >= 0) {
            g.drawRect((int) (x_center + x), (int) (y_center - y), 1, 1);
            g.drawRect((int) (x_center - x), (int) (y_center + y), 1, 1);
            g.drawRect((int) (x_center + x), (int) (y_center + y), 1, 1);
            g.drawRect((int) (x_center - x), (int) (y_center - y), 1, 1);
            if (d2 > 0) {
                y--;
                dy = dy - (2 * Rx * Rx);
                d2 = d2 + (Rx * Rx) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * Ry * Ry);
                dy = dy - (2 * Rx * Rx);
                d2 = d2 + dx - dy + (Rx * Rx);
            }
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}
