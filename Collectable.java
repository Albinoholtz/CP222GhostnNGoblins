import java.awt.Rectangle;

/**
 * @author William & Quinn
 * This class is a collectible that interacts with the player in some way.
 */
public class Collectable extends Sprite {

	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Collectable(int _x, int _y) {
		super(_x, _y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
