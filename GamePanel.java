import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * @author William & Quinn
 *
 */
public class GamePanel extends JComponent {

  private int myTimerDelay;
	private Timer myTimer;
	private Image img;
	private int scrolling;
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	public GamePanel() {
		
		//working to add background and scroll it
		scrolling = 400;
		ImageIcon i = new ImageIcon("background.png");
		img = i.getImage();
		
		
		//timer stuff still kinda wack
		myTimerDelay = 10;
		TimerTask gameTimer = new TimerTask() {
			@Override
			public void run() {
				//replace to updastae sprites every whenever
				//System.out.println("test");
				repaint();
				scrolling += 50;
				//keep this but tweak 
				if(scrolling > 750) {
					scrolling = -200;
				}
				
			}
		};
		Timer timer = new Timer();
		timer.schedule(gameTimer, 1000, 1000);
		
		
		//Create Knight
		Knight knight = new Knight(250, 250);
		spriteList.add(knight);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(img, scrolling,100, null);
		
		// draw sprites
		for (int i = 0; i < spriteList.size(); i++) {
			spriteList.get(i).draw(graphics);
		}
	}
}
