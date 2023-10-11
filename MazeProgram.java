// CHANGE UR PATH NAME
// u can ignore boolean[][] passedOver I'm just trying shit out

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MazeProgram extends JPanel implements KeyListener {
    JFrame frame;

    int x,y;
    int dir;
    int dim = 20;
    String[][] maze;
    boolean[][] passedOver;
    int dist = 50;

    int startR = 75;
    int startG = 15;
    int startB = 130;
    int startHR = 60;
    int startHG = 12;
    int startHB = 104;

    ArrayList<Wall> walls;
    ArrayList<FrontWall> frontWalls;
    boolean in3D = false;

    public MazeProgram(){
        frame = new JFrame();
        frame.add(this);
        frame.setSize(2560, 1600);
        frame.addKeyListener(this);

        x = 1;
        y = 1;
        dir = 0;

        setMaze();

        passedOver = new boolean[maze.length][maze[0].length];

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setMaze(){
        try{
            File file = new File("/Users/nikhilshyam/Desktop/Data Structures/Template/maze.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));
            String st;
            int row = 0;
            maze = new String[41][];
            while ((st = input.readLine())!= null){
                String[] rowOfWalls = st.split("");
                maze[row] = rowOfWalls;
                if(st.indexOf("N") >= 0){
                    dir = 0;
                    x = st.indexOf("N");
                    y = row;
                    maze[y][x] = " ";
                }
                if(st.indexOf("E") >= 0){
                    dir = 1;
                    x = st.indexOf("E");
                    y = row;
                    maze[y][x] = " ";
                }
                if(st.indexOf("W") >= 0){
                    dir = 3;
                    x = st.indexOf("W");
                    y = row;
                    maze[y][x] = " ";
                }
                if(st.indexOf("S") >= 0){
                    dir = 2;
                    x = st.indexOf("S");
                    y = row;
                    maze[y][x] = " ";
                }
                row++;
            }
        }
        catch(IOException e){
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g.setColor(Color.BLACK); //13, 3, 35
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g2.setStroke(new BasicStroke(2));
        if (!in3D){
            for(int i = 0; i < maze.length; i++){
                for (int j = 0; j < maze[i].length; j++){
                    if (maze[i][j].equals("#")){
                        g.setColor(new Color(startR, startG, startB)); //74, 17, 132 //44, 9, 119in //181, 94, 221pi
                        g.fillRect(j*dim+40, i*dim+40, dim, dim);
                        g.setColor(Color.BLACK);
                        g.drawRect(j*dim+40, i*dim+40, dim, dim);
                    }
                }
            }

            g.setColor(new Color(150, 150, 250));
            g.fillOval(x*dim+40, y*dim+40, dim, dim);
            g.setColor(Color.CYAN);
            g.drawOval(x*dim+40, y*dim+40, dim, dim);
        }else{
            for(int i = 0; i < maze.length; i++){
                for (int j = 0; j < maze[i].length; j++){
                    if (maze[i][j].equals("#")){
                        g.setColor(new Color(startR, startG, startB));
                        g.fillRect(j*10+800, i*10+200, 10, 10);
                        g.setColor(Color.BLACK);
                        g.drawRect(j*10+800, i*10+200, 10, 10);
                    }
                }
            }
            g.setColor(new Color(150, 150, 250));
            g.fillOval(x*10+800, y*10+200, 10, 10);
            g.setColor(Color.CYAN);
            g.drawOval(x*10+800, y*10+200, 10, 10);

            for (Wall wall: walls){
                g2.setPaint(wall.getGradientPaint());
                g2.fill(wall.getWall());
                g.setColor(Color.BLACK);
                g2.draw(wall.getWall());
            }

            for (FrontWall frontWall: frontWalls){
                g.setColor(frontWall.getColor());
                g2.fill(frontWall.getFrontWall());
                g.setColor(Color.BLACK);
                g2.draw(frontWall.getFrontWall());
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        // 0 - N | 1 - E | 2 - S | 3 - W
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 37){ // turning left
            dir--;
            if (dir < 0)
                dir = 3;
        }
        if (e.getKeyCode() == 38){ // walking forwards
            passedOver[y][x] = true;

            switch(dir){
                case 0:
                    if (maze[y-1][x].equals(" "))
                        y--;
                    break;
                case 1:
                    if (maze[y][x+1].equals(" "))
                        x++;
                    break;
                case 2:
                    if (maze[y+1][x].equals(" "))
                        y++;
                    break;
                case 3:
                    if (maze[y][x-1].equals(" "))
                        x--;
                    break;
            }
        }
        if (e.getKeyCode() == 39){ // turning right
            dir++;
            dir%=4;
        }

        if(e.getKeyCode() == 32){
            in3D =! in3D;
        }
        if (in3D){
            walls = new ArrayList<Wall>();
            frontWalls = new ArrayList<FrontWall>();
            addLeftHallways();
            addRightHallways();
            addLeftWalls();
            addRightWalls();
            addFloors();
            addCeilings();
            addFrontWalls();
        }

        repaint();
    }

    public void addLeftHallways(){
        for (int i = 0; i < 5; i++){
            try{
                int[] x = {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startHR, startHG, startHB), 350, 400, Color.BLACK)));
                x = new int[] {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                y = new int[] {150+dist*i, 150+dist*i, 650-dist*i, 650-dist*i};
                walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startHR, startHG, startHB), 350, 400, Color.BLACK)));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addRightHallways(){
        for (int i = 0; i < 5; i++){
            try{
                int[] x = {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startHR, startHG, startHB), 450, 400, Color.BLACK)));
                x = new int[] {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                y = new int[] {150+dist*i, 150+dist*i, 650-dist*i, 650-dist*i};
                walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startHR, startHG, startHB), 450, 400, Color.BLACK)));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addLeftWalls(){
        for (int i = 0; i < 5; i++){
            try{
                if (dir == 0 && maze[y-i][x-1].equals("#")){
                    int[] x = {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startR, startG, startB), 350, 400, Color.BLACK)));
                }
                if (dir == 1 && maze[y-1][x+i].equals("#")){
                    int[] x = {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startR, startG, startB), 350, 400, Color.BLACK)));
                }
                if (dir == 2 && maze[y+i][x+1].equals("#")){
                    int[] x = {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startR, startG, startB), 350, 400, Color.BLACK)));
                }
                if (dir == 3 && maze[y+1][x-i].equals("#")){
                    int[] x = {100+dist*i, 150+dist*i, 150+dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(100, 400, new Color(startR, startG, startB), 350, 400, Color.BLACK)));
                }
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addRightWalls(){
        for (int i = 0; i < 5; i++){
            try{
                if (dir == 0 && maze[y-i][x+1].equals("#")){
                    int[] x = {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startR, startG, startB), 450, 400, Color.BLACK)));
                }
                if (dir == 1 && maze[y+1][x+i].equals("#")){
                    int[] x = {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startR, startG, startB), 450, 400, Color.BLACK)));
                }
                if (dir == 2 && maze[y+i][x-1].equals("#")){
                    int[] x = {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startR, startG, startB), 450, 400, Color.BLACK)));
                }
                if (dir == 3 && maze[y-1][x-i].equals("#")){
                    int[] x = {700-dist*i, 650-dist*i, 650-dist*i, 700-dist*i};
                    int[] y = {100+dist*i, 150+dist*i, 650-dist*i, 700-dist*i};
                    walls.add(new Wall(x, y, new GradientPaint(700, 400, new Color(startR, startG, startB), 450, 400, Color.BLACK)));
                }
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addFloors(){
        for (int i = 0; i < 5; i++){
            try{
                int[] x = {100+dist*i, 700-dist*i, 650-dist*i, 150+dist*i};
                int[] y = {700-dist*i, 700-dist*i, 650-dist*i, 650-dist*i};
                walls.add(new Wall(x, y, new GradientPaint(400, 700, new Color(startR, startG, startB), 400, 450, Color.BLACK)));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addCeilings(){
        for (int i = 0; i < 5; i++){
            try{
                int[] x = {100+dist*i, 700-dist*i, 650-dist*i, 150+dist*i};
                int[] y = {100+dist*i, 100+dist*i, 150+dist*i, 150+dist*i};
                walls.add(new Wall(x, y, new GradientPaint(400, 100, new Color(startR, startG, startB), 400, 350, Color.BLACK)));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    public void addFrontWalls(){
    int r = 0;
    int g = 0;
    int b = 0;
        for (int i = 5; i >= 1; i--){
            try{
                if (dir == 0 && maze[y-i][x].equals("#")){
                    int[] x = {100+dist*i, 700-dist*i, 700-dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 100+dist*i, 700-dist*i, 700-dist*i};
                    frontWalls.add(new FrontWall(x, y, new Color(r, g, b)));
                }
                if (dir == 1 && maze[y][x+i].equals("#")){
                    int[] x = {100+dist*i, 700-dist*i, 700-dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 100+dist*i, 700-dist*i, 700-dist*i};
                    frontWalls.add(new FrontWall(x, y, new Color(r, g, b)));
                }
                if (dir == 2 && maze[y+i][x].equals("#")){
                    int[] x = {100+dist*i, 700-dist*i, 700-dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 100+dist*i, 700-dist*i, 700-dist*i};
                    frontWalls.add(new FrontWall(x, y, new Color(r, g, b)));
                }
                if (dir == 3 && maze[y][x-i].equals("#")){
                    int[] x = {100+dist*i, 700-dist*i, 700-dist*i, 100+dist*i};
                    int[] y = {100+dist*i, 100+dist*i, 700-dist*i, 700-dist*i};
                    frontWalls.add(new FrontWall(x, y, new Color(r, g, b)));
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            r += 15;
            g += 3;
            b += 26;
        }
    }

    public void keyTyped(KeyEvent e){
    }

    public class Wall{
        private int[] y;
        private int[] x;
        private GradientPaint gradientPaint;
        
        public Wall(int[] x, int[] y, GradientPaint gradientPaint){
            this.x = x;
            this.y = y;
            this.gradientPaint = gradientPaint;
        }

        public GradientPaint getGradientPaint(){
            return gradientPaint;
        }

        public Polygon getWall(){
            return new Polygon(x,y,x.length);
        }
    }

    public class FrontWall{
        private int[] y;
        private int[] x;
        private Color color;
        
        public FrontWall(int[] x, int[] y, Color color){
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public Color getColor(){
            return color;
        }

        public Polygon getFrontWall(){
            return new Polygon(x,y,x.length);
        }
    }

    public static void main(String[] args){
        MazeProgram app = new MazeProgram();
    }
}
