
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This abstract class implements CanCollide to make use of collision monitoring.
 * Includes a draw method to draw sprite.
 * @param path (image path), x, y
 */

abstract public class Sprite implements CanCollide {

    private int x = 0;
    private int y = 0;
    private BufferedImage image = null;
    private boolean remove = false;

    public Sprite(int _x, int _y) {
        x = _x;
        y = _y;
    }
    
    
    /**
     * @param filename
     * @return BufferedImage of 'filename' if file was successfully loaded
     * @return null if file was not successfully loaded
     */
    public BufferedImage getImageFile(String filename) {
    	BufferedImage spriteSheet = null;
    	try{
			spriteSheet = ImageIO.read(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	return spriteSheet;
    }
    
    public BufferedImage getImage() {
		return image;
    }
    
    public void setImage(BufferedImage newImage) {
    	image = newImage;
    }

    public void setX(int new_x) {
        x = new_x;
    }

    public void setY(int new_y) {
        y = new_y;
    }
    
    public int getX() { // Added this to get x
        return x;
    }

    public int getY() { // Added this to get y
        return y;
    }

    public void updateX(int delta) {
        x += delta;
    }

    public void updateY(int delta) {
        y += delta;
    }

    public int getWidth()
    {
    return image.getWidth();
    }

    public int getHeight()
    {
    return image.getHeight();
    }
   
    public boolean isRemove() {
		return remove;
	}


	public void setRemove(boolean remove) {
		this.remove = remove;
	}


	abstract public void update(int time);
    
    /**
     * This method checks to see if two sprites overlap with each other.
     * @param otherSprite
     * @return true if the sprites overlap
     */

    public boolean overlaps(Sprite otherSprite) {
    	//Create a Rectangle that captures our boundaries
    	Rectangle ourBounds = getHitbox();

    	//Create another Rectangle that gets the other sprite's boundaries
    	Rectangle otherBounds = otherSprite.getHitbox();
    	
    	//Check if hitboxes overlap
    	return ourBounds.intersects(otherBounds);
    }
    
    public abstract Rectangle getHitbox();
    
    void draw(Graphics2D g2) {
        // scales and draws the image by 2
        g2.drawImage(image, x, y, image.getWidth() * 3, image.getHeight() * 3, null);
	}
    
    
    /**
     * Converts a given Image into a BufferedImage
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // return the buffered image
        return bimage;
    }
}


