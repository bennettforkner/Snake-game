package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionListener implements ActionListener {
	
	private int direction;
	private SnakeGame game;

	public DirectionListener(char dir, SnakeGame g) {
		if (dir == '^') {
			this.direction = 1;
		} else if (dir == '>') {
			this.direction = 2;
		} else if (dir == 'v') {
			this.direction = 3;
		} else if (dir == '<') {
			this.direction = 4;
		}
		this.game = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.changeDirection(direction);
	}

}
