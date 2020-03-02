package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakeFace {
	private static final int WINDOW_Y_POSITION = 0;
	private static final int WINDOW_X_POSITION = 0;
	private static final int WINDOW_HEIGHT = 600;
	private static final int WINDOW_WIDTH = 550;
	private JFrame window = new JFrame();
	public JPanel buttons = new JPanel();
	public JPanel[][] screen;
	public HashMap<String, JButton> buttonMap = new HashMap<>();
	public JPanel screenPanel = new JPanel();
	private Container contentPane = window.getContentPane();
	private JLabel c = new JLabel(" GAME OVER ");
	private long score = 0;
	private JLabel scoreBoard = new JLabel(" SCORE: " + score);
	private long time = 0;
	private JLabel timeBoard = new JLabel(" TIME: " + time);
	
	public SnakeFace() {
		SnakeGame g = new SnakeGame(this);
		timeBoard.setBackground(new Color(230,230,230));
		scoreBoard.setBackground(new Color(230,230,230));
		timeBoard.setOpaque(true);
		scoreBoard.setOpaque(true);
		timeBoard.setBorder(BorderFactory.createLineBorder(new Color(220,220,220),2,true));
		scoreBoard.setBorder(BorderFactory.createLineBorder(new Color(220,220,220),2,true));
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocation(WINDOW_X_POSITION, WINDOW_Y_POSITION);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setBackground(new Color(250, 248, 239));
        
        
        contentPane.setLayout(new BorderLayout());
        buttons.setLayout(new GridLayout(3,3));
        String[] butts = {"T", "^", "S", "<", "RESET", ">", ".", "v", "."};
        for (int i = 0; i < 9; i++) {
        	if (butts[i].compareTo(".") != 0) {
        		if (butts[i].compareTo("S") == 0) {
        			buttons.add(scoreBoard);
        		} else if (butts[i].compareTo("T") == 0) {
        			buttons.add(timeBoard);
        		}
        		else {
		        	JButton button = new JButton(butts[i]);
		        	button.setVisible(true);
		        	button.setOpaque(true);
		        	buttons.add(button);
		        	buttonMap.put(butts[i],button);
        		}
        	}
        	else
        		buttons.add(new JLabel());
        }
        //buttons.setSize(new Dimension(10,5));
        buttons.setOpaque(true);
        buttons.setVisible(true);
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(buttons);
        
        screenPanel.setLayout(new GridLayout(30, 30));
        screenPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2,true));
        //screenPanel.setBorder(BorderFactory.createBevelBorder(1));
        screen = new JPanel[30][30];
        for (int i = 0; i < screen.length; i++) {
        	 for (int k = 0; k < screen[i].length; k++) {
	            screen[i][k] = new JPanel();
	            screen[i][k].setFont(new Font("Foop", 20, 13));
	            screen[i][k].setOpaque(true);
	            
	            screen[i][k].setSize(10,10);
	            
	            screen[i][k].setBackground(new Color(255, 255, 255));
	            screen[i][k].setBorder(BorderFactory.createLineBorder(new Color(245,245,245),1));
	            screenPanel.add(screen[i][k]);
        	 }
        }
        screenPanel.setSize(900, 900);
        JPanel top = new JPanel();
        top.add(screenPanel);
        top.setSize(900, 900);
        
        contentPane.add(bottom,BorderLayout.SOUTH);
        contentPane.add(top,BorderLayout.NORTH);
        window.addKeyListener(new ArrowKey(g));
        
        JPanel c2 = new JPanel();
        c2.setLayout(new FlowLayout());
		c.setFont(new Font("Foop",20,35));
		c.setVisible(false);
		c2.add(c);
		c.setForeground(new Color(255,0,0));
		c.setOpaque(true);
		c.setBackground(new Color(255,220,220));
		c.setBorder(BorderFactory.createBevelBorder(1));
		contentPane.add(c2,BorderLayout.CENTER);
        g.play();
	}
	
	public void printGameOver() {
		c.setVisible(true);
	}
	
	public void clearGameOver() {
		c.setVisible(false);
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
		scoreBoard.setText(" SCORE: " + score);
	}

	public void setTime(long time) {
		this.time  = time;
		timeBoard.setText(" TIME: " + (time / 4));
		
	}
}
