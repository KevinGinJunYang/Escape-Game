package gameworld;

import java.util.ArrayList;
import java.util.List;

/**
 * A room in the GameWorld.
 *
 * @author Remi
 *
 */
public class Room {
  /**
   * Room layout.
   */
  public Location[][] roomLayout;
  int roomId;
  ArrayList<Pair<Direction, Integer>> exits;
  int size = 5;
  String roomName;

  // boolean for Map Editor class to see whether the room has been initialised
  private boolean valid;
  private boolean containsPlayer;

  /**
   * Creates a new Room.
   *
   * @param id The ID of the Room.
   * @param name The Name of the Room.
   */
  public Room(int id, String name) {
    this.roomId = id;
    this.roomName = name;
    roomLayout = setUpRoom(size);
    exits = new ArrayList<Pair<Direction, Integer>>();

    this.valid = false;
    this.containsPlayer = false;
  }

  /**
   * Creates a new Room with noID.
   */
  public Room() {
    roomLayout = setUpRoom(5);
    exits = new ArrayList<Pair<Direction, Integer>>();
  }

  /**
   * Sets up the Room as a Square Array of location.
   *
   * @param size The Size of one of the Rows/Columns of the array
   * @return The Array of Locations
   */
  public Location[][] setUpRoom(int size) {
    Location[][] la = new Location[size][size];
    for (int i = 0; i < la.length; i++) {
      for (int j = 0; j < la[0].length; j++) {
        la[i][j] = new Location();
      }
    }
    return la;
  }

  /**
   * Sets the Boolean Flag for a player at the specified Location.
   *
   * @param x the X Coordinate.
   * @param y the Y Coordinate.
   */
  public void setPlayer(int x, int y) {
    this.roomLayout[x][y].togglePlayer();
    this.containsPlayer = true;
  }

  /**
   * Checks if the location at the specified Coordinates has no player or GameObject.
   *
   * @param x the X Coordinate.
   * @param y the Y Coordinate.
   * @return True if the Location is empty
   */
  public boolean isempty(int x, int y) {
    return roomLayout[x][y].isEmpty();
  }

  /**
   * Sets the GameObject at the specified x,y coordinates of the room.
   *
   * @param x The X-Coordinate.
   * @param y The Y-Coordinate.
   * @param o The GameObjet to be Place.
   * @return Returns true if Successful.
   */
  public boolean setGameObjectAt(int x, int y, GameObject o) {
    if (roomLayout[x][y].lookAtGameObject() != null) {
      return false;
    }
    roomLayout[x][y].placeGameObject(o);
    return true;
  }

  /**
   * Returns the GameObject at the specified x,y coordinates of the room.
   *
   * @param x The X-Coordinate.
   * @param y The Y-Coordinate.
   * @return The GameObject at the Specified Coordinates, null if there isn't one.
   */

  public GameObject getItemAt(int x, int y) {
    return roomLayout[x][y].getItem();
  }

  /**
   * Returns the Location Object at the specified x,y coordinates of the room.
   *
   * @param x The X-Coordinate.
   * @param y The Y-Coordinate.
   * @return The Location Object at the Coordinates.
   */
  public Location getLocationAt(int x, int y) {
    return roomLayout[x][y];
  }

  /**
   * Sets an Exit leading to the specified Room in the specified direction and sets the required
   * location as having a door, The Color will determine if the Door is Locked to a specific Colored
   * Key, Plain doors will never be locked.
   *
   * @param dir The specified Direction.
   * @param id The Id of the Connecting Room.
   * @param color The Color of the Door
   * @return returns True if successful.
   */
  public boolean setExit(Direction dir, int id, String color) {
    for (Pair<Direction, Integer> p : exits) {
      if (p.getKey().equals(dir) || p.getValue().equals(id)) {
        return false;
      }
    }
    exits.add(new Pair<Direction, Integer>(dir, id));

    switch (dir) {
      case NORTH:
        return roomLayout[2][0].placeGameObject(new Door(id, color));

      case SOUTH:
        return roomLayout[2][4].placeGameObject(new Door(id, color));

      case EAST:
        return roomLayout[4][2].placeGameObject(new Door(id, color));

      default:
        return roomLayout[0][2].placeGameObject(new Door(id, color));

    }
  }

  /**
   * sets an exit in the specified direction and places a passed door at the appropriate location in
   * the room.
   *
   * @param dir The Specified Direction.
   * @param door The Door to be placed.
   * @return True if successful.
   */
  public boolean setExit(Direction dir, Door door) {
    for (Pair<Direction, Integer> p : exits) {
      if (p.getKey().equals(dir) || p.getValue().equals(door.getRoomReference())) {
        return false;
      }
    }
    exits.add(new Pair<Direction, Integer>(dir, door.getRoomReference()));

    switch (dir) {
      case NORTH:
        roomLayout[2][0].placeGameObject(door);
        break;
      case EAST:
        roomLayout[4][2].placeGameObject(door);
        break;
      case SOUTH:
        roomLayout[2][4].placeGameObject(door);
        break;
      default:
        roomLayout[0][2].placeGameObject(door);
        break;
    }
    return true;
  }

  /**
   * Returns at RoomID which the Exit leads to in the Specified Direction if it exits otherwise -1.
   *
   * @param dir The specified Direction.
   * @return The RoomId of the Connecting Room, -1 if none.
   */
  public int getExit(Direction dir) {
    for (Pair<Direction, Integer> p : exits) {
      if (p.getKey().equals(dir)) {
        return (int) p.getValue();
      }
    }
    return -1;
  }

  /**
   * Get a list of the Door objects in this room.
   *
   * @return A List of Doors
   */
  public List<Door> getDoors() {
    ArrayList<Door> doors = new ArrayList<>();
    if (roomLayout[2][0].item instanceof Door) {
      doors.add((Door) roomLayout[2][0].item);
    }
    if (roomLayout[2][4].item instanceof Door) {
      doors.add((Door) roomLayout[2][4].item);
    }
    if (roomLayout[0][2].item instanceof Door) {
      doors.add((Door) roomLayout[0][2].item);
    }
    if (roomLayout[4][2].item instanceof Door) {
      doors.add((Door) roomLayout[4][2].item);
    }
    return doors;
  }

  /**
   * Returns the total amount of exits in this Room.
   *
   * @return The total amounts of exits in the room.
   */
  public int getTotalExits() {
    return exits.size();
  }

  /**
   * Push An item at the specified Location in the specified Direction.
   *
   * @param x The X Coordinate
   * @param y The Y Coordinate
   * @param dir The direction to push the Object
   * @return True if Successful.
   */
  public boolean push(int x, int y, Direction dir) {
    GameObject itemToMove;
    switch (dir) {
      case NORTH:
        if (!roomLayout[x][y - 1].isEmpty()) {
          return false;
        }
        itemToMove = roomLayout[x][y].lookAtGameObject();
        roomLayout[x][y - 1].placeGameObject(itemToMove);
        roomLayout[x][y].setItem(null);
        return true;
      case EAST:
        if (!roomLayout[x + 1][y].isEmpty()) {
          return false;
        }
        itemToMove = roomLayout[x][y].lookAtGameObject();
        roomLayout[x + 1][y].placeGameObject(itemToMove);
        roomLayout[x][y].setItem(null);
        return true;
      case SOUTH:
        if (!roomLayout[x][y + 1].isEmpty()) {
          return false;
        }
        itemToMove = roomLayout[x][y].lookAtGameObject();
        roomLayout[x][y + 1].placeGameObject(itemToMove);
        roomLayout[x][y].setItem(null);
        return true;
      default:// WEST
        if (!roomLayout[x - 1][y].isEmpty()) {
          return false;
        }
        itemToMove = roomLayout[x][y].lookAtGameObject();
        roomLayout[x - 1][y].placeGameObject(itemToMove);
        roomLayout[x][y].setItem(null);
        return true;
    }
  }

  // Methods Required for JAXB

  /**
   * Returns all the Location tiles in the Room.
   *
   * @return The Location Array describing the Layout of the Room.
   */
  public Location[][] getRoomLayout() {
    return roomLayout;
  }

  /**
   * Sets all the Location Tiles in the Room to The Given Location array.
   *
   * @param rl the Array of Locations.
   */
  public void setRoomLayout(Location[][] rl) {
    this.roomLayout = rl;
  }

  /**
   * The Id of this Room.
   *
   * @return The Rooms Id.
   */
  public int getRoomId() {
    return roomId;
  }

  /**
   * Sets the Id of this Room.
   *
   * @param roomId The ID to be set to this room
   */
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  /**
   * Get a List of Exits that are in this Room.
   *
   * @return the Exits in this room.
   */
  public ArrayList<Pair<Direction, Integer>> getExits() {
    return exits;
  }

  /**
   * Sets the Exits in this room to the Give List.
   *
   * @param exits the List of Exits in this room.
   */
  public void setExits(ArrayList<Pair<Direction, Integer>> exits) {
    this.exits = exits;
  }

  /**
   * Sets a rooms validity for graphics in map editor. Should not be called from anywhere but map
   * editor.
   * @param v boolean.
   */
  public void setValid(boolean v) {
    this.valid = v;
  }

  /**
   * Gets validity of graphics object in map editor. Should not be called from anywhere but map
   * editor.
   * @return valid
   *
   */
  public boolean getValid() {
    return this.valid;
  }

  /**
   * Called from map editor.
   *
   * @return returns true when the room holds a player.
   */
  public boolean getContainsPlayer() {
    return this.containsPlayer;
  }

  /**
   * Called from map editor.
   *
   * @param b true or false
   */
  public void setContainsPlayer(boolean b) {
    this.containsPlayer = b;
  }

  /**
   * Get the Name of this Room.
   * @return the roomName
   */
  public String getRoomName() {
    return roomName;
  }

  /**
   * Set the name of this Room.
   * @param roomName the roomName to set
   */
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  /**
   * Resets all of the images associated with all GameObjects in this Room.
   */
  public void resetImage() {
    for (int i = 0; i < roomLayout.length; i++) {
      for (int j = 0; j < roomLayout[0].length; j++) {
        roomLayout[i][j].resetImage();
      }
    }
  }

  /**
   * Find out the Amount of kittens in this Room.
   * @return the Amount of kittens in this Room.
   */
  public int kittensinRoom() {
    int count = 0;
    for (int i = 0; i < roomLayout.length; i++) {
      for (int j = 0; j < roomLayout[0].length; j++) {
        if (roomLayout[i][j].getItem() instanceof Kitten) {
          count++;
        } else if (roomLayout[i][j].getItem() instanceof Chest) {
          Chest c = (Chest) roomLayout[i][j].getItem();
          if (c.getItem() instanceof Kitten) {
            count++;
          } else {
            //do nothing
          }
        }
      }
    }
    return count;
  }
}
