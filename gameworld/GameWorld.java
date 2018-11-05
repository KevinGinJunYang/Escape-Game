package gameworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Game World in which the Player adventures in, it includes all of the logic for what the
 * player can and cannot do.
 *
 * @author Remi
 *
 */
public class GameWorld {
  /**
   * Map Layout.
   */
  public HashMap<Integer, Room> mapLayout;
  /**
   * Player of game.
   */
  public Player player;
  int defaultNumRooms;
  String roomName = "GenericRoom_";
  int startingRoomId = 01;
  int winCond;

  /**
   * Constructs the Game world using Random Assignments for the Rooms and items.
   */
  public GameWorld() {
    mapLayout = new HashMap<Integer, Room>();
    setUpRooms();
    player = new Player(startingRoomId);
  }

  /**
   * Establish a new Win condition based on how many Kittens are in the Maze.
   */
  public void setNewWinCond() {
    winCond = 0;
    for (Room r : mapLayout.values()) {
      if (r instanceof PuzzleRoom) {
        winCond += 1;
      } else {
        winCond += r.kittensinRoom();
      }
    }
  }

  /**
   * get the Win Condition.
   * @return the winCond
   */
  public int getWinCond() {
    return winCond;
  }

  /**
   * Sets the Win Condition.
   * @param winCond the winCond to set
   */
  public void setWinCond(int winCond) {
    this.winCond = winCond;
  }

  /**
   * Get the Current Room The Player is in.
   *
   * @return The Current Room.
   */
  public Room getCurrentRoom() {
    return mapLayout.get(player.getCurrentRoomId());
  }

  /**
   * Activate a GameObject.
   *
   * @param o The GameObject to Activate.
   *
   * @return True if successful.
   */
  public boolean activateGameObject(GameObject o) {
    if (o == null) {
      return false;
    } else {
      return o.activate(player);
    }
  }

  /**
   * Use this to Drop an item selected from the inventory on the players space.
   *
   * @param o The item to Drop.
   */
  public void dropItem(GameObject o) {
    int playerX = player.getLocationInRoom().getKey();
    int playerY = player.getLocationInRoom().getValue();
    Location l = mapLayout.get(player.getCurrentRoomId()).getLocationAt(playerX, playerY);
    if (l.placeGameObject(o)) {
      player.removeFromInventory(player.getInventoryIdx(o));
    }
  }

  /**
   * Get guidance based on where the player is to where there are kittens in the adjoining rooms.
   * @return
   *    A string Description on how far away kittens are.
   *
   */
  public String getGuidence() {
    Guide g = new Guide();
    List<Room> rooms = new ArrayList<>();
    List<Pair<Direction, Integer>> exits = getCurrentRoom().getExits();
    // adds the Current room and all adjoining rooms to the list of rooms to check
    rooms.add(getCurrentRoom());
    for (Pair<Direction, Integer> p : exits) {
      rooms.add(mapLayout.get(p.getValue()));
    }
    return g.getDialog(g.checkSurroundings(rooms), exits, player.getCurrentRoomId());
  }

  /**
   * Setup Generic Rooms with Random Assignments, every Room will have at-least two Exits. (Mostly
   * for Testing)
   */
  public void setUpRooms() {
    int newId;
    for (int i = 0; i < defaultNumRooms; i++) {
      if (i == 0) {
        newId = 01;
      } else {
        newId = roomName.hashCode() + i;
      }
      mapLayout.put(newId, new Room(newId, roomName + i));
    }

    Object[] mapIDs = mapLayout.keySet().toArray();

    for (Integer i : mapLayout.keySet()) {
      while (mapLayout.get(i).getTotalExits() < 2) {
        int id = (int) mapIDs[(int) (Math.random() * mapIDs.length)];
        Direction dir = Direction.randomDir();
        mapLayout.get(i).setExit(dir, id, "Plain");
        mapLayout.get(id).setExit(dir.turnArround(), i, "Plain");
      }
    }
  }


  /**
   * move in the direction you're facing.
   */
  public void move() {
    int playerX = player.getLocationInRoom().getKey();
    int playerY = player.getLocationInRoom().getValue();

    switch (player.getFacing()) {
      case NORTH:
        // check bounds
        if (playerY - 1 < 0) {
          break;
        }
        // Check if the location you are moving to is empty
        if (!mapLayout.get(player.getCurrentRoomId()).isempty(playerX, playerY - 1)) {
          break;
        }
        // toggle the Players Position within the current location
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY);
        // Update the Players Reference
        player.move(0, -1);
        // toggle the Players Position within the new location
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY - 1);
        break;

      case EAST:
        if (4 < playerX + 1) {
          break;
        }
        if (!mapLayout.get(player.getCurrentRoomId()).isempty(playerX + 1, playerY)) {
          break;
        }
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY);
        player.move(1, 0);
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX + 1, playerY);
        break;

      case SOUTH:
        if (4 < playerY + 1) {
          break;
        }
        if (!mapLayout.get(player.getCurrentRoomId()).isempty(playerX, playerY + 1)) {
          break;
        }
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY);
        player.move(0, 1);
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY + 1);
        break;

      default:// WEST
        if (playerX - 1 < 0) {
          break;
        }
        if (!mapLayout.get(player.getCurrentRoomId()).isempty(playerX - 1, playerY)) {
          break;
        }
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX, playerY);
        player.move(-1, 0);
        mapLayout.get(player.getCurrentRoomId()).setPlayer(playerX - 1, playerY);
        break;
    }
    // Check if the player Actually moved
  }

  /**
   * Call this to push an Object in the Direction the Player is Facing.
   * @param o The Object to be Push.
   */
  public void push(GameObject o) {
    if (o == null || !o.isPushable()) {
      //do nothing
    } else {
      Location l = find(o);
      Location[][] rl = getCurrentRoom().getRoomLayout();
      switch (player.getFacing()) {
        case NORTH:
          for (int i = 0; i < rl.length; i++) {
            for (int j = 0; j < rl[0].length; j++) {
              // find the location of the GameObject
              if (rl[i][j] == l) {
                if (j - 1 < 0) {
                  break;
                } // check bounds
                getCurrentRoom().push(i, j, player.getFacing());// the Push
              }
            }
          }
          break;

        case EAST:
          for (int i = 0; i < rl.length; i++) {
            for (int j = 0; j < rl[0].length; j++) {
              // find the location of the GameObject
              if (rl[i][j] == l) {
                if (i + 1 > 4) {
                  break;
                } // check bounds
                getCurrentRoom().push(i, j, player.getFacing());// the Push
              }
            }
          }
          break;

        case SOUTH:
          for (int i = 0; i < rl.length; i++) {
            for (int j = 0; j < rl[0].length; j++) {
              // find the location of the GameObject
              if (rl[i][j] == l) {
                if (j + 1 < 4) {
                  break;
                } // check bounds
                getCurrentRoom().push(i, j, player.getFacing());// the Push
              }
            }
          }
          break;

        default:// WEST
          for (int i = 0; i < rl.length; i++) {
            for (int j = 0; j < rl[0].length; j++) {
              // find the location of the GameObject
              if (rl[i][j] == l) {
                if (i - 1 < 0) {
                  break;
                } // check bounds
                getCurrentRoom().push(i, j, player.getFacing());// the Push
              }
            }
          }
          break;
      }
    }
  }

  /**
   * Pick up the Specified GameObject from the World and Place it in to the Players inventory.
   *
   * @param o The Object to be picked up.
   * @return True if Successful.
   */
  public boolean pickUpItem(GameObject o) {
    return player.addToInventory(find(o).pickUpGameObject());
  }

  /**
   * Place a specified Item in the specified Chest from the Inventory.
   *
   * @param o The Item to be placed in the Chest.
   * @param c The Chest for the Item to be placed in.
   * @return True if successful.
   */
  public boolean putIteminChest(GameObject o, GameObject c) {
    if (o == null || !(c instanceof Chest)) {
      return false;
    }
    Chest temp = (Chest) c;
    if (temp.addItemToChest(o)) {
      player.removeFromInventory(player.getInventoryIdx(o));
      return true;
    }
    return false;
  }

  /**
   * turn to the Facing of the Player.
   *
   * @param dir The Direction to turn towards.
   */
  public void turn(Direction dir) {
    player.setFacing(dir);
  }

  /**
   * Get the RoomLayout of a Specified Room.
   *
   * @param roomId The ID of the Specified Room.
   * @return An Array of Locations that represent the RoomLayout.
   */
  public Location[][] getRoomLayout(int roomId) {
    return mapLayout.get(roomId).getRoomLayout();
  }

  /**
   * Find the Location where specified GameObject is in the Room using reference comparison of the
   * Objects and return the location, null if the object can't be found.
   *
   * @param o The GameObject to find.
   * @return The Pair of the XY co-ordinates, null otherwise.
   */
  public Location find(GameObject o) {
    Location[][] array = mapLayout.get(player.getCurrentRoomId()).getRoomLayout();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        if (array[i][j].getItem() == o) {
          return array[i][j];
        }
      }
    }
    return null;
  }

  // Methods Required for JAXB

  /**
   * Get the mapLayout.
   * @return the mapLayout
   */
  public HashMap<Integer, Room> getMapLayout() {
    return mapLayout;
  }

  /**
   * Set the mapLayout.
   * @param mapLayout the mapLayout to set
   */
  public void setMapLayout(HashMap<Integer, Room> mapLayout) {
    this.mapLayout = mapLayout;
  }

  /**
   * Get the player.
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the Player.
   * @param player the player to set
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Get the Starting Room id.
   * @return the startingRoomID
   */
  public int getStartingRoomId() {
    return startingRoomId;
  }

  /**
   * Set the Starting Room id.
   * @param startingRoomId the startingRoomID to set
   */
  public void setStartingRoomId(int startingRoomId) {
    this.startingRoomId = startingRoomId;
  }

  /**
   *Reset the Image of Items in the Rooms of the Map.
   */
  public void resetItemImage() {
    for (Room r : mapLayout.values()) {
      r.resetImage();
    }
  }
}
