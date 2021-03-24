
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
	
	private int jumpCount = 0;
	private int jumpVelocity = 0;
	private int fps = 60;
	private int distance = 0;
	private int heroSpeed = 4;
	private Image backgroundImg;
	private InputListener listener;
	private int cooldown = 0;
	private boolean isRunning = true;
	private boolean jumping = false;
	private boolean falling = false;
	private int scrolling;
	private Knight knight;
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	ArrayList<Structure> groundArr = new ArrayList<Structure>();

	public GamePanel() {
		
		//creating Knight
		Knight knight = new Knight(320, 575);
		this.knight = knight;
		spriteList.add(knight);
		
		//test zombie
		Zombie myMan = new Zombie(100, 575);
		spriteList.add(myMan);
	
		//adding initial ground
		int xCor = -92;
		for(int i = 0; i < 20; i++) {
			Structure ground = new Structure(xCor, 580);
			groundArr.add(ground);
			xCor += 46;
			
		}
		
		
	}
	
	public void checkCollisions(ArrayList<Sprite> spriteList) {
    	ArrayList<Sprite> removeIndex = new ArrayList<Sprite>();
    	
    	for (int i = 0; i < spriteList.size(); i++) {
    		for (int j = 0; j < spriteList.size(); j++) {
    			if (i != j) {
    				if (spriteList.get(i).collidedWith(spriteList.get(j))) {
    					removeIndex.add(spriteList.get(i));
    				}
    			}
    		}
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

		if(jumping && this.knight.getState() != "runJump") {
			this.knight.setState("stationaryJump");
		} else if (jumping) {
			this.knight.setState("runJump");
		} else {
			this.knight.setState("idle");
		}

		if(listener.isKeyDown(KeyEvent.VK_DOWN)) {
			this.knight.setState("crouch");
		}
		
		if(listener.isKeyDown(KeyEvent.VK_RIGHT)) {
			distance += 2;
			
			if(jumping) {
				this.knight.setState("runJump");
			} else {
				this.knight.setState("running");
			}
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
			if(jumping) {
				this.knight.setState("runJump");
			} else {
				this.knight.setState("running");
			}
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
			if(jumpCount == 0 && knight.getState() != "crouch" && !jumping ) {
				jumping = true;
				jumpCount = 40;
				jumpVelocity = -20;
			}
		}
		
		if (listener.isKeyDown(KeyEvent.VK_SPACE)) {
			if (cooldown == 0) {
				if (knight.getState() == "crouch") {
					Weapon weapon = new Weapon(320, knight.getY() + 10, "lance", knight.getDirection());
					spriteList.add(weapon);
				} else {
					Weapon weapon = new Weapon(320, knight.getY(), "lance", knight.getDirection());
					spriteList.add(weapon);
				}
				cooldown = 20;
				
			}
		}

		if (jumping) {
			if (jumpCount == 0) {
				knight.updateY(jumpVelocity);
				jumping = false;
			} else {
				jumpCount -= 1;
				knight.updateY(jumpVelocity);
				jumpVelocity += 1;
			}
		}
		
		// Update all sprites/ground

		for (int i = 0; i < spriteList.size(); i++) {
			Sprite currentSprite = spriteList.get(i);
			//update non-structure sprites here
			currentSprite.update(updates / 6);
			//remove necessary non-structure sprites
			if (currentSprite.isRemove()) {
				spriteList.remove(i);
			}
		}	

		for (int j = 0; j < groundArr.size(); j++) {
			Sprite currentGround = groundArr.get(j);
			//update structure sprites here
			currentGround.update(updates / 6);
			//remove necessary non-structure sprites
			if (currentGround.isRemove()) {
				groundArr.remove(j);
			}
		}
		if (cooldown != 0) {
			cooldown -= 1;
		}
		
		checkCollisions(spriteList);
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
