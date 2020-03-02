package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {
	
	private SnakeGame g;
	
	public ClearListener(SnakeGame g) {
		this.g= g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		g.clearGame();

	}

}
