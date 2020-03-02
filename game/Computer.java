package game;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Computer {
	private SnakeGame game;
	private ActionListener north;
	private ActionListener east;
	private ActionListener south;
	private ActionListener west;
	private boolean[][] canMoveTo = new boolean[30][30];
	private boolean[][] food = new boolean[30][30];
	private int x;
	private int y;
	private int direction; //1 for n; 2 for e; 3 for s; 4 for w;
	public Computer (SnakeGame g, ArrayList<ActionListener> directions) {
		this.game = g;
		north = directions.remove(0);
		east = directions.remove(0);
		south = directions.remove(0);
		west = directions.remove(0);
		x = game.getX();
		y = game.getY();
		direction = game.getDirection();
	}
	
	public void play() {
		for (int r = 0; r < canMoveTo.length; r++) {
			for (int c = 0; c < canMoveTo[r].length; c++) {
				canMoveTo[r][c] = true;
			}
		}
		for (SnakePiece piece: game.pieces) {
			canMoveTo[piece.y][piece.x] = false;
		}
		for (int r = 0; r < canMoveTo.length; r++) {
			for (int c = 0; c < canMoveTo[r].length; c++) {
				
			}
		}
	}
}
