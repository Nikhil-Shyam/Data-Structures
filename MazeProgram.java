// CHANGE UR PATH NAME

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
    int dist = 50;

    ArrayList<Wall> walls;
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
        // g.setColor(Color.BLACK);

        Graphics2D g2 = (Graphics2D)g;

        g.setColor(new Color(13, 3, 35)); //13, 3, 35
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g2.setStroke(new BasicStroke(2));
        if (!in3D){
            for(int i = 0; i < maze.length; i++){
                for (int j = 0; j < maze[i].length; j++){
                    if (maze[i][j].equals("#")){
                        g.setColor(new Color(74, 17, 132)); //74, 17, 132 //44, 9, 119in //181, 94, 221pi
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
            for (Wall wall: walls){
                g.setColor(Color.YELLOW);
                g2.fill(wall.getWall());
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
            addLeftWalls();
            //addRightWalls();
            //addFloors();
            //addCeilings();
            //addFrontWalls();
        }

        repaint();
    }

    public void addLeftWalls(){
        int[] x = {100, 150, 150, 100};
        int[] y = {100, 150, 650, 700};
        walls.add(new Wall(x, y, dist));
    }

    public void keyTyped(KeyEvent e){
    }

    public class Wall{
        private int[] y;
        private int[] x;
        private int dist;
        
        public Wall(int[] x, int[] y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public Polygon getWall(){
            return new Polygon(x,y,x.length);
        }
    }

    public static void main(String[] args){
        MazeProgram app = new MazeProgram();
    }
}
