package renderer;

import gameworld.Direction;
import gameworld.Location;
import gameworld.Pair;

/**
 * RotateHelper provides utiliy methods for rotating the Room of the based on hte facing direction
 * of the Player.
 *
 * @author joewill
 *
 */
public class RotateHelper {


  /**
   * Rotates the items in a Room. Rotation amount is based on the Facing Direction of the Player.
   * The Room is rotated so the Player will be facing into the screen.
   *
   * @param room Room to rotate.
   * @param dir Facing Direction.
   * @return A new rotated room.
   */
  public static Location[][] rotateRoom(Location[][] room, Direction dir) {
    switch (dir) {
      case EAST: {
        return rotateRoomToEast(room);
      }
      case SOUTH: {
        return rotateRoomToSouth(room);
      }
      case WEST: {
        return rotateRoomToWest(room);
      }
      default: { // Deault is North
        return rotateRoomToNorth(room);
      }
    }
  }

  /*
   * Return a new room where no rotation is needed.
   */
  private static Location[][] rotateRoomToNorth(Location[][] room) {

    Location[][] rotatedRoom = new Location[room.length][room[0].length];
    for (int i = 0; i < room.length; ++i) {
      for (int j = 0; j < room[0].length; ++j) {
        rotatedRoom[i][j] = room[i][j];
      }
    }
    return rotatedRoom;

  }

  /*
   * Return a new room where the room is rotated clockwise so the Player facing into the screen.
   */
  private static Location[][] rotateRoomToWest(Location[][] room) {

    Location[][] rotatedRoom = new Location[room.length][room[0].length];

    for (int i = 0; i < room.length; ++i) {
      for (int j = 0; j < room[0].length; ++j) {
        rotatedRoom[i][j] = room[j][room.length - i - 1];
      }
    }
    return rotatedRoom;
  }

  /*
   * Return a new room where the room is rotated anticlockwise so the Player facing into the screen.
   */
  private static Location[][] rotateRoomToEast(Location[][] room) {

    Location[][] rotatedRoom = new Location[room[0].length][room.length];

    for (int i = 0; i < room.length; i++) {
      for (int j = 0; j < (room[0].length); j++) {
        rotatedRoom[i][j] = room[room.length - j - 1][i];
      }
    }
    return rotatedRoom;

  }

  /*
   * Return a new room where the room is rotated 180 so the Player facing into the screen.
   */
  private static Location[][] rotateRoomToSouth(Location[][] room) {

    Location[][] rotatedRoom = new Location[room.length][room[0].length];

    for (int i = 0; i < room.length; i++) {
      for (int j = 0; j < (room[0].length); j++) {
        rotatedRoom[i][j] = room[room.length - 1 - i][room[0].length - 1 - j];
      }
    }
    return rotatedRoom;
  }

  /**
   * Pair of coordinates.
   *
   * @param playerPos Player position to rotate.
   * @param dir Facing Direction.
   * @return new Position of the Player after rotation.
   */
  public static Pair<Integer, Integer> rotatePlayer(Pair<Integer, Integer> playerPos,
      Direction dir) {
    switch (dir) {
      case EAST: {
        return rotatePlayerToEast(playerPos);
      }
      case SOUTH: {
        return rotatePlayerToSouth(playerPos);
      }
      case WEST: {
        return rotatePlayerToWest(playerPos);
      }
      default: {
        return rotatePlayerToNorth(playerPos);
      }
    }
  }

  /*
   * Rotate the Position clockwise of the Player so their will be facing into the screen.
   */
  private static Pair<Integer, Integer> rotatePlayerToNorth(Pair<Integer, Integer> playerPos) {

    int newx = playerPos.getKey(); // facing north dont need to rotate.
    int newy = playerPos.getValue();

    return new Pair<Integer, Integer>(newx, newy);
  }

  /*
   * Rotate the Position clockwise of the Player so their will be facing into the screen.
   */
  private static Pair<Integer, Integer> rotatePlayerToWest(Pair<Integer, Integer> playerPos) {

    int newx = -(playerPos.getValue() - 2) + 2;
    int newy = (playerPos.getKey() - 2) + 2;

    return new Pair<Integer, Integer>(newx, newy);
  }

  /*
   * Rotate the Position anticlockwise of the Player so their will be facing into the screen.
   */
  private static Pair<Integer, Integer> rotatePlayerToEast(Pair<Integer, Integer> playerPos) {

    int newx = (playerPos.getValue() - 2) + 2;
    int newy = -(playerPos.getKey() - 2) + 2;

    return new Pair<Integer, Integer>(newx, newy);
  }

  /*
   * Rotate the Position 180 of the Player so their will be facing into the screen.
   */
  private static Pair<Integer, Integer> rotatePlayerToSouth(Pair<Integer, Integer> playerPos) {

    int newx = -(playerPos.getKey() - 2) + 2;
    int newy = -(playerPos.getValue() - 2) + 2;

    return new Pair<Integer, Integer>(newx, newy);
  }

}
