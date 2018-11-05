package gameworld;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Box GameObject.
 * @author remi
 *
 */
public class Box extends GameObject {

  /**
   * Creates a New Box GameObject.
   */
  public Box() {
    this.isPushable = true;
    resetImage();
  }

  @Override
  public boolean activate(Player player) {
    return false;
  }

  @Override
  public String toString() {
    return "Cardboard Box";
  }

  @Override
  public void resetImage() {
    try {
      this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Box.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
