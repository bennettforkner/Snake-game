package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKey implements KeyListener {
	
	private SnakeGame game;
	
	public ArrowKey(SnakeGame game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			game.changeDirection(1);
		} else if (code == KeyEvent.VK_DOWN) {
			game.changeDirection(3);
		} else if (code == KeyEvent.VK_LEFT) {
			game.changeDirection(4);
		} else if (code == KeyEvent.VK_RIGHT) {
			game.changeDirection(2);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

}
