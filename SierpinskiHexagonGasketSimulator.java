import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SierpinskiHexagonGasketSimulator extends JPanel implements KeyListener{
    JFrame frame;

    ArrayList<Point> pointList;

    Point ranPoint;

    Color color;

    int numPoints = 1;

    public SierpinskiHexagonGasketSimulator(){
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
        Point point4 = pointList.get(3);
        Point point5 = pointList.get(4);
        Point point6 = pointList.get(5);

        for (int i = 0; i < numPoints*10; i++){
            int p = (int)(Math.random()*6);
            Point corner;

            if (p == 0)
                corner = point1;
            else if (p == 1)
                corner = point2;
            else if (p == 2)
                corner = point3;
			else if (p == 3)
				corner = point4;
			else if (p == 4)
				corner = point5;
			else
				corner = point6;

            ranPoint = new Point((corner.getX() + ranPoint.getX())/2, (corner.getY() + ranPoint.getY())/2, color);
            pointList.add(ranPoint);
        }
        repaint();
    }

    public void startSierpinskiProcess(){
		pointList.add(new Point(this.getWidth()/4, this.getHeight()/2, color));
		pointList.add(new Point(this.getWidth()-this.getWidth()/4, this.getHeight()/2, color));
		pointList.add(new Point((int)(this.getWidth()/4+0.5*this.getWidth()/4), (int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2), color));
		pointList.add(new Point((int)(this.getWidth()/4+(this.getWidth()/4+0.5*this.getWidth()/4)), (int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2), color));
		pointList.add(new Point((int)(this.getWidth()/4+0.5*this.getWidth()/4), (int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2), color));
		pointList.add(new Point((int)(this.getWidth()/4+(this.getWidth()/4+0.5*this.getWidth()/4)), (int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2), color));

        Polygon hexagon = new Polygon(new int[]{this.getWidth()/4, this.getWidth()-this.getWidth()/4, (int)(this.getWidth()/4+0.5*this.getWidth()/4), (int)(this.getWidth()/4+(this.getWidth()/4+0.5*this.getWidth()/4)), (int)(this.getWidth()/4+0.5*this.getWidth()/4), (int)(this.getWidth()/4+(this.getWidth()/4+0.5*this.getWidth()/4))}, new int[]{this.getHeight()/2, this.getHeight()/2, (int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2), (int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2), (int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2), (int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2)}, 6);
        int xRan = (int)(Math.random()*(this.getWidth()/2+1))+this.getWidth()/4;
        int yRan = (int)(Math.random()*((int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2)-(int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2)+1))+(int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2);

        while (!hexagon.contains(xRan, yRan)){
            xRan = (int)(Math.random()*(this.getWidth()/2+1))+this.getWidth()/4;
        	yRan = (int)(Math.random()*((int)(this.getHeight()/2+(this.getWidth()/4)*Math.sqrt(3)/2)-(int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2)+1))+(int)(this.getHeight()/2-(this.getWidth()/4)*Math.sqrt(3)/2);
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
            numPoints = 5;
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
        new SierpinskiHexagonGasketSimulator();
    }
}