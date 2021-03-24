
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * @author William & Quinn
 *
 */
public class Weapon extends Sprite {
	String direction = null;
	String weaponType = "lance";
	boolean hit = false;
	double hitCount = -0.5;
	int velocity = 0;
	
	BufferedImage[] lanceImg = new BufferedImage[1];
	BufferedImage[] torchImg = new BufferedImage[2];
	BufferedImage[] daggerImg = new BufferedImage[1];
	BufferedImage[] crossImg = new BufferedImage[1];
	BufferedImage[] fireImg = new BufferedImage[8];
	BufferedImage[] hitImg = new BufferedImage[3];
	
	/**
	 * @param _x
	 * @param _y
	 */
	public Weapon(int _x, int _y, String _type, String _direction) {
		super(_x, _y);
		direction = _direction;
		weaponType = _type;
		
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
		
		lanceImg[0] = spriteSheet.getSubimage(32, 304, 32, 16);
		torchImg[0] = spriteSheet.getSubimage(0, 288, 16, 16);
		torchImg[1] = spriteSheet.getSubimage(16, 288, 16, 16);
		daggerImg[0] = spriteSheet.getSubimage(32, 288, 16, 16);
		crossImg[0] = spriteSheet.getSubimage(64, 288, 32, 32);
		// Fire animation
		fireImg[0] = spriteSheet.getSubimage(192, 160, 32, 32);
		fireImg[1] = spriteSheet.getSubimage(224, 160, 32, 32);
		fireImg[2] = spriteSheet.getSubimage(192, 160, 32, 32);
		fireImg[3] = spriteSheet.getSubimage(224, 160, 32, 32);
		fireImg[4] = spriteSheet.getSubimage(192, 160, 32, 32);
		fireImg[5] = spriteSheet.getSubimage(208, 160, 32, 32);
		fireImg[6] = spriteSheet.getSubimage(192, 160, 32, 32);
		fireImg[7] = spriteSheet.getSubimage(208, 160, 32, 32);
		// Weapon hit animation
		hitImg[0] = spriteSheet.getSubimage(224, 192, 16, 16);
		hitImg[1] = spriteSheet.getSubimage(240, 192, 16, 16);
		hitImg[2] = spriteSheet.getSubimage(224, 208, 16, 16);
		
		if (direction == "right") {
			velocity = 13;
		} else {
			velocity = -13;
		}
		
	}

	@Override
	public boolean collidedWith(Sprite other) {
		boolean collided = false;
		if (overlaps(other)) {
			if (other instanceof Enemy) { // After collision with enemy, hit & disappear
				hit = true;
			}
		}
		return collided;
	}

	@Override
	public void update(int time) {
		if(getX() < -92) {
			setRemove(true);
		} else if(getX() > 828) {
			setRemove(true);
		}
		
		switch (weaponType) {
		case "lance":
			if (hit) {
				hitCount += 0.2;
				if ((int) (hitCount) >= 3) {
					setRemove(true);
					break;
				}
				setImage(hitImg[(int) (hitCount)]);
				break;
			}
			setImage(lanceImg[0]);
			updateX(velocity);
			break;
		}
	}
	
	void draw(Graphics2D g2) {
		// scales and draws the image by 2
		if(direction == "right") {
			g2.drawImage(getImage(), getX(), getY(), getImage().getWidth() * 3, getImage().getHeight() * 3, null);
		} else {
			g2.drawImage(getImage(), getX() + 96, getY(), -(getImage().getWidth() * 3), getImage().getHeight() * 3, null);
		} 

	}
	
	@Override
	public Rectangle getHitbox() {
		Rectangle hitbox = new Rectangle();
		switch(weaponType) {
		default: // default is lance
			if (direction == "right") {
				hitbox.setSize(19, 6);
				hitbox.setLocation(getX() + 10, getY() + 10);
				break;
			} else {
				hitbox.setSize(19, 6);
				hitbox.setLocation(getX(), getY() + 10);
				break;	
			}
		}
		return hitbox;
	}
}
