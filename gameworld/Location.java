package gameworld;

/**
 * A Location in a Room Storing a Single Object or the Player.
 *
 * @author Remi
 *
 */
public class Location {
  /**
   * item in location.
   */
  public GameObject item;
  boolean player;

  /**
   * A Location is constructed with nothing is occupying it.
   */
  public Location() {
    item = null;
    player = false;
  }

  /**
   * Checks if there's no Player or GameObject at this Location.
   *
   * @return True if empty.
   */
  public boolean isEmpty() {
    return (item == null || item instanceof Door) ? true : false;
  }

  /**
   * returns true if the player is in this Location.
   *
   * @return a boolean flag if the Player is here.
   */
  public boolean getPlayer() {
    return player;
  }

  /**
   * Toggles the boolean flag stating if the Player is in this Location.
   */
  public void togglePlayer() {
    player = (player == true) ? false : true;
  }

  /**
   * Sets the GameObject in this Location.
   *
   * @param o
   *          the GameObject to be placed in this Location.
   * @return Returns true if the GameObject is able to be placed
   */
  public boolean placeGameObject(GameObject o) {
    if (item != null) {
      return false;
    }
    item = o;
    return true;
  }

  /**
   * Returns the Game Object in this Location without removing it from the
   * Location.
   *
   * @return The GameObject in this Location, null otherwise.
   */
  public GameObject lookAtGameObject() {
    return item;
  }

  /**
   * Returns the Game Object in this Location and removes it from the Location.
   *
   * @return The GameObject in this Location, null otherwise.
   */
  public GameObject pickUpGameObject() {
    if (!this.item.isPickupable()) {
      return null;
    }
    GameObject i = this.item;
    item = null;
    return i;
  }

  // Methods Required for JAXB

  /**
   * Get the Item at this Location.
   * @return the item
   */
  public GameObject getItem() {
    return item;
  }

  /**
   * Set the Item at this Location.
   * @param item
   *          the item to set
   */
  public void setItem(GameObject item) {
    this.item = item;
  }

  /**
   * Get if the Player is at this Location.
   * @return the player
   */
  public boolean isPlayer() {
    return player;
  }

  /**
   * Set if the Player is at this Location.
   * @param player
   *          the player to set
   */
  public void setPlayer(boolean player) {
    this.player = player;
  }

  /**
   * Reset the Image of the Item at this Location.
   */
  public void resetImage() {
    if (item != null) {
      item.resetImage();
    }
  }
}
