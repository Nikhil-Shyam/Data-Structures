import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JFrame implements ActionListener, MouseListener{
	JToggleButton[][] board;
	JPanel boardPanel;

	ImageIcon mineIcon, flag, smile, loseSmile, winSmile, incorrectMine;
	ImageIcon[] numbers;

	JButton reset;
	JTextField timeField;
	// JLabel numFlags;
	JMenuBar menuBar;
	JMenu difficulty;
	JMenuItem beginner, intermediate, expert, random;

	Timer timer;
	int timePassed = 0;

	int numMines, emptyBoxCount, numFlags;

	Font font;
	GraphicsEnvironment ge;

	boolean firstClick, gameOver;

	public Minesweeper(){
		try{
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\digital-7.ttf"));
			ge.registerFont(font);
		}catch(IOException|FontFormatException e){}

		smile = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\smile0.png");
		smile = new ImageIcon(smile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		loseSmile = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\dead0.png");
		loseSmile = new ImageIcon(loseSmile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		winSmile = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\win0.png");
		winSmile = new ImageIcon(winSmile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		// incorrectMine = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\wait0.png");
		// incorrectMine = new ImageIcon(incorrectMine.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

		reset = new JButton();
		reset.setFocusable(false);
		reset.addActionListener(this);
		reset.setIcon(smile);

		timeField = new JTextField();
		timeField.setFont(font.deriveFont(20f));
		timeField.setBackground(Color.BLACK);
		timeField.setForeground(Color.GREEN);
		timeField.setEditable(false);
		timeField.setText("  " + 0);


		menuBar = new JMenuBar();
		difficulty = new JMenu("Difficulty");
		beginner = new JMenuItem("Beginner");
		intermediate = new JMenuItem("Intermediate");
		expert = new JMenuItem("Expert");
		random = new JMenuItem("Random");

		difficulty.setFont(font.deriveFont(15f));
		beginner.setFont(font.deriveFont(15f));
		intermediate.setFont(font.deriveFont(15f));
		expert.setFont(font.deriveFont(15f));
		random.setFont(font.deriveFont(15f));

		difficulty.add(beginner);
		difficulty.add(intermediate);
		difficulty.add(expert);
		difficulty.add(random);


		beginner.addActionListener(this);
		intermediate.addActionListener(this);
		expert.addActionListener(this);
		random.addActionListener(this);

		menuBar.add(difficulty);
		menuBar.add(reset);
		menuBar.add(timeField);

		menuBar.setLayout(new GridLayout(1, 3));
		this.add(menuBar, BorderLayout.NORTH);


		mineIcon = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\mine0.png");
		mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

		flag = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

		numbers = new ImageIcon[8];
		for (int i = 0; i < 8; i++){
			numbers[i] = new ImageIcon("C:\\Users\\10018099\\Desktop\\Data Structures\\Minesweeper Images\\" + (i+1) + ".png");
			numbers[i] = new ImageIcon(numbers[i].getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		}

		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}catch(Exception e){}
		UIManager.put("ToggleButton.select", new Color(255, 128, 130));

		createBoard(9,9, 10);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void createBoard(int row,int col, int numMines){
		if (boardPanel != null)
			this.remove(boardPanel);

		board = new JToggleButton[row][col];
		firstClick = true;
		gameOver = false;
		this.numMines = numMines;
		numFlags = numMines;
		emptyBoxCount = row * col - numMines;
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(row, col));

		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				board[i][j]=new JToggleButton();
				board[i][j].putClientProperty("row", i);
				board[i][j].putClientProperty("col", j);
				board[i][j].putClientProperty("state", 0);
				board[i][j].setMargin(new Insets(0, 0, 0, 0));
				board[i][j].setFocusable(false);
				board[i][j].addMouseListener(this);
				boardPanel.add(board[i][j]);
			}
		}
		this.add(boardPanel,BorderLayout.CENTER);

		this.setSize(col*40, row*40);
		this.revalidate();
	}

	public void setMinesAndCounts(int row,int col){
		int count = 0;
		while (count < numMines){
			int randR = (int)(Math.random()*board.length);
			int randC = (int)(Math.random()*board[0].length);
			int state = (int)(board[randR][randC].getClientProperty("state"));
			if (state != 10 && Math.abs(randR-row) > 1 && Math.abs(randC-col) > 1){
				board[randR][randC].putClientProperty("state",10);
				for (int i = randR - 1; i <= randR + 1; i++){
					for (int j = randC - 1; j <= randC + 1; j++){
						try{
							state = (int)(board[i][j].getClientProperty("state"));
							if (state != 10){
								board[i][j].putClientProperty("state", state + 1);
							}
						}catch(ArrayIndexOutOfBoundsException e){}
					}
				}
				count++;
			}
		}
	}

	public void mouseReleased(MouseEvent e){
		int row = (int)(((JToggleButton)e.getComponent()).getClientProperty("row"));
		int col = (int)(((JToggleButton)e.getComponent()).getClientProperty("col"));
		if (!gameOver){
			if (e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled()){
				if (firstClick){
					timer = new Timer();
					timer.schedule(new UpdateTimer(), 0, 1000);

					setMinesAndCounts(row, col);
					firstClick=false;
				}
				int state = (int)(board[row][col].getClientProperty("state"));
				if (state == 10){
					board[row][col].setSelected(false);
					showMines();
					gameOver = true;
					timer.cancel();
					reset.setIcon(loseSmile);
					// JOptionPane.showMessageDialog(null, "You are a loser!");
				}else{
					board[row][col].setSelected(true);
					emptyBoxCount--;
					expand(row, col);
					checkWin();
				}
			}
			if (!firstClick && e.getButton() == MouseEvent.BUTTON3 && !board[row][col].isSelected()){
				if (board[row][col].isEnabled() && numFlags > 0){
					board[row][col].setIcon(flag);
					board[row][col].setDisabledIcon(flag);
					board[row][col].setEnabled(false);
					numFlags--;
				}else if (board[row][col].getIcon() == flag){
					board[row][col].setIcon(null);
					board[row][col].setDisabledIcon(null);
					board[row][col].setEnabled(true);
					numFlags++;
				}
			}
		}

	}

	public void showMines(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				board[i][j].setEnabled(false);
				int state = (int)(board[i][j].getClientProperty("state"));
				if (state == 10){
					board[i][j].setIcon(mineIcon);
					board[i][j].setDisabledIcon(mineIcon);
				}
			}
		}
	}

	public void checkWin(){
		if (emptyBoxCount == 0){
			gameOver = true;
            showMines();
            timer.cancel();
            reset.setIcon(winSmile);
            // JOptionPane.showMessageDialog(null, "You are a winner!");
		}
	}

	public void expand(int row, int col){
		if (!board[row][col].isSelected()){
			board[row][col].setSelected(true);
			emptyBoxCount--;
		}

		int state = (int)(board[row][col].getClientProperty("state"));
		if (state != 0){
			board[row][col].setIcon(numbers[state-1]);
			board[row][col].setDisabledIcon(numbers[state-1]);
		}else{
			for (int i = row - 1; i <= row + 1; i++){
				for (int j = col - 1; j <= col + 1; j++){
					try{
						if (!board[i][j].isSelected())
							expand(i, j);
					}catch(ArrayIndexOutOfBoundsException e){}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == beginner)
			createBoard(9, 9, 10);
		if (e.getSource() == intermediate)
			createBoard(16, 16, 40);
		if (e.getSource() == expert)
			createBoard(16, 30, 99);
		if (e.getSource() == random){
			int row = (int)(Math.random()*12)+9;
			int col = (int)(Math.random()*22)+9;
			int div = (int)(Math.random()*5)+6;
			createBoard(row, row, row*col/div);
		}
		if (e.getSource() == reset){
			timer.cancel();
			timePassed = 0;
			timeField.setText("  " + timePassed);
			reset.setIcon(smile);
			createBoard(board.length, board[0].length, numMines);
		}
	}

	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}


	public static void main(String[] args){
		new Minesweeper();
	}

	public class UpdateTimer extends TimerTask{
		public void run(){
			if (!gameOver){
				timePassed++;
				timeField.setText("    " + timePassed);
			}
		}
	}
}
