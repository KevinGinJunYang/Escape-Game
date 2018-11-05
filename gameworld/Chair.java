package gameworld;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Chair GameObject.
 * @author Remi
 *
 */
public class Chair extends GameObject {

  /**
   * Create a new Chair.
   */
  public Chair() {
    resetImage();
  }

  @Override
  public boolean activate(Player player) {
    return false;
  }

  @Override
  public String toString() {
    return "Wooden Chair";
  }

  @Override
  public void resetImage() {
    try {
      this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chair.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
