import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gameworld.Chest;
import gameworld.Direction;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Location;
import gameworld.Pair;
import org.junit.Test;
import renderer.RotateHelper;


/**
 * Tests for the RotaticHelper class.
 *
 * @author joewill
 *
 */
public class RendererRotateHelperTests {


  /**
   * Test the game board rotation using RendererHelper.
   */
  @Test
  public void testBoardRotation() {
    Location[][] loc = this.contructBoard();

    Chest chest = new Chest("plain");
    Key key = new Key("plain");
    Kitten kitten = new Kitten();

    loc[0][1].placeGameObject(chest);
    loc[2][2].placeGameObject(key);
    loc[3][4].placeGameObject(kitten);

    this.testRotateNorth(loc);
    this.testRotateEast(loc);
    this.testRotateWest(loc);
    this.testRotateSouth(loc);
  }

  /*
   * Test rotating the board when Player is facing North.
   */
  private void testRotateNorth(Location[][] loc) {

    Location[][] north = RotateHelper.rotateRoom(loc, Direction.NORTH); // no rotation
    // objects should remain in same location
    assertTrue(north[0][1].equals(loc[0][1]));
    assertTrue(north[2][2].equals(loc[2][2]));
    assertTrue(north[3][4].equals(loc[3][4]));

    // objects should not have moved
    assertFalse(north[1][1].equals(loc[0][1]));
    assertFalse(north[2][3].equals(loc[2][2]));
    assertFalse(north[3][1].equals(loc[3][4]));

  }

  /*
   * Test rotating the board when Player is facing East.
   */
  private void testRotateEast(Location[][] loc) {
    Location[][] east = RotateHelper.rotateRoom(loc, Direction.EAST); // anticlockwise rotation
    assertTrue(east[1][4].equals(loc[0][1]));
    assertTrue(east[2][2].equals(loc[2][2]));
    assertTrue(east[4][1].equals(loc[3][4]));

    // objects should not remain in same location
    assertFalse(east[0][1].equals(loc[0][1]));
    assertFalse(east[3][4].equals(loc[3][4]));
  }

  /*
   * Test rotating the board when Player is facing South.
   */
  private void testRotateSouth(Location[][] loc) {
    Location[][] south = RotateHelper.rotateRoom(loc, Direction.SOUTH); // 180 rotation
    assertTrue(south[4][3].equals(loc[0][1]));
    assertTrue(south[2][2].equals(loc[2][2]));
    assertTrue(south[1][0].equals(loc[3][4]));

    // objects should not remain in same location
    assertFalse(south[0][1].equals(loc[0][1]));
    assertFalse(south[3][4].equals(loc[3][4]));
  }

  /*
   * Test rotating the board when Player is facing West.
   */
  private void testRotateWest(Location[][] loc) {
    Location[][] west = RotateHelper.rotateRoom(loc, Direction.WEST); // clockwise rotation

    // objects should have moved
    assertTrue(west[3][0].equals(loc[0][1]));
    assertTrue(west[2][2].equals(loc[2][2]));
    assertTrue(west[0][3].equals(loc[3][4]));

    // objects should not remain in same location
    assertFalse(west[0][1].equals(loc[0][1]));
    assertFalse(west[3][4].equals(loc[3][4]));
  }

  /**
   * Test the Player position rotation on game board using RendererHelper.
   */
  @Test
  public void testPlayerRotation() {

    this.testRotatePlayerNorth();
    this.testRotatePlayerEast();
    this.testRotatePlayerSouth();
    this.testRotatePlayerWest();
  }

  /*
   * Test rotating Player position when facing North.
   */
  private void testRotatePlayerNorth() {

    // Position of player when "rotated" facing North remain unchanged.
    Pair<Integer, Integer> center = new Pair<Integer, Integer>(2, 2);
    assertTrue(center.equals(RotateHelper.rotatePlayer(center, Direction.NORTH)));

    Pair<Integer, Integer> left = new Pair<Integer, Integer>(1, 0);
    assertTrue(left.equals(RotateHelper.rotatePlayer(left, Direction.NORTH)));

    Pair<Integer, Integer> right = new Pair<Integer, Integer>(4, 3);
    assertTrue(right.equals(RotateHelper.rotatePlayer(right, Direction.NORTH)));
  }

  /*
   * Test rotating Player position when facing East.
   */
  private void testRotatePlayerEast() {

    Pair<Integer, Integer> center = new Pair<Integer, Integer>(2, 2);
    assertTrue(center.equals(RotateHelper.rotatePlayer(center, Direction.EAST)));

    // Position of player when rotated and center be rotated anti clockwise.
    Pair<Integer, Integer> left = new Pair<Integer, Integer>(1, 0);
    assertTrue(
        new Pair<Integer, Integer>(0, 3).equals(RotateHelper.rotatePlayer(left, Direction.EAST)));

    Pair<Integer, Integer> right = new Pair<Integer, Integer>(4, 3);
    assertTrue(
        new Pair<Integer, Integer>(3, 0).equals(RotateHelper.rotatePlayer(right, Direction.EAST)));
  }

  /*
   * Test rotating Player position when facing South.
   */
  private void testRotatePlayerSouth() {
    // Position of player when rotated and center shoudl remain unchanged.
    Pair<Integer, Integer> center = new Pair<Integer, Integer>(2, 2);
    assertTrue(center.equals(RotateHelper.rotatePlayer(center, Direction.SOUTH)));

    // Position of player when rotated and center be rotated 180.
    Pair<Integer, Integer> left = new Pair<Integer, Integer>(1, 0);
    assertTrue(
        new Pair<Integer, Integer>(3, 4).equals(RotateHelper.rotatePlayer(left, Direction.SOUTH)));

    Pair<Integer, Integer> right = new Pair<Integer, Integer>(4, 3);
    assertTrue(
        new Pair<Integer, Integer>(0, 1).equals(RotateHelper.rotatePlayer(right, Direction.SOUTH)));
  }

  /*
   * Test rotating Player position when facing West.
   */
  private void testRotatePlayerWest() {
    // Position of player when rotated and center shoudl remain unchanged.
    Pair<Integer, Integer> center = new Pair<Integer, Integer>(2, 2);
    assertTrue(center.equals(RotateHelper.rotatePlayer(center, Direction.WEST)));

    // Position of player when rotated and center be rotated clockwise.
    Pair<Integer, Integer> left = new Pair<Integer, Integer>(1, 0);
    assertTrue(
        new Pair<Integer, Integer>(4, 1).equals(RotateHelper.rotatePlayer(left, Direction.WEST)));

    Pair<Integer, Integer> right = new Pair<Integer, Integer>(4, 3);
    assertTrue(
        new Pair<Integer, Integer>(1, 4).equals(RotateHelper.rotatePlayer(right, Direction.WEST)));
  }

  /*
   * Helper method taht returns an empty room.
   */
  private Location[][] contructBoard() {

    Location[][] room = new Location[5][5];

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        room[i][j] = new Location(); // inialise Room with empty Locations
      }
    }
    return room;
  }

}


