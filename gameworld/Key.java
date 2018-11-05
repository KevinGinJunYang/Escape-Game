package gameworld;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Key with a Reference to the Object it Unlocks (Currently Just Doors).
 *
 * @author remi
 *
 */
public class Key extends GameObject {
  String keyColor;

  /**
   * A Key with a Id and Color.
   *
   * @param color
   *          The Color of the Key as a String.
   */
  public Key(String color) {
    this.keyColor = color.toUpperCase();
    setImage();
  }

  /**
   * Set the Color of the Key and assign the Relative PNG File to the ITemImage.
   */
  public void setImage() {
    try {
      switch (keyColor) {
        case "RED":
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Key_Red.png"));
          break;
        case "BLUE":
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Key_Blue.png"));
          break;
        case "GREEN":
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Key_Green.png"));
          break;
        case "YELLOW":
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Key_Yellow.png"));
          break;
        default:
          keyColor = "PLAIN";
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Key_Plain.png"));
          break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean activate(Player player) {
    //with auto unlock on locked objects you don't need to activate keys
    return false;
  }

  // Methods Required for JAXB

  /**
   * Get the Color of this Key.
   *
   * @return the keyColor
   */
  public String getKeyColor() {
    return keyColor;
  }

  /**
   * Set the color of this key.
   *
   * @param keyColor
   *          the keyColor to set
   */
  public void setKeyColor(String keyColor) {
    this.keyColor = keyColor;
  }

  @Override
  public String toString() {
    return keyColor + " KEY";
  }

  @Override
  public void resetImage() {
    setImage();
  }
}
