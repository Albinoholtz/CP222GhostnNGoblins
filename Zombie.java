import java.awt.image.BufferedImage;

/**
 * @author William & Quinn
 *
 */
public class Zombie extends Enemy {
	
	BufferedImage[] spawnImg = new BufferedImage[3];
	BufferedImage[] walkImg = new BufferedImage[3];
	BufferedImage currentImage = null;
	String state = null;
	double spawnCount = -0.5;
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


		for (int i = 0; i < 5; i++) { // cut images and add to master list
			if (i < 3) {
				allImg[i] = spriteSheet.getSubimage(i * 22, 66, 24, 32);
			} else {
			allImg[i] = spriteSheet.getSubimage(i * 24, 66, 24, 32); 
			}
		}
		
		for (int i = 0; i < allImg.length; i++) { // add images to proper lists
			if (i < 3) {
				walkImg[i] = allImg[i];
			} else {
				spawnImg[i - 3] = allImg[i];
			}
		}
		
		state = "spawing";
		currentImage = spawnImg[2];
		setImage(currentImage);

	}

	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
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
			
		}		
	}
}
