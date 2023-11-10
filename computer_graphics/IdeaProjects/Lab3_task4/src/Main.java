import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    public static void main(String[] args) {

        //Creating the frame
        JFrame frame = new JFrame("Task 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        DrawingCanvas canvas = new DrawingCanvas();
        frame.add(canvas);

        frame.setVisible(true);

    }

    static class DrawingCanvas extends JPanel
    {
        private Point topTriangleVertex = new Point(200,100);

        public DrawingCanvas()
        {
            addMouseMotionListener(new MouseAdapter()
            {
               @Override
               public void mouseDragged(MouseEvent e)
               {
                   topTriangleVertex = e.getPoint();
                   repaint();
               }
            });
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            //Draw bottom triangle (solid color)
            g.setColor(Color.BLUE);
            g.fillPolygon(new int[]{150, 250, 200}, new int[]{250, 250, 150}, 3);

            //Draw top triangle (semi-transparent)
            g.setColor(new Color(255, 0, 0, 127));
            g.fillPolygon(new int[]{topTriangleVertex.x - 50, topTriangleVertex.x + 50, topTriangleVertex.x},
                    new int[]{topTriangleVertex.y + 50, topTriangleVertex.y + 50, topTriangleVertex.y - 50}, 3);
        }
    }
}