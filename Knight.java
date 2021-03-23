import java.awt.Color;
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
	BufferedImage[] crouchImg = new BufferedImage[2];
	BufferedImage[] crouchThrowImg = new BufferedImage[4];
	String state = null;
	boolean clothed = true;
	
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
				allImg[(8 * i) + j] = spriteSheet.getSubimage(j * 32, i * 32, 32, 32);
			}
		}
		
		for (int i = 0; i < 4; i++) { // Adds images to run images array
			runImg[i] = allImg[i];
		}
		for (int i = 4; i < 8; i++) { // Adds images to run images array
			runImg[i] = allImg[i +16];
		}
		
		// Adds images to idle images array
		idleImg[0] = allImg[4];
		idleImg[1] = allImg[20];
		
		
		currentImage = idleImg[0];
		setImage(currentImage);
		state = "running";
	}

	
	public void update(int time) {
		switch (state) {
		case "idle":
			setImage(idleImg[0]);
			break;
		case "running":
			setImage(runImg[Math.abs(-3 + ((int) (time)) % 6)]);
			break;
		}
	}
	
	public void move() {
		
	}
	
	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void updateX(int delta) {
		return;
	}
	
	@Override
	public void updateY(int delta) {
		return;
	}

}
