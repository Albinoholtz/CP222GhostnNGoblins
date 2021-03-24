
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author William & Quinn
 *
 */
public class Zombie extends Enemy {
	
	BufferedImage[] spawnImg = new BufferedImage[3];
	BufferedImage[] walkImg = new BufferedImage[3];
	BufferedImage[] dyingImg = new BufferedImage[2];
	BufferedImage currentImage = null;
	String state = null;
	double spawnCount = -0.5;
	double dyingCount = -0.5;
	int velocity = 0;
	
	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Zombie(int _x, int _y) {
		super(_x, _y);
		
		BufferedImage spriteSheet = getImageFile("enemies.png"); // load file
		BufferedImage[] allImg = new BufferedImage[6]; // create list for cutting images


		for (int i = 0; i < 6; i++) { // cut images and add to master list
			if (i < 3) {
				allImg[i] = spriteSheet.getSubimage(i * 22, 66, 24, 32);
			} else {
			allImg[i] = spriteSheet.getSubimage((i * 25) - 6, 66, 24, 32); 
			}
		}
		
		for (int i = 0; i < allImg.length; i++) { // add images to proper lists
			if (i < 3) {
				walkImg[i] = allImg[i];
			} else {
				spawnImg[i - 3] = allImg[i];
			}
		}
		
		Image spriteSheetImg = (Image) getImageFile("knight.png");
		
		Color backPink = new Color(252,105,216,255);
		Color backBlue = new Color(117,146,252,255);
		Color backGreen = new Color(167,226,110,255);
		Color backCyan = new Color(95,205,228,255);
		
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backBlue);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backPink);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backGreen);
		spriteSheetImg = Transparency.makeColorTransparent(spriteSheetImg, backCyan);
		
		spriteSheet = toBufferedImage(spriteSheetImg);
		dyingImg[0] = spriteSheet.getSubimage(192, 160, 32, 32);
		dyingImg[1] = spriteSheet.getSubimage(224, 160, 32, 32);
		
		state = "spawing";
		currentImage = spawnImg[2];
		setImage(currentImage);

	}

	@Override
	public boolean collidedWith(Sprite other) {
		boolean collided = false;
		if (overlaps(other)) {
			if (other instanceof Weapon) { // After collision with weapon, die
				state = "dying";
			}
		}
		return collided;
	}

	@Override
	public void update(int time) {
		
		switch (state) {
		case "spawing":
			spawnCount += 0.1;
			
			if ((int) (spawnCount / 4) >= 3) {
				state = "walking";
				setImage(walkImg[time % 3]);
				if (getX() < 320) { // set starting direction
					velocity = 4;
				} else {
					velocity = -4;
				}
				break;
			}
			System.out.println(2 - (int) (spawnCount / 4));
			setImage(spawnImg[2 - (int) (spawnCount / 4)]);
			break;
		case "walking":
			setImage(walkImg[time % 3]);
			if (getX() < -10) { // switch direction
				velocity = 4;
			} else if (getX() > 680){ // switch direction
				velocity = -4;
			}
			
			updateX(velocity);
			break;
		case "dying":
			dyingCount += 0.1;
			
			if ((int) (dyingCount / 2) >= 2) {
				setRemove(true);
				break;
			}
			
			setImage(dyingImg[(int) (dyingCount / 2)]);
			break;
			
		}		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	void draw(Graphics2D g2) {
        
		
		// scales and draws the image by 2
		if(direction == "right") {
			g2.drawImage(getImage(), getX(), getY(), getImage().getWidth() * 3, getImage().getHeight() * 3, null);
		} else {
			g2.drawImage(getImage(), getX() + 66, getY(), -(getImage().getWidth() * 3), getImage().getHeight() * 3, null);
		} 
       
	}
	
}
