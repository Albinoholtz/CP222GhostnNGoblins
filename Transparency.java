import java.awt.*;
import java.awt.image.*;

/**
 * @link //www.rgagnon.com/javadetails/java-0265.html 
 * @author Réal Gagnon
 */

public class Transparency {
  public static Image makeColorTransparent
    (Image spriteSheetImg, final Color color) {
    ImageFilter filter = new RGBImageFilter() {
      // the color we are looking for... Alpha bits are set to opaque
      public int markerRGB = color.getRGB() | 0xFF000000;

      public final int filterRGB(int x, int y, int rgb) {
        if ( ( rgb | 0xFF000000 ) == markerRGB ) {
          // Mark the alpha bits as zero - transparent
          return 0x00FFFFFF & rgb;
          }
        else {
          // nothing to do
          return rgb;
          }
        }
      }; 

    ImageProducer ip = new FilteredImageSource(spriteSheetImg.getSource(), filter);
    return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
