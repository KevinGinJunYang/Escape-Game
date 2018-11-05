package gameworld;

/**
 * The Cardinal Directions of a Compass.
 * 
 * @author Remi
 *
 */
public enum Direction {
  /** Cardinal North Direction. */
  NORTH,
  /** Cardinal East Direction. */
  EAST,
  /** Cardinal South Direction. */
  SOUTH,
  /** Cardinal West Direction. */
  WEST;

  /**
   * Change the relative Direction to the Right of the Current Direction.
   * 
   * @return The Direction to the Right of this one.
   */
  // Rotate 90 degrees clockwise
  public Direction rotateRight() {
    return values()[(ordinal() + 1) % 4];
  }

  // Rotate 180 degrees
  /**
   * Change the relative Direction to the Direct opposite of the Current
   * Direction.
   * 
   * @return The Direction to the Direct opposite of this one.
   */
  public Direction turnArround() {
    return values()[(ordinal() + 2) % 4];
  }

  // Rotate 90 counterclockwise
  /**
   * Change the relative Direction to the Left of the Current Direction.
   * 
   * @return The Direction to the Left of this one.
   */
  public Direction rotateLeft() {
    return values()[(ordinal() + 3) % 4];
  }

  /**
   * Get the Direct opposite of the specified Direction.
   * 
   * @param dir
   *          the Specified Direction.
   * @return The Direction to the Direct opposite of the Specified Direction.
   */
  public static Direction getOppisite(Direction dir) {
    switch (dir) {
      case NORTH:
        return Direction.SOUTH;
      case EAST:
        return Direction.WEST;
      case SOUTH:
        return Direction.NORTH;
      default:
        return Direction.EAST;
    }
  }

  /**
   * Select a Random Direction Value.
   * 
   * @return A Direction value.
   */
  public static Direction randomDir() {
    return Direction.values()[(int) (Math.random() * 4)];
  }

  /**
   * turn the String of a Direction in to a Direction Object.
   * 
   * @param dir
   *          The String of one of the Cardinal Directions.
   * @return The Direction Object of the String
   */
  public Direction fromString(String dir) {
    switch (dir.toLowerCase()) {
      case "north":
        return Direction.NORTH;
      case "south":
        return Direction.SOUTH;
      case "east":
        return Direction.EAST;
      case "west":
        return Direction.WEST;
      default:
        break;
    }
    return null;

  }
}
