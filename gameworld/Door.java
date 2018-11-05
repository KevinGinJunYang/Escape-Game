package gameworld;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A Door that leads to another Room.
 *
 * @author Remi
 *
 */
public class Door extends GameObject {
  int roomReference;
  String doorColor;

  /**
   * The Constructor of a Door with a reference to the room it Leads to and the Color of the Door,
   * if no color is Specified the Door will be a Plain Door.
   *
   * @param roomId The RoomID assigned to this Door.
   * @param color The Color of the Door as a String.
   */
  public Door(int roomId, String color) {
    this.roomReference = roomId;
    this.doorColor = color.toLowerCase();
    this.isPickupable = false;
    this.isPushable = false;
    setImage();
  }

  /**
   * Set the Color of the Door and assign the Relative PNG File to the ITemImage.
   */
  public void setImage() {
    try {
      switch (doorColor) {
        case "red":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Door_Red_Closed.png"));
          break;
        case "blue":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Door_Blue_Closed.png"));
          break;
        case "green":
          this.isLocked = true;
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Door_Green_Closed.png"));
          break;
        case "yellow":
          this.isLocked = true;
          this.itemImage =
              ImageIO.read(new File("src/gameworld/Art_Assets/Door_Yellow_Closed.png"));
          break;
        default:
          doorColor = "plain";
          this.itemImage = ImageIO.read(new File("src/gameworld/Art_Assets/Door_Plain_Closed.png"));
          break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Unlocks the door given a key matches.
   *
   * @param k A Key of Some Color.
   * @return true if Successful, otherwise null.
   */
  public boolean unlock(Key k) {
    if (k.getKeyColor().equalsIgnoreCase(doorColor)) {
      isLocked = false;
    }
    return !isLocked;
  }

  @Override
  public boolean activate(Player player) {
    // auto unlock start
    if (isLocked) {
      GameObject[] inventory = player.getPlayerInventory();
      for (int i = 0; i < inventory.length; i++) {
        if (inventory[i] instanceof Key) {
          Key k = (Key) inventory[i];
          if (unlock(k)) {
            player.removeFromInventory(i);
            if (player.transitionRoom(this.roomReference)) {
              player.updatePosInNewRoom();
            }
            return true;
          }
        }
      }
      // auto unlock end
      return false;
    }
    if (player.transitionRoom(this.roomReference)) {
      player.updatePosInNewRoom();
    }
    return true;
  }

  // Methods Required for JAXB

  /**
   * Get the color of the Door.
   *
   * @return The color of the Door as a String.
   */
  public String getDoorColor() {
    return this.doorColor;
  }

  /**
   * Set the Color of this Door.
   *
   * @param color the Color of the Door as a String.
   */
  public void setDoorCOlor(String color) {
    this.doorColor = color;
  }

  /**
   * Get the RoomID Which this Door Connects to.
   *
   * @return The RoomId.
   */
  public int getRoomReference() {
    return this.roomReference;
  }

  /**
   * Set the RoomId to which this Door Connects to.
   *
   * @param roomId The RoomId to which this door connects to.
   */
  public void setRoomReference(int roomId) {
    this.roomReference = roomId;
  }

  @Override
  public String toString() {
    return doorColor + " Door";
  }

  @Override
  public void resetImage() {
    setImage();
  }
}
