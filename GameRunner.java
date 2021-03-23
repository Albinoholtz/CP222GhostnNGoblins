import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		GamePanel game = new GamePanel();
		game.run(); // runs game

	}
}
