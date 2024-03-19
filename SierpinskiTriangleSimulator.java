import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SierpinskiTriangleSimulator extends JPanel implements KeyListener{
    JFrame frame;

    ArrayList<Point> pointList;

    Point ranPoint;

    Color color;

    int numPoints = 1;

    public SierpinskiTriangleSimulator(){
        frame = new JFrame("Sierpinski Triangle Simulator");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frame.getSize());
        frame.add(this);

        pointList = new ArrayList<>();
        color = Color.WHITE;

        startSierpinskiProcess();

        frame.addKeyListener(this);
		frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    public void addPoint(){
        Point point1 = pointList.get(0);
        Point point2 = pointList.get(1);
        Point point3 = pointList.get(2);

        for (int i = 0; i < numPoints*10; i++){
            int p = (int)(Math.random()*3);
            Point corner;

            if (p == 0)
                corner = point1;
            else if (p == 1)
                corner = point2;
            else
                corner = point3;

            ranPoint = new Point((corner.getX() + ranPoint.getX())/2, (corner.getY() + ranPoint.getY())/2, color);
            pointList.add(ranPoint);
        }
        repaint();
    }

    public void startSierpinskiProcess(){
        pointList.add(new Point(this.getWidth()/2, 100, color));
        pointList.add(new Point(100, this.getHeight()-100, color));
        pointList.add(new Point(this.getWidth()-100, this.getHeight()-100, color));

        Polygon triangle = new Polygon(new int[]{this.getWidth()/2, 100, this.getWidth()-100}, new int[]{100, this.getHeight()-100, this.getHeight()-100}, 3);
        int xRan = (int)(Math.random()*(this.getWidth()-100-100+1))+100;
        int yRan = (int)(Math.random()*(this.getHeight()-100-100+1))+100;

        while (!triangle.contains(xRan, yRan)){
            xRan = (int)(Math.random()*(this.getWidth()-100-100+1))+100;
            yRan = (int)(Math.random()*(this.getHeight()-100-100+1))+100;
        }

        ranPoint = new Point(xRan, yRan, color);
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.WHITE);
        for (int i = 0; i < pointList.size(); i++){
            g.fillOval(pointList.get(i).getX(), pointList.get(i).getY(), 2, 2);
        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == 32)
			addPoint();
        if (e.getKeyCode() == 53)
            numPoints += 5;
        if (e.getKeyCode() == 49)
			numPoints = 1;
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    private class Point{
        private int x;
        private int y;
        private Color color;

        public Point(int x, int y, Color color){
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    public static void main(String[] args){
        new SierpinskiTriangleSimulator();
    }
}
