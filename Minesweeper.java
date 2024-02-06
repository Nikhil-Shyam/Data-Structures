import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeper extends JFrame implements ActionListener, MouseListener{
	JToggleButton[][] board;
	JPanel boardPanel;
	boolean firstClick;

	ImageIcon mineIcon, flag;
	ImageIcon[] numbers;

	int numMines;
	int emptyBoxCount;
	Font font = new Font("Arial",Font.PLAIN,9);
	boolean gameOver = false;

	public Minesweeper(){
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

		createBoard(9,9);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void createBoard(int row,int col){
		if (boardPanel != null)
			this.remove(boardPanel);

		board = new JToggleButton[row][col];
		firstClick = true;
		numMines = 10;
		emptyBoxCount = row * col - numMines;
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(row, col));

		//make a bunch of JToggleButtons and store them in your array!
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



	public void actionPerformed(ActionEvent e){

	}

	public void setMinesAndCounts(int row,int col){
		//you know what to do....
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
		/*for(int r=0;r<board.length;r++)
		{
			for(int c=0;c<board[0].length;c++)
			{
				String st=""+board[r][c].getClientProperty("state");
				board[r][c].setText(st);
			}
		}
*/

	}

	public void mouseReleased(MouseEvent e){
		int row = (int)(((JToggleButton)e.getComponent()).getClientProperty("row"));
		int col = (int)(((JToggleButton)e.getComponent()).getClientProperty("col"));
		if (!gameOver){
			if (e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled()){
				if (board[row][col].getIcon() == flag) // flag stuff
					board[row][col].setIcon(null);
				if (firstClick){
					setMinesAndCounts(row, col);
					firstClick=false;
				}
				int state = (int)(board[row][col].getClientProperty("state"));
				if (state == 10){
					gameOver = true;
					JOptionPane.showMessageDialog(null, "You are a loser!");
					// show all the mines
					showMines();
				}else{
					//magic happens
					board[row][col].setSelected(true);
					emptyBoxCount--;
					expand(row, col);
					checkWin();
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3){ // flag stuff
				if (!board[row][col].isSelected()){
					if (board[row][col].getIcon() == null){
						board[row][col].setIcon(flag);
						board[row][col].setDisabledIcon(flag);
					}else if (board[row][col].getIcon() == flag){
						board[row][col].setIcon(null);
						board[row][col].setDisabledIcon(null);
					}
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
			JOptionPane.showMessageDialog(null, "You are a winner!");
		}
	}

	public void expand(int row, int col){
		if (!board[row][col].isSelected()){
			board[row][col].setSelected(true);
			emptyBoxCount--;
		}

		int state = (int)(board[row][col].getClientProperty("state"));
		if (state != 0){
			// board[row][col].setText(state + "");
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

	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}


	public static void main(String[] args){
		new Minesweeper();
	}
}