/**
 * 
 */
package GhostAndGoblins;

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
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(img, scrolling,100, null);
	}
	
  
}
