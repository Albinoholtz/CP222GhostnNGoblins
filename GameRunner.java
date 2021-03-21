package GhostAndGoblins;

import javax.swing.JFrame;

/**
 * @author William & Quinn
 * This class creates and runs a instance of the game ghosts and goblins.
 */
public class GameRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameRunner game = new GameRunner(); // creates game instance
		game.run(); // runs game

	}
	
	/**
	 * runs the game ghosts and goblins
	 */
	public void run() { 
		JFrame mainWin = new JFrame("Ghosts n' Goblins"); // creates window
		
		mainWin.setSize(750, 522); // sizes window
		
		GamePanel mainPanel = new GamePanel(); // creates game component
		InputListener listener = new InputListener(mainPanel); // creates game listener
		
		mainWin.add(mainPanel); // adds game component to main window
		
		mainWin.setVisible(true); // shows game
	}

}
