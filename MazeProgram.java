// CHANGE UR PATH NAMES
// change the startR/G/B and startHR/G/B to ur own values
// the startHR/G/B shld be a darker tone of the color u choose with startR/G/B

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class MazeProgram extends JPanel implements KeyListener {
    JFrame frame;

    int x,y;
    int dir;
    int dim = 20;
    String[][] maze;
    int dist = 50;

    int startR = 75; //75
    int startG = 15; //15
    int startB = 130; //130
    int startHR = 60; //60
    int startHG = 12; //12
    int startHB = 104; //104

    BufferedImage northWalk, northStand, eastWalk, eastStand, southWalk, southStand, westWalk, westStand;
    int count;

    ArrayList<Wall> walls;
    ArrayList<FrontWall> frontWalls;
    boolean in3D = false;

    private Monster monster;

    Font font = new Font("Comic Sans", Font.BOLD, 40);
    boolean gameOver = false;

    public MazeProgram(){
        frame = new JFrame();
        frame.add(this);
        frame.setSize(2560, 1600);
        frame.addKeyListener(this);

        try{
            northStand = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/northStand.png"));
            northWalk = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/northWalk.png"));
            eastWalk = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/eastWalk.png"));
            eastStand = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/eastStand.png"));
            southWalk = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/southWalk.png"));
            southStand = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/southStand.png"));
            westWalk = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/westWalk.png"));
            westStand = ImageIO.read(new File("/Users/nikhilshyam/Desktop/Data Structures/Template/westStand.png"));
        }catch(IOException e){}

        x = 1;
        y = 1;
        dir = 0;

        setMaze();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public BufferedImage resize(BufferedImage image, int width, int height){
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledVersion = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledVersion.createGraphics();
        g2.drawImage(temp, 0, 0, null);
        g2.dispose();
        return scaledVersion;
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
                if(st.indexOf("M") >= 0){
                    monster = new Monster(st.indexOf("M"), row);
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
            northWalk = resize(northWalk, dim, dim);
            northStand = resize(northStand, dim, dim);
            eastWalk = resize(eastWalk, dim, dim);
            eastStand = resize(eastStand, dim, dim);
            southWalk = resize(southWalk, dim, dim);
            southStand = resize(southStand, dim, dim);
            westWalk = resize(westWalk, dim, dim);
            westStand = resize(westStand, dim, dim);

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

            g.setColor(Color.RED);
            g.fillOval(monster.getX()*dim+40, monster.getY()*dim+40, dim, dim);
            g.setColor(Color.WHITE);
            g.drawOval(monster.getX()*dim+40, monster.getY()*dim+40, dim, dim);

            if (dir == 0){
                if (count%2==0)
                    g.drawImage(northWalk, x*dim+40, y*dim+40, this);
                else
                    g.drawImage(northStand, x*dim+40, y*dim+40, this);
            }
            if (dir == 1){
                if (count%2==0)
                    g.drawImage(eastWalk, x*dim+40, y*dim+40, this);
                else
                    g.drawImage(eastStand, x*dim+40, y*dim+40, this);
            }
            if (dir == 2){
                if (count%2==0)
                    g.drawImage(southWalk, x*dim+40, y*dim+40, this);
                else
                    g.drawImage(southStand, x*dim+40, y*dim+40, this);
            }
            if (dir == 3){
                if (count%2==0)
                    g.drawImage(westWalk, x*dim+40, y*dim+40, this);
                else
                    g.drawImage(westStand, x*dim+40, y*dim+40, this);
            }
            if (gameOver){
                g2.setColor(Color.WHITE);
                g2.setFont(font);
                g2.drawString("CAPTURED, YOU LOSE!", 300, 200);
            }
        }else{
            northWalk = resize(northWalk, 10, 10);
            northStand = resize(northStand, 10, 10);
            eastWalk = resize(eastWalk, 10, 10);
            eastStand = resize(eastStand, 10, 10);
            southWalk = resize(southWalk, 10, 10);
            southStand = resize(southStand, 10, 10);
            westWalk = resize(westWalk, 10, 10);
            westStand = resize(westStand, 10, 10);

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

            g.setColor(Color.RED);
            g.fillOval(monster.getX()*10+800, monster.getY()*10+200, 10, 10);
            g.setColor(Color.WHITE);
            g.drawOval(monster.getX()*10+800, monster.getY()*10+200, 10, 10);

            if (count%2 == 0)
                g.drawImage(eastWalk, x*10+800, y*10+200, this);
            else
                g.drawImage(eastWalk, x*10+800, y*10+200, this);
            if (dir == 0){
                if (count%2==0)
                    g.drawImage(northWalk, x*10+800, y*10+200, this);
                else
                    g.drawImage(northStand, x*10+800, y*10+200, this);
            }
            if (dir == 1){
                if (count%2==0)
                    g.drawImage(eastWalk, x*10+800, y*10+200, this);
                else
                    g.drawImage(eastStand, x*10+800, y*10+200, this);
            }
            if (dir == 2){
                if (count%2==0)
                    g.drawImage(southWalk, x*10+800, y*10+200, this);
                else
                    g.drawImage(southStand, x*10+800, y*10+200, this);
            }
            if (dir == 3){
                if (count%2==0)
                    g.drawImage(westWalk, x*10+800, y*10+200, this);
                else
                    g.drawImage(westStand, x*10+800, y*10+200, this);
            }

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
            if (gameOver){
                g2.setColor(Color.WHITE);
                g2.setFont(font);
                g2.drawString("CAPTURED, YOU LOSE!", 500, 100);
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        count++;
        monster.updatePosition(x, y);

        // 0 - N | 1 - E | 2 - S | 3 - W
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
        if (e.getKeyCode() == 40){ // walking backwards
            switch(dir){
                case 0:
                    if (maze[y+1][x].equals(" "))
                        y++;
                    break;
                case 1:
                    if (maze[y][x-1].equals(" "))
                        x--;
                    break;
                case 2:
                    if (maze[y-1][x].equals(" "))
                        y--;
                    break;
                case 3:
                    if (maze[y][x+1].equals(" "))
                        x++;
                    break;
            }
        }

        if (x == monster.getX() && y == monster.getY()){
            gameOver = true;
            repaint();
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

    public class Monster{
        private int monsterX;
        private int monsterY;

        public Monster(int monsterX, int monsterY){
            this.monsterX = monsterX;
            this.monsterY = monsterY;
        }

        public void updatePosition(int playerX, int playerY){
            if (monsterX < playerX){
                if (isValidMove(monsterX + 1, monsterY)){
                    monsterX++;
                }
            }else if (monsterX > playerX){
                if (isValidMove(monsterX - 1, monsterY)){
                    monsterX--;
                }
            }
            if (monsterY < playerY){
                if (isValidMove(monsterX, monsterY + 1)){
                    monsterY++;
                }
            }else if (monsterY > playerY) {
                if (isValidMove(monsterX, monsterY - 1)){
                    monsterY--;
                }
            }   
        }

        public boolean isValidMove(int x, int y){
            if (maze[y][x].equals(" "))
                return true;
            return false;
        }

        public int getX(){
            return monsterX;
        }

        public int getY(){
            return monsterY;
        }
    }

    public static void main(String[] args){
        MazeProgram app = new MazeProgram();
    }
}
