package game;
import java.awt.Color;

import javax.swing.BorderFactory;

public class SnakePiece {
	public int x;
	public int y;
	public int age;
	private SnakeFace face;
	private SnakeGame g;
	public SnakePiece(int x, int y, SnakeFace face, SnakeGame g) {
		this.g = g;
		this.face = face;
		this.x = x;
		this.y = y;
		age = 0;
		g.board[y][x] = true;
	}
	
	public void checkAge(int maxAge) {
		/*int colorGreen = 255 - (200 - (age));
		if (colorGreen < 0) {
			colorGreen = 0;
		} else if (colorGreen > 255) {
			colorGreen = 255;
		}*/
			
		if (this.age >= maxAge - 1) {
			face.screen[y][x].setBackground(new Color(255, 255, 255));
			face.screen[y][x].setBorder(BorderFactory.createLineBorder(new Color(245,245,245),1));
			g.board[y][x] = false;
			g.pieces.remove(this);
		}
		else {
			this.age++;
		}
	}
}
