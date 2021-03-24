import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author William & Quinn
 *
 */
public class GamePanel extends JPanel{
	
	private int fps = 60;
	private int distance = 0;
	private int heroSpeed = 4;
	private String direction = "right";
	private Image backgroundImg;
	private InputListener listener;
	private boolean isRunning = true;
	private int scrolling;
	private Knight knight;
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	ArrayList<Structure> groundArr = new ArrayList<Structure>();

	public GamePanel() {
		
		//creating Knight
		Knight knight = new Knight(320, 575);
		this.knight = knight;
		spriteList.add(knight);
	
		//adding initial ground
		int xCor = -92;
		for(int i = 0; i < 20; i++) {
			Structure ground = new Structure(xCor, 580);
			groundArr.add(ground);
			xCor += 46;
			
		}
		
		
	}

	public void run() {
		//calls initialization to set up the frame
		initialize();
		int updates = 0;
		//runs while the game is going
		while(isRunning) {
			
			if(scrolling > 750) {
				scrolling = -200;
			}
			long time = System.currentTimeMillis();

			update(updates);
			repaint();

			time = (1000/fps) - (System.currentTimeMillis() - time);
			if(time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e){}
			}
			
			updates += 1;
			if (updates > 600) {
				updates = 0;
			}
		}
		//"closes" the window when the game stops running
		setVisible(false);
	}

	public void initialize(){
		//setting up all the stuffs
		ImageIcon background = new ImageIcon("background.png");
		backgroundImg = background.getImage();
		
		listener = new InputListener(this);
		repaint();
		
	}
	
	public void update(int updates) {

	
		
		if(listener.isKeyDown(KeyEvent.VK_RIGHT)) {
			distance += 2;	
			this.knight.setState("running");
			this.knight.setDirection("right");
			
			// moves the sprites over based on player movement
			for(int i = 0; i < groundArr.size(); i ++) {
				groundArr.get(i).updateX(-heroSpeed);
			}
			for (int i = 0; i < spriteList.size(); i++) {
				spriteList.get(i).updateX(-heroSpeed);
			}	
			
			// builds new ground
			if(groundArr.get(groundArr.size()-1).getX() < 874){
				int lastX = groundArr.get(groundArr.size()-1).getX();
				Structure ground = new Structure(lastX + 46, 580);
				groundArr.add(ground);
			}
		
		} 
		
		if(listener.isKeyDown(KeyEvent.VK_LEFT)) {
			distance -= 2;
			this.knight.setState("running");
			this.knight.setDirection("left");
			
			// moves the sprites over based on player movement
			for(int i = 0; i < groundArr.size(); i ++) {
				groundArr.get(i).updateX(heroSpeed);
			}
			for (int i = 0; i < spriteList.size(); i++) {
				spriteList.get(i).updateX(heroSpeed);
			}
				
			// builds new ground
			if(groundArr.get(0).getX() > -138) {
				int firstX = groundArr.get(0).getX();
				Structure ground = new Structure(firstX - 46, 580);
				groundArr.add(0, ground);
			}
		} 
		
		
		if(listener.isKeyDown(KeyEvent.VK_UP)) {
			System.out.println("UP");
		}
		if(listener.isKeyDown(KeyEvent.VK_DOWN)) {
			System.out.println("DOWN");
		}
		
		
		// Update all sprites/ground
		
		for (int i = 0; i < spriteList.size(); i++) {
			Sprite currentSprite = spriteList.get(i);
			//check for collisions here TODO
			
			//update non-structure sprites here
			currentSprite.update(updates / 6);
			//remove necessary non-structure sprites
			if (currentSprite.isRemove()) {
				spriteList.remove(i);
			}
		}	
		
		for (int j = 0; j < groundArr.size(); j++) {
			Sprite currentGround = groundArr.get(j);
			//check for collisions here TODO
			
			//update structure sprites here
			currentGround.update(updates / 6);
			//remove necessary non-structure sprites
			if (currentGround.isRemove()) {
				groundArr.remove(j);
			}
		}		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.fillRect(0, 0, 736, 758);
		g2.drawImage(backgroundImg, (int) -distance/8, 150, 400 , 300, null);
		//g.drawImage(backBuffer, 0, 0, this);
	

		// draw ground first
		for (int j = 0; j < groundArr.size(); j++) {
			groundArr.get(j).draw(g2);
		}
		// draw all other sprites
		for(int i = 0; i < spriteList.size(); i ++) {
			spriteList.get(i).draw(g2);
		}
	}
}
