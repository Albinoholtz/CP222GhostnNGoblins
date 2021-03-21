package GhostAndGoblins;

/**
 * @author William & Quinn
 *
 */
public class Zombie extends Enemy {

	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Zombie(String path, int _x, int _y) {
		super(path, _x, _y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collidedWith(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
