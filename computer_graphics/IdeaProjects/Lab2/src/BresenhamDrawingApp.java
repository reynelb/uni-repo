import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BresenhamDrawingApp extends JFrame {

    public BresenhamDrawingApp() {
        super("Drawing App");
        DrawingPanel drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BresenhamDrawingApp::new);
    }

    static class DrawingPanel extends JPanel {
        private Point startPoint = null;
        private ShapeType currentShape = ShapeType.LINE;

        public DrawingPanel() {
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    drawShape(startPoint, e.getPoint());
                    startPoint = null; // Reset the start point
                }
            });

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_L: // 'L' key for Line
                            currentShape = ShapeType.LINE;
                            break;
                        case KeyEvent.VK_C: // 'C' key for Circle
                            currentShape = ShapeType.CIRCLE;
                            break;
                        case KeyEvent.VK_E: // 'E' key for Ellipse
                            currentShape = ShapeType.ELLIPSE;
                            break;
                    }
                }
            });
            setFocusable(true); // To receive key events, the panel must be focusable.
        }

        private void drawShape(Point start, Point end) {
            Graphics g = getGraphics();
            switch (currentShape) {
                case LINE:
                    BresenhamAlgorithms.drawLine(g, start.x, start.y, end.x, end.y);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
                    BresenhamAlgorithms.drawCircle(g, start.x, start.y, radius);
                    break;
                case ELLIPSE:
                    int a = Math.abs(end.x - start.x) / 2; // Semi-major axis
                    int b = Math.abs(end.y - start.y) / 2; // Semi-minor axis
                    int xCenter = (start.x + end.x) / 2;
                    int yCenter = (start.y + end.y) / 2;
                    BresenhamAlgorithms.drawEllipse(g, xCenter, yCenter, a, b);
                    break;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

        }

        enum ShapeType {
            LINE, CIRCLE, ELLIPSE
        }
    }


    static class BresenhamAlgorithms {

        public static void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int sx = x1 < x2 ? 1 : -1;
            int sy = y1 < y2 ? 1 : -1;
            int err = dx - dy;
            int e2;

            while (true) {
                g.fillRect(x1, y1, 1, 1); // Draw pixel at (x1, y1)

                if (x1 == x2 && y1 == y2) {
                    break;
                }

                e2 = 2 * err;
                if (e2 > -dy) {
                    err -= dy;
                    x1 += sx;
                }

                if (e2 < dx) {
                    err += dx;
                    y1 += sy;
                }
            }
        }

        public static void drawCircle(Graphics g, int xCenter, int yCenter, int radius) {
            int x = 0;
            int y = radius;
            int d = 3 - 2 * radius;
            drawCirclePoints(g, xCenter, yCenter, x, y);
            while (y >= x) {
                x++;
                if (d > 0) {
                    y--;
                    d = d + 4 * (x - y) + 10;
                } else {
                    d = d + 4 * x + 6;
                }
                drawCirclePoints(g, xCenter, yCenter, x, y);
            }

    }

        private static void drawCirclePoints(Graphics g, int xCenter, int yCenter, int x, int y) {
            g.fillRect(xCenter + x, yCenter + y, 1, 1);
            g.fillRect(xCenter - x, yCenter + y, 1, 1);
            g.fillRect(xCenter + x, yCenter - y, 1, 1);
            g.fillRect(xCenter - x, yCenter - y, 1, 1);
            g.fillRect(xCenter + y, yCenter + x, 1, 1);
            g.fillRect(xCenter - y, yCenter + x, 1, 1);
            g.fillRect(xCenter + y, yCenter - x, 1, 1);
            g.fillRect(xCenter - y, yCenter - x, 1, 1);
        }

        public static void drawEllipse(Graphics g, int xCenter, int yCenter, int a, int b) {
            int wx, wy;
            int thresh;
            int asq = a * a;
            int bsq = b * b;
            int xa, ya;

            g.fillRect(xCenter, yCenter - b, 1, 1);
            g.fillRect(xCenter, yCenter + b, 1, 1);
            g.fillRect(xCenter - a, yCenter, 1, 1);
            g.fillRect(xCenter + a, yCenter, 1, 1);

            wx = 0;
            wy = b;
            xa = 0;
            ya = asq * 2 * b;
            thresh = asq / 4 - asq * b;

            // Region 1
            while (xa < ya) {
                xa += bsq * 2;
                wx++;
                thresh += xa;

                if (thresh >= 0) {
                    ya -= asq * 2;
                    wy--;
                    thresh -= ya;
                }

                g.fillRect(xCenter + wx, yCenter - wy, 1, 1);
                g.fillRect(xCenter - wx, yCenter - wy, 1, 1);
                g.fillRect(xCenter + wx, yCenter + wy, 1, 1);
                g.fillRect(xCenter - wx, yCenter + wy, 1, 1);
            }

            // Region 2
            wx = a;
            wy = 0;
            xa = bsq * 2 * a;
            ya = 0;
            thresh = bsq / 4 - bsq * a;

            while (wx >= 0) {
                ya += asq * 2;
                wy++;
                thresh += ya;

                if (thresh >= 0) {
                    xa -= bsq * 2;
                    wx--;
                    thresh -= xa;
                }

                g.fillRect(xCenter + wx, yCenter - wy, 1, 1);
                g.fillRect(xCenter - wx, yCenter - wy, 1, 1);
                g.fillRect(xCenter + wx, yCenter + wy, 1, 1);
                g.fillRect(xCenter - wx, yCenter + wy, 1, 1);
            }
    }}}
