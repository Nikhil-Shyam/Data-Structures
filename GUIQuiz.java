import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUIQuiz extends JFrame implements ActionListener{
	JFrame frame;
	JPanel buttonPanel, bigPanel;
	JButton north, south, east, west, reset;
	JMenuBar menuBar;
	JMenu fontBar, fontSizeBar, textColorBar, textBackgroundColorBar, buttonOutlineColorBar;

	String[] fonts = {"Arial", "Times New Roman", "Comic Sans"};
	int[] fontSizes = {12, 24, 36};
	String[] textColors = {"Red", "Blue", "Random"};
	String[] textBackgroundColors = {"Red", "Blue", "Random"};
	String[] outlineColors = {"None", "Red", "Blue", "Random"};

	JMenuItem[] fontOptions = new JMenuItem[fonts.length];
	JMenuItem[] fontSizeOptions = new JMenuItem[fontSizes.length];
	JMenuItem[] textColorOptions = new JMenuItem[textColors.length];
	JMenuItem[] textBackgroundColorOptions = new JMenuItem[textBackgroundColors.length];
	JMenuItem[] outlineColorOptions = new JMenuItem[outlineColors.length];

	JTextArea textArea;

	float size;

	Font font;
	Font[] possibleFonts = new Font[fonts.length];


	public GUIQuiz(){
		frame = new JFrame();
		buttonPanel = new JPanel();
		bigPanel = new JPanel();
		menuBar = new JMenuBar();

		textArea = new JTextArea();

		fontBar = new JMenu("Fonts");
		fontSizeBar = new JMenu("Font Size");
		textColorBar = new JMenu("Text Colors");
		textBackgroundColorBar = new JMenu("Text Background Colors");
		buttonOutlineColorBar = new JMenu("Button Outline Colors");

		north = new JButton("North");
		south = new JButton("South");
		east = new JButton("East");
		west = new JButton("West");
		reset = new JButton("Reset");

		for (int i = 0; i < possibleFonts.length; i++)
			possibleFonts[i] = new Font(fonts[i], Font.PLAIN, fontSizes[0]);
		font = possibleFonts[0];

		for (int i = 0; i < fontOptions.length; i++){
			fontOptions[i] = new JMenuItem(fonts[i]);
			fontOptions[i].setFont(possibleFonts[i]);
			fontOptions[i].addActionListener(this);
			fontOptions[i].setBorder(new LineBorder(null));
			fontBar.add(fontOptions[i]);
		}

		for (int i = 0; i < fontSizeOptions.length; i++){
			fontSizeOptions[i] = new JMenuItem(fontSizes[i] + "");
			fontSizeOptions[i].setFont(font);
			fontSizeOptions[i].addActionListener(this);
			fontSizeOptions[i].setBorder(new LineBorder(null));
			fontSizeBar.add(fontSizeOptions[i]);
		}
		for (int i = 0; i < textColorOptions.length; i++){
			textColorOptions[i] = new JMenuItem(textColors[i]);
			textColorOptions[i].setFont(font);
			textColorOptions[i].addActionListener(this);
			textColorOptions[i].setBorder(new LineBorder(null));
			textColorBar.add(textColorOptions[i]);
		}
		for (int i = 0; i < textBackgroundColorOptions.length; i++){
			textBackgroundColorOptions[i] = new JMenuItem(textBackgroundColors[i]);
			textBackgroundColorOptions[i].setFont(font);
			textBackgroundColorOptions[i].addActionListener(this);
			textBackgroundColorOptions[i].setBorder(new LineBorder(null));
			textBackgroundColorBar.add(textBackgroundColorOptions[i]);

		}
		for (int i = 0; i < outlineColorOptions.length; i++){
			outlineColorOptions[i] = new JMenuItem(outlineColors[i]);
			outlineColorOptions[i].setFont(font);
			outlineColorOptions[i].addActionListener(this);
			outlineColorOptions[i].setBorder(new LineBorder(null));
			buttonOutlineColorBar.add(outlineColorOptions[i]);
		}

		north.addActionListener(this);
		south.addActionListener(this);
		east.addActionListener(this);
		west.addActionListener(this);
		reset.addActionListener(this);

		north.setFont(font);
		south.setFont(font);
		east.setFont(font);
		west.setFont(font);
		reset.setFont(font);
		fontBar.setFont(font);
		fontSizeBar.setFont(font);
		textColorBar.setFont(font);
		textBackgroundColorBar.setFont(font);
		buttonOutlineColorBar.setFont(font);

		north.setBorder(new LineBorder(null));
		south.setBorder(new LineBorder(null));
		east.setBorder(new LineBorder(null));
		west.setBorder(new LineBorder(null));
		reset.setBorder(new LineBorder(null));
		fontBar.setBorder(new LineBorder(null));
		fontSizeBar.setBorder(new LineBorder(null));
		textColorBar.setBorder(new LineBorder(null));
		textBackgroundColorBar.setBorder(new LineBorder(null));
		buttonOutlineColorBar.setBorder(new LineBorder(null));

		textArea.setFont(font);
		textArea.setBackground(null);

		menuBar.add(fontBar);
		menuBar.add(fontSizeBar);
		menuBar.add(textColorBar);
		menuBar.add(textBackgroundColorBar);
		menuBar.add(buttonOutlineColorBar);
		menuBar.add(reset);

		buttonPanel.add(north);
		buttonPanel.add(south);
		buttonPanel.add(east);
		buttonPanel.add(west);

		bigPanel.add(buttonPanel);
		bigPanel.add(menuBar);

		menuBar.setLayout(new GridLayout(1, 6));
		buttonPanel.setLayout(new GridLayout(1, 4));
		bigPanel.setLayout(new GridLayout(1, 2));


		frame.add(bigPanel, BorderLayout.NORTH);
		frame.add(textArea, BorderLayout.CENTER);
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == fontOptions[0])
			changeFonts(possibleFonts[0]);
		if (e.getSource() == fontOptions[1])
			changeFonts(possibleFonts[1]);
		if (e.getSource() == fontOptions[2])
			changeFonts(possibleFonts[2]);
		if (e.getSource() == north)
			setNorth();
		if (e.getSource() == south)
			setSouth();
		if (e.getSource() == east)
			setEast();
		if (e.getSource() == west)
			setWest();
		if (e.getSource() == fontSizeOptions[0])
			changeFontSize(fontSizes[0]);
		if (e.getSource() == fontSizeOptions[1])
			changeFontSize(fontSizes[1]);
		if (e.getSource() == fontSizeOptions[2])
			changeFontSize(fontSizes[2]);
		if (e.getSource() == textColorOptions[0])
			changeTextColor(textColors[0]);
		if (e.getSource() == textColorOptions[1])
			changeTextColor(textColors[1]);
		if (e.getSource() == textColorOptions[2])
			changeTextColor(textColors[2]);
		if (e.getSource() == textBackgroundColorOptions[0])
			changeBackgroundTextColor(textBackgroundColors[0]);
		if (e.getSource() == textBackgroundColorOptions[1])
			changeBackgroundTextColor(textBackgroundColors[1]);
		if (e.getSource() == textBackgroundColorOptions[2])
			changeBackgroundTextColor(textBackgroundColors[2]);
		if (e.getSource() == outlineColorOptions[0])
			changeOutlineColor(outlineColors[0]);
		if (e.getSource() == outlineColorOptions[1])
			changeOutlineColor(outlineColors[1]);
		if (e.getSource() == outlineColorOptions[2])
			changeOutlineColor(outlineColors[2]);
		if (e.getSource() == outlineColorOptions[3])
			changeOutlineColor(outlineColors[3]);
		if (e.getSource() == reset)
			reset();
	}

	public void reset(){
		setNorth();
		changeFonts(possibleFonts[0]);
		changeFontSize(fontSizes[0]);
		changeTextColor("");
		changeBackgroundTextColor("");
		changeOutlineColor("");
		textArea.setText(null);
	}

	public void changeOutlineColor(String color){
		Color c = Color.BLACK;
		if (color.equals(outlineColors[0]))
			c = Color.BLACK;
		if (color.equals(outlineColors[1]))
			c = Color.RED;
		if (color.equals(outlineColors[2]))
			c = Color.BLUE;
		if (color.equals(outlineColors[3]))
			c = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

		north.setBorder(new LineBorder(c));
		south.setBorder(new LineBorder(c));
		east.setBorder(new LineBorder(c));
		west.setBorder(new LineBorder(c));
		reset.setBorder(new LineBorder(c));
		fontBar.setBorder(new LineBorder(c));
		fontSizeBar.setBorder(new LineBorder(c));
		textColorBar.setBorder(new LineBorder(c));
		textBackgroundColorBar.setBorder(new LineBorder(c));
		buttonOutlineColorBar.setBorder(new LineBorder(c));
		for (int i = 0; i < fontSizeOptions.length; i++)
			fontSizeOptions[i].setBorder(new LineBorder(c));
		for (int i = 0; i < textColorOptions.length; i++)
			textColorOptions[i].setBorder(new LineBorder(c));
		for (int i = 0; i < textBackgroundColorOptions.length; i++)
			textBackgroundColorOptions[i].setBorder(new LineBorder(c));
		for (int i = 0; i < outlineColorOptions.length; i++)
			outlineColorOptions[i].setBorder(new LineBorder(c));
	}

	public void changeBackgroundTextColor(String color){
		Color c = null;
		if (color.equals(textBackgroundColors[0]))
			c = Color.RED;
		if (color.equals(textBackgroundColors[1]))
			c = Color.BLUE;
		if (color.equals(textBackgroundColors[2]))
			c = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

		/*
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		reset.setBackground(c);
		fontBar.setBackground(c);
		fontSizeBar.setBackground(c);
		textColorBar.setBackground(c);
		textBackgroundColorBar.setBackground(c);
		buttonOutlineColorBar.setBackground(c);
		for (int i = 0; i < fontSizeOptions.length; i++)
			fontSizeOptions[i].setBackground(c);
		for (int i = 0; i < textColorOptions.length; i++)
			textColorOptions[i].setBackground(c);
		for (int i = 0; i < textBackgroundColorOptions.length; i++)
			textBackgroundColorOptions[i].setBackground(c);
		for (int i = 0; i < outlineColorOptions.length; i++)
			outlineColorOptions[i].setBackground(c);
		*/
		textArea.setBackground(c);
	}

	public void changeTextColor(String color){
		Color c = Color.BLACK;
		if (color.equals(textColors[0]))
			c = Color.RED;
		if (color.equals(textColors[1]))
			c = Color.BLUE;
		if (color.equals(textColors[2]))
			c = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

		north.setForeground(c);
		south.setForeground(c);
		east.setForeground(c);
		west.setForeground(c);
		reset.setForeground(c);
		fontBar.setForeground(c);
		fontSizeBar.setForeground(c);
		textColorBar.setForeground(c);
		textBackgroundColorBar.setForeground(c);
		buttonOutlineColorBar.setForeground(c);
		for (int i = 0; i < fontSizeOptions.length; i++)
			fontSizeOptions[i].setForeground(c);
		for (int i = 0; i < textColorOptions.length; i++)
			textColorOptions[i].setForeground(c);
		for (int i = 0; i < textBackgroundColorOptions.length; i++)
			textBackgroundColorOptions[i].setForeground(c);
		for (int i = 0; i < outlineColorOptions.length; i++)
			outlineColorOptions[i].setForeground(c);
		textArea.setForeground(c);
	}

	public void changeFontSize(int s){
		this.size = (float) s;
		/*
		north.setFont(font.deriveFont(s));
		south.setFont(font.deriveFont(s));
		east.setFont(font.deriveFont(s));
		west.setFont(font.deriveFont(s));
		reset.setFont(font.deriveFont(s));
		fontBar.setFont(font.deriveFont(s));
		fontSizeBar.setFont(font.deriveFont(s));
		textColorBar.setFont(font.deriveFont(s));
		textBackgroundColorBar.setFont(font.deriveFont(s));
		buttonOutlineColorBar.setFont(font.deriveFont(s));
		for (int i = 0; i < fontSizeOptions.length; i++)
			fontSizeOptions[i].setFont(font.deriveFont(s));
		for (int i = 0; i < textColorOptions.length; i++)
			textColorOptions[i].setFont(font.deriveFont(s));
		for (int i = 0; i < textBackgroundColorOptions.length; i++)
			textBackgroundColorOptions[i].setFont(font.deriveFont(s));
		for (int i = 0; i < outlineColorOptions.length; i++)
			outlineColorOptions[i].setFont(font.deriveFont(s));
		*/
		textArea.setFont(font.deriveFont(size));
	}

	public void setNorth(){
		frame.remove(bigPanel);
		buttonPanel.setLayout(new GridLayout(1, 4));
		menuBar.setLayout(new GridLayout(1, 6));
		bigPanel.setLayout(new GridLayout(1, 2));
		frame.add(bigPanel, BorderLayout.NORTH);
		frame.revalidate();
	}

	public void setSouth(){
		frame.remove(bigPanel);
		buttonPanel.setLayout(new GridLayout(1, 4));
		menuBar.setLayout(new GridLayout(1, 6));
		bigPanel.setLayout(new GridLayout(1, 2));
		frame.add(bigPanel, BorderLayout.SOUTH);
		frame.revalidate();
	}

	public void setEast(){
		frame.remove(bigPanel);
		buttonPanel.setLayout(new GridLayout(4, 1));
		menuBar.setLayout(new GridLayout(6, 1));
		bigPanel.setLayout(new GridLayout(2, 1));
		frame.add(bigPanel, BorderLayout.EAST);
		frame.revalidate();
	}

	public void setWest(){
		frame.remove(bigPanel);
		buttonPanel.setLayout(new GridLayout(4, 1));
		menuBar.setLayout(new GridLayout(6, 1));
		bigPanel.setLayout(new GridLayout(2, 1));
		frame.add(bigPanel, BorderLayout.WEST);
		frame.revalidate();
	}

	public void changeFonts(Font font){
		this.font = font;
		north.setFont(font);
		south.setFont(font);
		east.setFont(font);
		west.setFont(font);
		reset.setFont(font);
		fontBar.setFont(font);
		fontSizeBar.setFont(font);
		textColorBar.setFont(font);
		textBackgroundColorBar.setFont(font);
		buttonOutlineColorBar.setFont(font);
		for (int i = 0; i < fontSizeOptions.length; i++)
			fontSizeOptions[i].setFont(font);
		for (int i = 0; i < textColorOptions.length; i++)
			textColorOptions[i].setFont(font);
		for (int i = 0; i < textBackgroundColorOptions.length; i++)
			textBackgroundColorOptions[i].setFont(font);
		for (int i = 0; i < outlineColorOptions.length; i++)
			outlineColorOptions[i].setFont(font);
		textArea.setFont(font);
		textArea.setFont(font.deriveFont(size));
	}

	public static void main(String[] args){
		new GUIQuiz();
	}
}
