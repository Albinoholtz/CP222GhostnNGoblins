import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author William & Quinn
 * This class create a structure in the world (wall or platform)
 */
public class Structure extends Sprite {
	
	BufferedImage currentImage;
	BufferedImage[] groundImgs = new BufferedImage[3];
	boolean remove = false;

	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Structure(int _x, int _y) {
		super(_x, _y);
		
		Image groundOne = (Image) getImageFile("foregroundOne.png");
		Image groundTwo = (Image) getImageFile("foregroundTwo.png");
		Image groundThree = (Image) getImageFile("foregroundThree.png");
		
		BufferedImage imageOne = toBufferedImage(groundOne);
		BufferedImage imageTwo = toBufferedImage(groundTwo);
		BufferedImage imageThree = toBufferedImage(groundThree);
		
		groundImgs[0] = imageOne;
		groundImgs[1] = imageTwo;
		groundImgs[2] = imageThree;
		
		int max = 10;
		int min = 0;
		int random_int = (int) (Math.random() * (max - min + 1) + min);
		
		if(random_int >= 8) {
			this.currentImage = groundImgs[0];
		} else if (random_int >= 6 && random_int < 8) {
			this.currentImage = groundImgs[2];
		} else {
			this.currentImage = groundImgs[1];
		}
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		 g2.drawImage(currentImage, getX(), getY(), 46, 180, null);
	}

	@Override
	public void update() {
		if(getX() < -92) {
			remove = true;
		}
		if(getX() > 828) {
			remove = true;
		}

	}
	
	public boolean getRemove() {
		return remove;
	}

}
