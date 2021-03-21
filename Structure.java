package GhostAndGoblins;

/**
 * @author William & Quinn
 * This class create a structure in the world (wall or platform)
 */
public class Structure extends Sprite {

	/**
	 * @param path
	 * @param _x
	 * @param _y
	 */
	public Structure(String path, int _x, int _y) {
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
