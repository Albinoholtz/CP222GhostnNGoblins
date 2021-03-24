
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		GamePanel gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
	
		mainWin.add(gamePanel);
		
		mainWin.setSize(736, 758);
		mainWin.getContentPane().setBackground(Color.black);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setResizable(false);
		mainWin.setVisible(true);
		
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel.start();
	}
	
	public class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
}
