package gameworld;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class for the Guide NPC that will help the player to find their missing Kittens.
 * 
 * @author Remi
 *
 */
public final class Guide {

  /**
   * Create a new Guide.
   */
  public Guide() {}

  /**
   * Checks a room for Kittens.
   * 
   * @param r The room to check.
   * @return The number of Kittens in the Room.
   */
  public int checkForKittensInRoom(Room r) {
    int count = 0;
    Location[][] l = r.getRoomLayout();
    for (int i = 0; i < l.length; i++) {
      for (int j = 0; j < l[0].length; j++) {
        if (l[i][j].getItem() instanceof Chest) {
          Chest c = (Chest) l[i][j].getItem();
          if (c.getItem() instanceof Kitten) {
            count++;
          }
        }
        if (l[i][j].getItem() instanceof Kitten) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Checks the Surrounding Rooms for kittens and returns the list of RoomIds that contain at least
   * one kitten, and the number of kittens in the Room. Returns a Pair of integers of the form
   * (RoomId, NO.Kittens).
   * 
   * @param rooms The List of the Surrounding Rooms.
   * @return The Rooms that have Kittens in them and how many Kittens are in them.
   */
  public List<Pair<Integer, Integer>> checkSurroundings(List<Room> rooms) {
    ArrayList<Pair<Integer, Integer>> returnList = new ArrayList<>();
    int kittens;
    for (Room r : rooms) { 
      if (r instanceof PuzzleRoom) {
        returnList.add(new Pair<Integer, Integer>(r.getRoomId(), 1));
      }
      kittens = checkForKittensInRoom(r);
      if (kittens > 0) {
        returnList.add(new Pair<Integer, Integer>(r.getRoomId(), kittens));
      }
    }
    if (returnList.isEmpty()) {
      return null;
    } else {
      return returnList;
    }
  }

  /**
   * This returns the String of What the guides' Response to the player will be when called this
   * method requires the Exits of the Current room and a List of Pairs of the form (RoomId,
   * NO.Kittens).
   * 
   * @param roomsWithKittens The list of Rooms with Kittens in them and the amount of kittens in
   *        them.
   * @param directionOfRooms the Directions of the Surrounding Rooms with references to the IDs of
   *        the Rooms.
   * @param currentRoomId The Current Room's Id.
   * @return The String to print out.
   */
  public String getDialog(List<Pair<Integer, Integer>> roomsWithKittens,
      List<Pair<Direction, Integer>> directionOfRooms, int currentRoomId) {

    String s = "";
    String multiKittens = " Kittens in the Room to the ";
    String singleKitten = " Kitten in the Room to the ";

    if (roomsWithKittens == null) {
      return "I sense no Kittens in the Surrrounding rooms.";// may change this to something better
    } else {

      for (Pair<Integer, Integer> roomKittens : roomsWithKittens) {
        int numkittens = roomKittens.getValue();

        if (roomKittens.getKey() == currentRoomId) {
          s += "I sense " + numkittens + " Kitten in this Room. \n";
        } else {

          for (Pair<Direction, Integer> dirRoom : directionOfRooms) {
            if (dirRoom.getValue() == roomKittens.getKey()) {
              s += "I sense "
                  + (numkittens > 1 ? numkittens + multiKittens : numkittens + singleKitten)
                  + dirRoom.getKey() + ". \n";
            }
          }
        }
      }
      return s;
    }
  }
}
