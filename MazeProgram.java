import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazeProgram extends JPanel implements KeyListener {
    JFrame frame;

    int x,y;
    int dim = 20;
    String[][] maze;

    public MazeProgram(){
        frame = new JFrame();
        frame.add(this);
        frame.setSize(2560, 1600);
        frame.addKeyListener(this);

        x = 1;
        y = 1;

        setMaze();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setMaze(){
        try{
            File file = new File("maze.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));
            String st;
            int row = 0;
            maze = new String[41][];
            while ((st = input.readLine())!= null){
                String[] rowOfWalls = st.split("");
                maze[row] = rowOfWalls;
                if(st.indexOf("H") >= 0){
                    x = st.indexOf("H");
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

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g2.setStroke(new BasicStroke(2));
        for(int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (maze[i][j].equals("#")){
                    g.setColor(Color.YELLOW);
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
    }
    
    public void keyReleased(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 37 && maze[y][x-1].equals(" "))
            x-=1;
        if (e.getKeyCode() == 38 && maze[y-1][x].equals(" "))
            y-=1;
        if (e.getKeyCode() == 40 && maze[y+1][x].equals(" "))
            y+=1;
        if (e.getKeyCode() == 39 && maze[y][x+1].equals(" "))
            x+=1;
        repaint();
    }
    public void keyTyped(KeyEvent e){
    }
    public static void main(String[] args){
        MazeProgram app = new MazeProgram();
    }
}
