import java.awt.Color;

import javax.swing.JFrame;

/**
 * @author William & Quinn
 * This class creates and runs a instance of the game ghosts and goblins.
 */
public class GameRunner {

	private static int frameWidth = 736;
	private static int frameHeight = 758;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameRunner game = new GameRunner();
		game.run();
		
	
	}

	private void run() {
		JFrame mainWin = new JFrame("Ghosts N' Goblins");
		
		mainWin.setSize(736, 758);
		
		GamePanel gamePanel = new GamePanel();

		
		mainWin.add(gamePanel);
		mainWin.getContentPane().setBackground(Color.black);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setResizable(false);
		mainWin.setVisible(true);
		gamePanel.run();
		
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
