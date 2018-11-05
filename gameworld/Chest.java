package gameworld;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A Chest that can be locked and contain a GameObject.
 *
 * @author Remi
 */
public class Chest extends GameObject {
  GameObject item;
  String chestColor;

  /**
   * Make a Chest of the specified Color, any Chest that is Colored is locked.
   *
   * @param color
   *          The Color of the chest as a String.
   */
  public Chest(String color) {
    this.isContainer = true;
    this.chestColor = color.toLowerCase();
    this.isPickupable = false;
    this.isPushable = false;
    setImage();
  }

  /**
   * Set the Color of the Chest and assign the Relative PNG File to the ITemImage.
   */
  public void setImage() {
    try {
      switch (chestColor) {
        case "red":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chest_Red.png"));
          break;
        case "blue":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chest_Blue.png"));
          break;
        case "green":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chest_Green.png"));
          break;
        case "yellow":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chest_Yellow.png"));
          break;
        default:
          chestColor = "plain";
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Chest_Plain.png"));
          break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add a GameObject to a Chest.
   *
   * @param o
   *          The Object to add.
   * @return Returns true if Successful.
   */
  public boolean addItemToChest(GameObject o) {
    if (item != null || o == null || !o.isPickupable()) {
      return false;
    }
    item = o;
    return true;
  }

  /**
   * Get the Item from the Chest if it is Unlocked.
   *
   * @param player The PLayer that activates the Chest.
   *
   * @return returns the Item from the Chest if there is one, null otherwise
   */
  public boolean getItemFromChest(Player player) {
    if (item == null || isLocked) {
      return false;
    } else {
      if (player.addToInventory(item)) {
        item = null;
        return true;
      }
      return false;
    }
  }

  /**
   * Unlocks the Chest given a key matches.
   *
   * @param k
   *          A Key of Some Color.
   * @return true if Successful, otherwise null.
   */
  public boolean unlock(Key k) {
    if (k.getKeyColor().equalsIgnoreCase(chestColor)) {
      isLocked = false;
    }
    return !isLocked;
  }

  @Override
  public boolean activate(Player player) {
    if (!isLocked && item == null) {
      return true;
    } else if (!isLocked && item != null) {
      return getItemFromChest(player);
      // auto unlock start
    } else if (isLocked && item != null) {
      GameObject[] inventory = player.getPlayerInventory();
      for (int i = 0; i < inventory.length; i++) {
        if (inventory[i] instanceof Key) {
          Key k = (Key) inventory[i];
          if (unlock(k)) {
            player.removeFromInventory(i);
            return getItemFromChest(player);
          }
        }
      }
    } else if (isLocked && item == null) {
      GameObject[] inventory = player.getPlayerInventory();
      for (int i = 0; i < inventory.length; i++) {
        if (inventory[i] instanceof Key) {
          Key k = (Key) inventory[i];
          if (unlock(k)) {
            player.removeFromInventory(i);
            return true;
          }
        }
      }
    }
    return false;
  }

  // Methods Required for JAXB

  /**
   * Get the Item in the Chest.
   * @return the item
   */
  public GameObject getItem() {
    return item;
  }

  /**
   * Set the Item in the Chest.
   * @param item
   *          the item to set
   */
  public void setItem(GameObject item) {
    this.item = item;
  }

  /**
   * Get the Color of the Chest.
   * @return the chestColor
   */
  public String getChestColor() {
    return chestColor;
  }

  /**
   * Set the Color of the Chest.
   * @param chestColor
   *          the chestColor to set
   */
  public void setChestColor(String chestColor) {
    this.chestColor = chestColor;
  }

  @Override
  public String toString() {
    return chestColor + " Chest";
  }

  @Override
  public void resetImage() {
    setImage();
  }
}
