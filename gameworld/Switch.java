package gameworld;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A Switch that unlocks or locks the object it is Connected to.
 *
 * @author Remi
 *
 */
public class Switch extends GameObject {

  GameObject itemToUnlock = null;
  PuzzleRoom room = null;

  /**
   * Creates a new switch that unlocks a Specific Door.
   *
   * @param d The Door Connected to this Switch.
   */
  public Switch(Door d) {
    this.isPickupable = false;
    this.isPushable = false;
    itemToUnlock = d;
    resetImage();
  }

  /**
   * Creates a new switch that unlocks a Specific Chest.
   *
   * @param c The Chest Connected to this Switch.
   */
  public Switch(Chest c) {
    this.isPickupable = false;
    this.isPushable = false;
    itemToUnlock = c;
    resetImage();
  }

  /**
   * Creates a new switch for a puzzle.
   *
   * @param pr The Puzzle Room connected to this Switch
   */
  public Switch(PuzzleRoom pr) {
    this.room = pr;
    this.isPickupable = false;
    this.isPushable = false;
    resetImage();
  }

  @Override
  public boolean activate(Player player) {
    // check if puzzle switch
    if (itemToUnlock == null && room != null) {
      if (room.checkIfSolved(player)) {
        return true;
      } else {
        return false;
      }
    } else if (itemToUnlock != null && room == null) {
      itemToUnlock.isLocked = false;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    if (room != null) {
      return "A Switch to check the Puzzle in this Room";
    }
    return "A Switch that unlocks the " + itemToUnlock.toString() + " in this Room";
  }

  @Override
  public void resetImage() {
    try {
      if (itemToUnlock instanceof Door) {
        Door d = (Door) itemToUnlock;
        switch (d.doorColor) {
          case "red":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Red.png"));
            break;

          case "blue":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Blue.png"));
            break;

          case "green":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Green.png"));
            break;

          case "yellow":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Yellow.png"));
            break;

          default:
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Plain.png"));
            break;
        }
      } else if (itemToUnlock instanceof Chest) {
        Chest c = (Chest) itemToUnlock;
        switch (c.chestColor) {
          case "red":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Red.png"));
            break;

          case "blue":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Blue.png"));
            break;

          case "green":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Green.png"));
            break;

          case "yellow":
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Yellow.png"));
            break;

          default:
            this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Plain.png"));
            break;
        }
      } else {
        this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Switch_Plain.png"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  // For JAXB

  /**
   * Get the Item connected to this Switch.
   * @return the itemToUnlock
   */
  public GameObject getItemToUnlock() {
    return itemToUnlock;
  }

  /**
   * Set the Item connected to this Switch.
   * @param itemToUnlock the itemToUnlock to set
   */
  public void setItemToUnlock(GameObject itemToUnlock) {
    this.itemToUnlock = itemToUnlock;
  }

  /**
   * Get the Room connected to this Switch.
   * @return the room
   */
  public PuzzleRoom getRoom() {
    return room;
  }

  /**
   * Set the Room connected to this Switch.
   * @param room the room to set
   */
  public void setRoom(PuzzleRoom room) {
    this.room = room;
  }

}
