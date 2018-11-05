package gameworld;

/**
 * The Player of this Game.
 *
 * @author Remi
 *
 */
public class Player {
  /**
   * Current room ID.
   */
  public int currentRoomId;
  /**
   * facing of direction.
   */
  public Direction facing;
  /**
   * Room location.
   */
  public Pair<Integer, Integer> roomLocation;
  GameObject[] playerInventory;

  /**
   * This creates a new Player and the Specified Room.
   *
   * @param roomId the RoomID where the Player Starts.
   */
  public Player(int roomId) {
    this.currentRoomId = roomId;
    this.facing = Direction.NORTH;
    this.roomLocation = new Pair<Integer, Integer>(2, 2);
    playerInventory = new GameObject[10];
  }

  /**
   * Changes the Players local reference to their position in the Room by dx,dy.
   *
   * @param dx A int value
   * @param dy A int value
   */
  public void move(int dx, int dy) {
    roomLocation.setKey(roomLocation.getKey() + dx);
    roomLocation.setValue(roomLocation.getValue() + dy);
  }

  /**
   * Called When Moving Between Rooms sets the currentRoomId to the New RoomID and the Facing to the
   * opposite of the Direction you came from.
   *
   * @param newRoomId The RoomID your heading to.
   *
   * @return Returns True if successful.
   */
  public boolean transitionRoom(int newRoomId) {
    this.currentRoomId = newRoomId;
    return true;
  }

  /**
   * Updates the Players position when moving to a new room.
   */
  public void updatePosInNewRoom() {
    switch (facing) {
      case NORTH:
        roomLocation = new Pair<>(2, 4);
        break;
      case EAST:
        roomLocation = new Pair<>(0, 2);
        break;
      case SOUTH:
        roomLocation = new Pair<>(2, 0);
        break;
      default: // WEST
        roomLocation = new Pair<>(4, 2);
        break;
    }
  }

  /**
   * Adds the specified GameObject to the Players inventory.
   *
   * @param o The GameObject to Add.
   * @return True if successful
   */
  public boolean addToInventory(GameObject o) {
    if (o == null) {
      return false;
    }
    for (int i = 0; i < playerInventory.length; i++) {
      if (playerInventory[i] == null) {
        playerInventory[i] = o;
        return true;
      }
    }
    return false;
  }

  /**
   * Get the String Descriptions of every object in the Players Inventory.
   * @return The String Description
   */
  public String getInventoryList() {
    String s = "";
    for (int i = 0; i < playerInventory.length; i++) {
      if (playerInventory[i] != null) {
        s += "\n  " + playerInventory[i].toString() + " ";
      }
    }
    return s;
  }

  /**
   * Gets the Total Amount of kittens in the Players Inventory.
   *
   * @return The number of Kittens.
   */
  public int totalAmtOfKittens() {
    int count = 0;
    for (int i = 0; i < playerInventory.length; i++) {
      if (playerInventory[i] instanceof Kitten) {
        count++;
      }
    }
    return count;
  }

  /**
   * Get the index of a item that is in the players inventory and returns it.
   *
   * @param o the Item to find in the Inventory.
   * @return The index of the Item, -1 if not there.
   */
  public int getInventoryIdx(GameObject o) {
    for (int i = 0; i < playerInventory.length; i++) {
      if (playerInventory[i] == o) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets the GameObject at index provided. Returns null if index is invalid.
   *
   * @param idx the index of the array.
   * @return The Game Object at the index in the Players inventory.
   */
  public GameObject getInventoryItem(int idx) {
    if (idx < 0 || playerInventory.length < idx) {
      return null;
    } else {
      return playerInventory[idx];
    }
  }

  /**
   * Removes the GameObject at index provided.
   *
   * @param i the index of the array.
   */
  public void removeFromInventory(int i) {
    playerInventory[i] = null;
  }

  /**
   * Checks if the Players Inventory contains an equivalent GameObject.
   * @param o
   *    An Example of the GameObject you are looking for.
   * @param color
   *    The color of the gameObject you are looking for, null if Not Applicable.
   * @return
   *    The GameObject of the type described by 'o' and of the Color 'color'.
   */
  public GameObject inventoryContains(GameObject o, String color) {
    if (o == null) {
      return null;
    }

    for (int i = 0; i < playerInventory.length; i++) {
      if (playerInventory[i] != null) {
        if (playerInventory[i].getClass().equals(o.getClass())) {
          if (color != null) {
            Key k = (Key) playerInventory[i];
            if (k.getKeyColor().equalsIgnoreCase(color)) {
              return playerInventory[i];
            }
          } else {
            return playerInventory[i];
          }
        }
      }
    }
    return null;
  }

  // Methods Required for JAXB

  /**
   * Returns the Direction the Player is currently Facing.
   *
   * @return The Direction the Player is Facing.
   */
  public Direction getFacing() {
    return facing;
  }

  /**
   * sets the Direction the Player is Facing.
   *
   * @param dir The new Directional Facing.
   */
  public void setFacing(Direction dir) {
    facing = dir;
  }

  /**
   * Returns where in the Current Room the Player is Located.
   *
   * @return the X,Y Coordinates in a Pair.
   */
  public Pair<Integer, Integer> getLocationInRoom() {
    return roomLocation;
  }

  /**
   * sets/updates where in the Current Room the Player is Located.
   *
   * @param loc The new X,Y pair describing the Players' Location in the Room.
   */
  public void setRoomLocation(Pair<Integer, Integer> loc) {
    roomLocation = loc;
  }

  /**
   * Sets the CurrentRoomID to the Provided ID.
   *
   * @param roomId The RoomID that the Player is assigned.
   */
  public void setCurrentRoomId(int roomId) {
    currentRoomId = roomId;
  }

  /**
   * Returns the CurrentRoomID.
   *
   * @return the RoomId that the Player is in.
   */
  public int getCurrentRoomId() {
    return currentRoomId;
  }

  /**
   * Returns the Array Containing the Players Inventory.
   *
   * @return the playerInventory
   */
  public GameObject[] getPlayerInventory() {
    return playerInventory;
  }

  /**
   * Set the Players Inventory to the specified Array.
   *
   * @param playerInventory the playerInventory to set
   */
  public void setPlayerInventory(GameObject[] playerInventory) {
    this.playerInventory = playerInventory;
  }


}
