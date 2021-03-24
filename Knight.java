import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author William & Quinn
 * This is the class for the player character. This player 
 * should be able to move using keyboard inputs.
 */
public class Knight extends Sprite {

	BufferedImage currentImage = null;
	BufferedImage[] runImg = new BufferedImage[8];
	BufferedImage[] idleImg = new BufferedImage[2];
	BufferedImage[] jumpImg = new BufferedImage[4];
	BufferedImage[] throwImg = new BufferedImage[4];
	BufferedImage[] dyingImg = new BufferedImage[4];
	BufferedImage[] crouchImg = new BufferedImage[2];
	BufferedImage[] crouchThrowImg = new BufferedImage[4];
	int coolDown = 20;
	boolean gameOver = false;
	int dyingCount = 4;
	String state = null;
	String direction = null;
	boolean armored = true;
	boolean hit = false;
	boolean dead = false;
	boolean dying = false;
	
	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Knight(int _x, int _y) {
		super(_x, _y);
		
		Image spriteSheetImg = (Image) getImageFile("knight.png");
		
		Color backPink = new Color(252,105,216,255);
		Color backBlue = new Color(117,146,252,255);
		Color backGreen = new Color(167,226,110,255);
		Color backCyan = new Color(95,205,228,255);
		
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backBlue);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backPink);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backGreen);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backCyan);
		
		
		
		BufferedImage spriteSheet = toBufferedImage(spriteSheetImg);
		
		BufferedImage[] allImg = new BufferedImage[80];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 8; j++) {
				if((8 * i) + j == 37){
					allImg[(8 * i) + j] = spriteSheet.getSubimage(j * 32, i * 32, 32, 16);
				} else {
					allImg[(8 * i) + j] = spriteSheet.getSubimage(j * 32, i * 32, 32, 32);
				}
				
			}
		}
		
		for (int i = 0; i < 4; i++) { // Adds images to run images array
			runImg[i] = allImg[i];
		}
		for (int i = 4; i < 8; i++) { // Adds images to run images array
			runImg[i] = allImg[i + 12];
		}
		
		// Adds images to idle images array
		idleImg[0] = allImg[4];
		idleImg[1] = allImg[20];
		jumpImg[0] = allImg[5]; //armored run jump
		jumpImg[1] = allImg[6]; //armored stationary jump
		jumpImg[2] = allImg[21]; //naked run jump
		jumpImg[3] = allImg[22]; //naked stationary jump
		
		crouchImg[0] = allImg[7]; // armored crouch
		crouchImg[1] = allImg[23]; // naked crouch
		
		dyingImg[0] = allImg[37];
		dyingImg[1] = allImg[36];
		dyingImg[2] = allImg[35];
		dyingImg[3] = allImg[34];
		
		
		
		currentImage = idleImg[0];
		setImage(currentImage);
		state = "idle";
		direction = "right";
	}
	
	public void death() {
		dyingCount += 0.2;
		setImage(dyingImg[3]);
		dead = true;
		
	}
	
	public boolean getDead() {
		return this.dying;
	}

	
	public void update(int time) {
		System.out.println();
		
		if(armored == false) {
			if(coolDown > 0) {
				coolDown -= 1;
			}
			
		}
		
		switch (state) {
		case "idle":
			if(armored) {
				setImage(idleImg[0]);
			} else {
				setImage(idleImg[1]);
			}
			break;
			
		case "running":
			if(armored) {
				setImage(runImg[Math.abs(-3 + ((int) (time)) % 6)]);
			} else {
				setImage(runImg[Math.abs(-3 + ((int) (time)) % 6) + 4]);
			}
			
			break;
			
		case "runJump":
			if(armored) {
				setImage(jumpImg[0]);
			} else {
				setImage(jumpImg[2]);
			}
			break;
			
		case "stationaryJump":
			if(armored) {
				setImage(jumpImg[1]);
			} else {
				setImage(jumpImg[3]);
			}
			break;
			
		case "crouch":
			if(armored) {
				setImage(crouchImg[0]);
			} else {
				setImage(crouchImg[1]);
			}
			break;
	
		}
		
	}
	
	
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	void draw(Graphics2D g2) {
		if(dead) {
			g2.drawImage(getImage(), getX(), 575, getImage().getWidth() * 3, getImage().getHeight() * 3, null);
		}
        // scales and draws the image by 2
		if(this.direction == "right") {
			g2.drawImage(getImage(), getX(), getY(), getImage().getWidth() * 3, getImage().getHeight() * 3, null);
		} 
		if(this.direction == "left"){
			
			g2.drawImage(getImage(), getX() + 96, getY(), -(getImage().getWidth() * 3), getImage().getHeight() * 3, null);
		} 
       
	}
	
	@Override
	public boolean collidedWith(Sprite other){
		boolean collided = false;
		if (overlaps(other)) {
			if (other instanceof Enemy && ((Zombie) other).getState() == "walking") { // After collision with enemy, hit & disappear
				if(armored == true) {
					armored = false;
				} else if(armored == false && coolDown == 0){
					gameOver = true;
				}
			}
		}
		return collided;
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	@Override
	public void updateX(int delta) {
		return;
	}
	
	public String getState() {
		return this.state;
	}


	public String getDirection() {
		return direction;
	}


	@Override
	public Rectangle getHitbox() {
		Rectangle hitbox = new Rectangle();
		switch(state) {
		case "crouch":
			hitbox.setSize(22, 22);
			hitbox.setLocation(getX() + 5, getY() + 10);
			break;
		default:
			hitbox.setSize(22, 32);
			hitbox.setLocation(getX() + 5, getY());
			break;
		}
		return hitbox;
	}
}
