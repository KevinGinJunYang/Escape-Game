import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.GuiFrame;
import gameworld.Chest;
import gameworld.Direction;
import gameworld.Door;
import gameworld.GameWorld;
import gameworld.Kitten;
import gameworld.Location;
import gameworld.Player;
import gameworld.Room;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import renderer.Renderer;
import renderer.drawableelements.PlaceableElement;
import renderer.drawableelements.placeableelements.DoorElement;
import renderer.drawableelements.placeableelements.KittenElement;


/**
 * Test for the Renderer class.
 *
 * @author joewill
 *
 */
public class RendererTests {

  /**
   * Test render image method.
   */
  @Test
  public void testRenderImage() {

    Renderer renderer = constructRenderer();

    Room room = constructRoom();

    Player player = constructPlayer(room.getRoomId());

    // called renderImage() to do work.
    renderer.renderImage(room, player);

    // Make sure that renderImage() assigns as it's suppose to.
    try {
      Class<? extends Renderer> rendererClass = renderer.getClass();

      Field roomField = rendererClass.getDeclaredField("room");
      roomField.setAccessible(true);
      Room r1 = (Room) (roomField.get(renderer));
      // The Room assigned to Field room should match the name of the created Room.
      assertTrue(r1 == room);

      Field playerField = rendererClass.getDeclaredField("player");
      playerField.setAccessible(true);
      Player p1 = (Player) (playerField.get(renderer));

      // The Pplayer assigned to Field room should match the name of the created Player.
      assertTrue(p1 == player);

    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test the method findTestHeight() which calculate teh height of the board cells based on the row
   * the Player is at.
   */
  @Test
  public void testFindCellHeight() {
    Renderer renderer = RendererTests.constructRenderer();
    try {

      Class<? extends Renderer> rendererClass = renderer.getClass();
      Method findCellHeight =
          rendererClass.getDeclaredMethod("findCellHeight", int.class, int.class);
      findCellHeight.setAccessible(true);

      // if current row is 0 then height will ne zero
      int wallHeight = 50;
      int row0 = 0;
      int height0 = (int) findCellHeight.invoke(renderer, wallHeight, row0);
      int expectedheight0 = 0; // if
      assertTrue(height0 == expectedheight0);

      // test the other rows
      for (int row = 1; row < 5; row++) {
        int height = (int) findCellHeight.invoke(renderer, wallHeight, row);
        int expectedheight = (Renderer.SCREEN_HEIGHT - wallHeight) / row;
        assertTrue(height == expectedheight);

      }

    } catch (IllegalArgumentException | IllegalAccessException | SecurityException
        | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();

    }

  }

  /**
   * Test Click on the renderer.
   */
  @Test
  public void testPassClickedOnElement() {

    Renderer renderer = constructRenderer();

    Room room = constructRoom();

    Player player = constructPlayer(room.getRoomId());

    // called renderImage() to do work.
    renderer.renderImage(room, player);

    // Make sure that renderImage() assigns as it's suppose to.
    try {
      Class<? extends Renderer> rendererClass = renderer.getClass();

      Field placeablesField = rendererClass.getDeclaredField("placeablesInRoom");
      placeablesField.setAccessible(true);

      List<PlaceableElement> placeables = new ArrayList<>();
      PlaceableElement kitty = new KittenElement(new Kitten(), 2, 4, 2, 2, 60, 50, 800, 600, 0);

      placeables.add(kitty);
      placeablesField.set(renderer, placeables);

      Point hit = new Point(500, 650);
      Point miss = new Point(100, 100);



      Method passClickedOnElement =
          rendererClass.getDeclaredMethod("passClickedOnElement", Point.class);
      passClickedOnElement.setAccessible(true);
      boolean clickHit = (boolean) passClickedOnElement.invoke(renderer, hit);
      assertTrue(clickHit);


      boolean clickMiss = (boolean) passClickedOnElement.invoke(renderer, miss);
      assertFalse(clickMiss);

      // The Room assigned to Field room should match the name of the created Room.


      // The Pplayer assigned to Field room should match the name of the created Player.


    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }

  }


  /**
   * Test clicking on item.
   */
  @Test
  public void testValidGameItemClick() {

    PlaceableElement doorFront = new DoorElement(new Kitten(), 2, 2, 2, 2, 60, 50, 600, 400, 0);
    boolean b = doorFront.pointIsOnElement(new Point(700, 600));
    assertTrue(b);


  }

  /**
   * Test not clicking on item.
   */
  @Test
  public void testInvalidGameitemClick() {
    PlaceableElement doorFront = new DoorElement(new Kitten(), 2, 2, 2, 0, 60, 50, 600, 400, 0);

    boolean b = doorFront.pointIsOnElement(new Point(100, 100));
    assertFalse(b);


  }

  /**
   * Test click on door.
   */
  @Test
  public void testValidDoorClick() {

    PlaceableElement doorFront =
        new DoorElement(new Door(123, "red"), 2, 2, 2, 0, 60, 50, 600, 400, 0);

    boolean b = doorFront.pointIsOnElement(new Point(500, 300));
    assertTrue(b);

    PlaceableElement doorleft =
        new DoorElement(new Door(123, "red"), 2, 2, 0, 2, 60, 50, 600, 400, 0);
    boolean b1 = doorleft.pointIsOnElement(new Point(-100, 500));
    assertTrue(b1);

    PlaceableElement doorRight =
        new DoorElement(new Door(123, "red"), 2, 2, 4, 2, 60, 50, 600, 400, 0);
    boolean b2 = doorRight.pointIsOnElement(new Point(1100, 500));
    assertTrue(b2);

  }

  /**
   * Test not click on door.
   */
  @Test
  public void testInvalidDoorClick() {
    PlaceableElement doorFront =
        new DoorElement(new Door(123, "red"), 2, 2, 2, 0, 60, 50, 600, 400, 0);

    boolean b = doorFront.pointIsOnElement(new Point(500, 600));
    assertFalse(b);

    PlaceableElement doorleft =
        new DoorElement(new Door(123, "red"), 2, 2, 0, 2, 60, 50, 600, 400, 0);
    boolean b1 = doorleft.pointIsOnElement(new Point(600, 300));
    assertFalse(b1);

    PlaceableElement doorRight =
        new DoorElement(new Door(123, "red"), 2, 2, 4, 2, 60, 50, 600, 400, 0);
    boolean b2 = doorRight.pointIsOnElement(new Point(500, 300));
    assertFalse(b2);

  }

  /**
   * Consruct a room.
   *
   * @return a Room.
   */
  public static Room constructRoom() {
    Room room = new Room(123, "testRoom");
    return room;
  }

  /**
   * Creates a player.
   *
   * @param roomId Id of the room.
   * @return Predefined Player.
   */
  public static Player constructPlayer(int roomId) {
    Player player = new Player(roomId);
    player.setFacing(Direction.NORTH);

    return player;
  }



  /**
   * Fill the given Room with objects.
   *
   * @param room Room to populate.
   */
  public static void populateRoom(Room room) {
    Location[][] loc = room.getRoomLayout();

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        loc[i][j].placeGameObject(new Chest("red"));
      }
    }



  }

  /**
   * Create a renderer.
   *
   * @return new Renderer.
   */
  public static Renderer constructRenderer() {

    return new Renderer(new GuiFrame(new GameWorld()));
  }


}
