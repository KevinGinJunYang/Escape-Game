package gameworld;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A kitten GameObject.
 * @author Remi
 *
 */
public class Kitten extends GameObject {

  /**
   * Create a new Kitten GameObject.
   */
  public Kitten() {
    resetImage();
  }

  @Override
  public boolean activate(Player player) {
    return false;
  }

  @Override
  public String toString() {
    return "A Fluffy Ball Of Joy";
  }

  @Override
  public void resetImage() {
    try {
      itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Kitten.png"));// TODO : Make image
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
