package gameworld;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A Table GameObject.
 * @author Remi
 *
 */
public class Table extends GameObject {

  /**
   * Create a new Table.
   */
  public Table() {
    resetImage();
  }

  @Override
  public boolean activate(Player player) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String toString() {
    return "Wooden Table";
  }

  @Override
  public void resetImage() {
    try {
      this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Table.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
