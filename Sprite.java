package GhostAndGoblins;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This abstract class has instance variables to determine locations and abstract methods
 * that are used to update the instance. Implements CanCollide to make use of collision monitoring.
 * Also includes a draw method to draw sprite.
 * @param path (image path), x, y
 */

abstract public class Sprite implements CanCollide {

    private int x = 0;
    private int y = 0;
    private BufferedImage image = null;

    public Sprite(String path, int _x, int _y) {
        x = _x;
        y = _y;
        try {
          image = ImageIO.read(new File(path));
        } catch(IOException e) {
          System.out.println(e);
          System.exit(1);
        }
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
   
    abstract public void update();
    
    /**
     * This method checks to see if two sprites overlap with each other. This will come
     * in handy when we're checking for collisions.
     * @param otherSprite
     * @return true if the sprites overlap
     */

    public boolean overlaps(Sprite otherSprite)
    {
    	 //Create a Rectangle that captures our boundaries
   	 Rectangle ourBounds = new Rectangle();
        ourBounds.setSize(getWidth(), getHeight());
        ourBounds.setLocation(x, y);
        
        //Create another Rectangle that gets the other sprite's boundaries
        Rectangle otherBounds = new Rectangle();
        otherBounds.setSize(otherSprite.getWidth(), otherSprite.getHeight());
        otherBounds.setLocation(otherSprite.getX(), otherSprite.getY());
        
        //Now we can use the handy intersects method that Rectangle provides!
        return ourBounds.intersects(otherBounds);
    }
    
    //TODO change graphics type
    
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(image, x, y, null);
    }
}


