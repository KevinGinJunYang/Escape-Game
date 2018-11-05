import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Direction;
import gameworld.Door;
import gameworld.GameWorld;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Location;
import gameworld.Pair;
import gameworld.Player;
import gameworld.PuzzleRoom;
import gameworld.Room;
import gameworld.Table;
import org.junit.Test;

/**
 * Game World.
 *
 * @author Remi- yangkevi & willie for docs
 *
 */
public class GameWorldTest {
  GameWorld gw = new GameWorld();

  /**
   * Test initialisation.
   */
  @Test
  public void test_GameWorld_initilization() {
    GameWorld gwtest = new GameWorld();
    gwtest.setUpRooms();
    assertNotNull(gwtest);
  }

  /**
   * test activate.
   */
  @Test
  public void test_Activate() {
    Player player = new Player(1);
    Chair c = new Chair();
    Box b = new Box();
    Table t = new Table();

    assertFalse(c.activate(player));
    assertFalse(b.activate(player));
    assertFalse(t.activate(player));
  }

  /**
   * test get exit.
   */
  @Test
  public void test_getExit() {
    buildGameWorld(gw);
    assertEquals(03, gw.getCurrentRoom().getExit(Direction.NORTH));
  }

  /**
   * test reset image.
   */
  @Test
  public void test_ResetImage() {
    buildGameWorld(gw);
    gw.getCurrentRoom().getItemAt(4, 4).setItemImage(null);
    gw.resetItemImage();
    assertNotNull(gw.getCurrentRoom().getItemAt(4, 4).itemImage);
  }

  /**
   * test room initialisation.
   */
  @Test
  public void room_initilization() {
    assertNotNull(new Room());
    assertNotNull(new Room(21, "test"));
    assertNotNull(new PuzzleRoom(33, 1));
  }

  /**
   * test item drop.
   */
  @Test
  public void test_Drop() {
    buildGameWorld(gw);
    Key k = new Key("");

    assertTrue(gw.getPlayer().addToInventory(k));
    gw.dropItem(k);
    assertEquals(k, gw.getCurrentRoom().getLocationAt(2, 2).getItem());
  }

  /**
   * test activate game object.
   */
  @Test
  public void test_gwActivateGameObject() {
    buildGameWorld(gw);
    assertTrue(gw.activateGameObject(gw.getCurrentRoom().getItemAt(4, 4)));
  }

  /**
   * test win condition.
   */
  @Test
  public void testWinCond() {
    buildGameWorld(gw);
    gw.setNewWinCond();
    assertEquals(2, gw.getWinCond());
    gw.getMapLayout().get(04).getItemAt(4, 4).activate(gw.getPlayer());
    gw.getMapLayout().get(04).getItemAt(2, 2).activate(gw.getPlayer());
    gw.getMapLayout().get(04).getItemAt(2, 2).activate(gw.getPlayer());
    gw.player.addToInventory(new Kitten());
    assertEquals(2, gw.getPlayer().totalAmtOfKittens());

  }

  /**
   * test inventory contains.
   */
  @Test
  public void check_InventoryContains() {
    Player p = new Player(1);
    Key k = new Key("red");
    Box b = new Box();
    p.addToInventory(k);
    p.addToInventory(b);

    assertEquals(k, p.inventoryContains(new Key(""), "Red"));
    assertNull(p.inventoryContains(new Table(), null));
    assertNull(p.inventoryContains(new Key(""), "Blue"));
    assertEquals(b, p.inventoryContains(new Box(), null));
  }

  /**
   * test location - empty.
   */
  @Test
  public void location_Test_01() {
    Location l = new Location();
    assertTrue(l.isEmpty());
    l.setItem(new Kitten());
    assertFalse(l.isEmpty());
  }

  /**
   * test location - has player.
   */
  @Test
  public void location_Test_02() {
    Location l = new Location();
    assertFalse(l.getPlayer());
    l.togglePlayer();
    assertTrue(l.getPlayer());
  }

  /**
   * test location pickup nothing.
   */
  @Test
  public void location_Test_03() {
    Location l = new Location();
    l.setItem(new Door(01, "blue"));
    assertNull(l.pickUpGameObject());
  }

  /**
   * test location - pickup object.
   */
  @Test
  public void location_Test_04() {
    Location l = new Location();
    Table t = new Table();
    l.setItem(t);
    assertTrue(l.pickUpGameObject().equals(t));
  }

  /**
   * test room - has player.
   */
  @Test
  public void test_Room_01() {
    Room r = new Room(01, "a");
    r.setPlayer(2, 2);
    assertTrue(r.getLocationAt(2, 2).isPlayer());
  }

  /**
   * test room - empty.
   */
  @Test
  public void test_Room_02() {
    Room r = new Room(01, "a");
    r.setPlayer(2, 2);
    assertTrue(r.isempty(2, 2));
  }

  /**
   * test room - object.
   */
  @Test
  public void test_Room_03() {
    Room r = new Room(01, "a");
    Chest c = new Chest("plain");
    assertTrue(r.setGameObjectAt(0, 0, c));
  }

  /**
   * test room - chest.
   */
  @Test
  public void test_Room_04() {
    Room r = new Room(01, "a");
    Key k = new Key("red");
    Chest c = new Chest("plain");
    r.setGameObjectAt(0, 0, k);
    assertFalse(r.setGameObjectAt(0, 0, c));
  }

  /**
   * test room - get item.
   */
  @Test
  public void test_Room_05() {
    Room r = new Room(01, "a");
    Chest c = new Chest("plain");
    r.setGameObjectAt(0, 0, c);
    assertTrue(r.getItemAt(0, 0).equals(c));
  }

  /**
   * test room - setting exits.
   */
  @Test
  public void test_Room_06() {
    Room r = new Room(01, "a");
    assertTrue(r.setExit(Direction.NORTH, 02, "plain"));
    assertTrue(r.setExit(Direction.EAST, 03, "RED"));
    assertTrue(r.setExit(Direction.SOUTH, 04, "Blue"));
    assertTrue(r.setExit(Direction.WEST, 05, "YELLOW"));
    assertFalse(r.setExit(Direction.WEST, 07, "YELLOW"));
  }

  /**
   * test room - setting exits.
   */
  @Test
  public void test_Room_07() {
    Room r = new Room(01, "a");
    Door d1 = new Door(02, "resd");
    Door d2 = new Door(03, "red");
    Door d3 = new Door(04, "blue");
    assertTrue(r.setExit(Direction.NORTH, d1));
    assertTrue(r.setExit(Direction.EAST, d2));
    assertTrue(r.setExit(Direction.SOUTH, d3));
    assertFalse(r.setExit(Direction.WEST, d1));
    Door d4 = new Door(05, "yellow");
    assertFalse(r.setExit(Direction.SOUTH, d4));
    assertTrue(r.setExit(Direction.WEST, d4));
  }

  /**
   * test door .
   */
  @Test
  public void test_Door_01() {
    Door redDoor = new Door(01, "RED");
    assertTrue(redDoor.getDoorColor().equalsIgnoreCase("Red"));
  }

  /**
   * test door - is locked.
   */
  @Test
  public void test_Door_02() {
    Door redDoor = new Door(01, "RED");
    assertTrue(redDoor.isLocked());
  }

  /**
   * test door - not locked.
   */
  @Test
  public void test_Door_03() {
    Door plainDoor = new Door(01, "plain");
    assertFalse(plainDoor.isLocked());
  }

  /**
   * test door - is container.
   */
  @Test
  public void test_Door_04() {
    Door redDoor = new Door(01, "RED");
    assertFalse(redDoor.isContainer());
  }

  /**
   * test door - is pushable.
   */
  @Test
  public void test_Door_05() {
    Door redDoor = new Door(01, "RED");
    assertFalse(redDoor.isPushable());
  }

  /**
   * test chest - blue.
   */
  @Test
  public void test_Chest_01() {
    Chest blueChest = new Chest("Blue");
    assertTrue(blueChest.getChestColor().equalsIgnoreCase("BLUE"));
  }

  /**
   * test chest - red.
   */
  @Test
  public void test_Chest_02() {
    Chest blueChest = new Chest("Blue");
    assertTrue(blueChest.isLocked());
  }

  /**
   * test chest - plain locked.
   */
  @Test
  public void test_Chest_03() {
    Chest plainChest = new Chest("plain");
    assertFalse(plainChest.isLocked());
  }

  /**
   * test chest - blue. is container.
   */
  @Test
  public void test_Chest_04() {
    Chest blueChest = new Chest("Blue");
    assertTrue(blueChest.isContainer());
  }

  /**
   * test chest - get key from chest.
   */
  @Test
  public void test_Chest_05() {
    Chest blueChest = new Chest("Blue");
    Key key = new Key("Red");
    blueChest.addItemToChest(key);
    assertTrue(blueChest.getItem().equals(key));
  }

  /**
   * test chest - unlock with key.
   */
  @Test
  public void test_Chest_06() {
    Chest blueChest = new Chest("Blue");
    Key key = new Key("blue");
    blueChest.unlock(key);
    assertFalse(blueChest.isLocked());
  }

  /**
   * test chest - add item.
   */
  @Test
  public void test_Chest_07() {
    Player player = new Player(01);
    Chest blueChest = new Chest("Blue");
    Key key = new Key("Red");
    blueChest.addItemToChest(key);
    assertFalse(blueChest.getItemFromChest(player));
  }

  /**
   * test chest - pass item to player.
   */
  @Test
  public void test_Chest_08() {
    Player player = new Player(01);
    Chest blueChest = new Chest("Blue");
    Key key = new Key("Red");
    Key key2 = new Key("BLUE");
    blueChest.addItemToChest(key);
    blueChest.unlock(key2);
    assertTrue(blueChest.getItemFromChest(player));
  }

  /**
   * test chest - invalid unlock.
   */
  @Test
  public void test_Chest_09() {
    Chest blueChest = new Chest("Blue");
    Key key = new Key("Red");
    blueChest.unlock(key);
    assertFalse(blueChest.unlock(key));
  }

  /**
   * test chest - interact empty.
   */
  @Test
  public void test_Chest_10() {
    Player p = new Player(01);
    Chest c = new Chest("pla");
    assertTrue(c.activate(p));
  }

  /**
   * test chest - interact kitten.
   */
  @Test
  public void test_Chest_11() {
    Player p = new Player(01);
    Chest c = new Chest("pla");
    c.addItemToChest(new Kitten());
    assertTrue(c.activate(p));
  }

  /**
   * test chest - interact key.
   */
  @Test
  public void test_Chest_12() {
    Player p = new Player(01);
    Chest c = new Chest("red");
    p.addToInventory(new Key("RED"));
    assertTrue(c.activate(p));
  }

  /**
   * test chest - add table.
   */
  @Test
  public void test_Chest_13() {
    Player p = new Player(01);
    Chest c = new Chest("red");
    p.addToInventory(new Key("RED"));
    c.addItemToChest(new Table());
    assertTrue(c.activate(p));
  }

  /**
   * test chest - invalid add table.
   */
  @Test
  public void test_Chest_14() {
    Player p = new Player(01);
    Chest c = new Chest("red");
    p.addToInventory(new Key("Blue"));
    c.addItemToChest(new Table());
    assertFalse(c.activate(p));
  }

  /**
   * test move - north.
   */
  @Test
  public void test_Move_North_1() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertNotNull(gw.getGuidence());
    assertTrue(dx == 2 && dy == 1);
  }

  /**
   * test move - north.
   */
  @Test
  public void test_Move_North_2() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(0, 0));

    int x = gw.getPlayer().getLocationInRoom().getKey();
    int y = gw.getPlayer().getLocationInRoom().getValue();
    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(x == dx && y == dy);
  }

  /**
   * test move - east.
   */
  @Test
  public void test_Move_east_1() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);

    gw.turn(gw.getPlayer().getFacing().rotateRight());
    assertTrue(gw.player.facing.equals(Direction.EAST));

    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(dx == 3 && dy == 2);
  }

  /**
   * test move - east.
   */
  @Test
  public void test_Move_east_2() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.turn(gw.getPlayer().getFacing().rotateRight());
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(4, 0));

    int x = gw.getPlayer().getLocationInRoom().getKey();
    int y = gw.getPlayer().getLocationInRoom().getValue();
    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(x == dx && y == dy);
  }

  /**
   * test south movement.
   */
  @Test
  public void test_Move_south_1() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);

    gw.turn(gw.getPlayer().getFacing().turnArround());
    assertTrue(gw.player.facing.equals(Direction.SOUTH));

    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(dx == 2 && dy == 3);
  }

  /**
   * test south movement.
   */
  @Test
  public void test_Move_south_2() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.turn(gw.getPlayer().getFacing().turnArround());
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(0, 4));

    int x = gw.getPlayer().getLocationInRoom().getKey();
    int y = gw.getPlayer().getLocationInRoom().getValue();
    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(x == dx && y == dy);
  }

  /**
   * test west movement.
   */
  @Test
  public void test_Move_west_1() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);

    gw.turn(gw.getPlayer().getFacing().rotateLeft());
    assertTrue(gw.player.facing.equals(Direction.WEST));

    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(dx == 1 && dy == 2);
  }

  /**
   * test west movement.
   */
  @Test
  public void test_Move_west_2() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.turn(gw.getPlayer().getFacing().rotateRight());
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(4, 4));

    int x = gw.getPlayer().getLocationInRoom().getKey();
    int y = gw.getPlayer().getLocationInRoom().getValue();
    gw.move();
    int dx = gw.getPlayer().getLocationInRoom().getKey();
    int dy = gw.getPlayer().getLocationInRoom().getValue();
    assertTrue(x == dx && y == dy);
  }

  /**
   * test puzzle.
   */
  // puzzle 1
  @Test
  public void test_Puzzle_1() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 1));
    gw.mapLayout.get(01).setExit(Direction.EAST, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.WEST, 01, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 02, "blue");

    gw.player.currentRoomId = 04;
    assertFalse(gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player));
    assertTrue(gw.getCurrentRoom().getItemAt(4, 4).activate(gw.player));
    gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player);

    assertTrue(gw.getCurrentRoom().getItemAt(2, 2) instanceof Chest);

  }

  // puzzle 1
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzle_2() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 1));
    gw.mapLayout.get(01).setExit(Direction.EAST, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.WEST, 01, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 02, "blue");

    gw.player.currentRoomId = 04;

    gw.getCurrentRoom().getItemAt(0, 4).activate(gw.player);
    gw.getCurrentRoom().getItemAt(4, 4).activate(gw.player);
    assertFalse(gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player));

  }

  // puzzle 2
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzel_3() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 2));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    assertTrue(gw.getCurrentRoom().getItemAt(2, 0) instanceof Chest);

    assertFalse(gw.getCurrentRoom().getItemAt(2, 3).isPickupable);
  }

  // puzzle 2
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzle_4() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 2));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    gw.player.roomLocation = new Pair<Integer, Integer>(2, 4);
    gw.push(gw.getCurrentRoom().getItemAt(2, 3));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 3) == null);
    assertFalse(gw.getCurrentRoom().getItemAt(2, 2).isPickupable());
    assertFalse(gw.getCurrentRoom().getItemAt(3, 4).activate(gw.player));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 3) instanceof Box);
    assertTrue(gw.player.roomLocation.getKey() == 2 && gw.player.roomLocation.getValue() == 4);
  }

  // puzzle 3
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzle_5() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 3));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    assertTrue(gw.getCurrentRoom().getItemAt(2, 2) instanceof Table);
    assertFalse(gw.getCurrentRoom().getItemAt(0, 1).activate(gw.player));
    gw.getCurrentRoom().setGameObjectAt(2, 1, new Chair());
    assertFalse(gw.getCurrentRoom().getItemAt(0, 1).activate(gw.player));
    gw.getCurrentRoom().setGameObjectAt(2, 3, new Chair());
    assertTrue(gw.getCurrentRoom().getItemAt(0, 1).activate(gw.player));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 2) instanceof Chest);
  }

  // puzzle 3
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzle_6() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 3));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    gw.getCurrentRoom().setGameObjectAt(1, 2, new Chair());
    gw.getCurrentRoom().setGameObjectAt(3, 2, new Chair());

    assertTrue(gw.getCurrentRoom().getItemAt(0, 1).activate(gw.player));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 2) instanceof Chest);
  }

  /**
   * test puzzle.
   */
  // puzzle 4
  @Test
  public void test_Puzzle_7() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 4));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    assertFalse(gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player));
    assertTrue(gw.pickUpItem(gw.getCurrentRoom().getItemAt(4, 2)));
    Chest c = (Chest) gw.getCurrentRoom().getItemAt(0, 0);
    assertTrue(c.addItemToChest(gw.player.getInventoryItem(0)));
  }

  // puzzle 4
  /**
   * test puzzle.
   */
  @Test
  public void test_Puzzle_8() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.mapLayout.put(04, new PuzzleRoom(04, 4));
    gw.mapLayout.get(03).setExit(Direction.NORTH, 04, "plain");
    gw.mapLayout.get(04).setExit(Direction.SOUTH, 03, "plain");
    gw.player.currentRoomId = 04;

    Key k = new Key("");
    Table t = new Table();
    Chair c = new Chair();
    Box b = new Box();

    gw.player.addToInventory(k);
    gw.player.addToInventory(t);
    gw.player.addToInventory(c);
    gw.player.addToInventory(b);

    gw.putIteminChest(k, gw.getCurrentRoom().getItemAt(0, 0));
    gw.putIteminChest(t, gw.getCurrentRoom().getItemAt(0, 4));
    gw.putIteminChest(b, gw.getCurrentRoom().getItemAt(4, 0));
    gw.putIteminChest(c, gw.getCurrentRoom().getItemAt(4, 4));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player));
    assertTrue(gw.getCurrentRoom().getItemAt(2, 2).activate(gw.player));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_01() {
    Player p = new Player(01);
    Key k = new Key("BLUE");
    assertTrue(p.addToInventory(k));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_02() {
    Player p = new Player(01);
    assertFalse(p.addToInventory(null));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_03() {
    Player p = new Player(01);
    Key k = new Key("BLUE");
    Box b = new Box();
    p.addToInventory(k);
    p.addToInventory(b);
    assertTrue(p.getPlayerInventory()[1].equals(b));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_04() {
    Player p = new Player(01);
    Door d = new Door(02, "BLUE");
    Key k = new Key("BLUE");
    p.addToInventory(k);
    assertTrue(d.activate(p));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_05() {
    Player p = new Player(01);
    Chest c = new Chest("BLUE");
    Key k2 = new Key("RED");
    c.addItemToChest(k2);
    Key k = new Key("BLUE");
    p.addToInventory(k);
    assertTrue(c.activate(p));
  }

  /**
   * test inventory.
   */
  @Test
  public void test_Inventory_06() {
    Player p = new Player(01);

    p.addToInventory(new Key("pla"));
    p.addToInventory(new Box());
    p.addToInventory(new Kitten());
    p.addToInventory(new Table());
    p.addToInventory(new Key("Red"));
    p.addToInventory(new Box());
    p.addToInventory(new Kitten());
    p.addToInventory(new Table());
    p.addToInventory(new Chair());
    p.addToInventory(new Table());
    assertFalse(p.addToInventory(new Chair()));
    assertNotNull(p.getInventoryList());
  }

  /**
   * test push 1.
   */
  @Test
  public void test_Push_01() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(3, 4));
    gw.push(gw.getCurrentRoom().getItemAt(3, 3));

    assertTrue(gw.getCurrentRoom().roomLayout[3][2].item instanceof Box);

  }

  /**
   * test push 2.
   */
  @Test
  public void test_Push_02() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(4, 3));
    gw.getPlayer().facing = Direction.WEST;
    gw.push(gw.getCurrentRoom().getItemAt(3, 3));
    assertTrue(gw.getCurrentRoom().roomLayout[2][3].item instanceof Box);
  }

  /**
   * test push 3.
   */
  @Test
  public void test_Push_03() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(3, 2));
    gw.getPlayer().facing = Direction.SOUTH;
    gw.push(gw.getCurrentRoom().getItemAt(3, 3));
    assertTrue(gw.getCurrentRoom().roomLayout[3][4].item instanceof Box);
  }

  /**
   * test push 4.
   */
  @Test
  public void test_Push_04() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().setRoomLocation(new Pair<Integer, Integer>(2, 3));
    gw.getPlayer().facing = Direction.EAST;
    gw.push(gw.getCurrentRoom().getItemAt(3, 3));
    assertTrue(gw.getCurrentRoom().roomLayout[4][3].item instanceof Box);
  }

  /**
   * test trans 1.
   */
  @Test
  public void test_Transition_Room_01() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().addToInventory(new Key("green"));
    gw.getPlayer().setFacing(Direction.WEST);
    gw.getCurrentRoom().getItemAt(0, 2).activate(gw.player);

    Pair<Integer, Integer> p = gw.player.getLocationInRoom();
    assertTrue(p.getKey() == 4 && p.getValue() == 2);

    gw.player.facing = Direction.EAST;
    gw.getCurrentRoom().getItemAt(4, 2).activate(gw.player);

    p = gw.player.getLocationInRoom();
    assertTrue(p.getKey() == 0 && p.getValue() == 2);

    gw.player.facing = Direction.SOUTH;
    gw.getCurrentRoom().getItemAt(0, 2).activate(gw.player);

    p = gw.player.getLocationInRoom();
    assertTrue(p.getKey() == 2 && p.getValue() == 0);

  }

  /**
   * test trams.
   */
  @Test
  public void test_Transition_Room_02() {
    GameWorld gw = new GameWorld();
    buildGameWorld(gw);
    gw.getPlayer().addToInventory(new Key("green"));

    // north requires Yellow key
    assertFalse(gw.getCurrentRoom().getItemAt(2, 0).activate(gw.player));
    // west requires Green key
    assertTrue(gw.getCurrentRoom().getItemAt(0, 2).activate(gw.player));


  }

  /**
   * Builds game world.
   *
   * @param gw gameworld.
   */
  public void buildGameWorld(GameWorld gw) {


    Chest tl = new Chest("Red");
    tl.addItemToChest(new Key("Yellow"));
    Chest tr = new Chest("Blue");
    tr.addItemToChest(new Key("green"));
    Chest bl = new Chest("plain");
    bl.addItemToChest(new Key("red"));
    Chest br = new Chest("plain");
    br.addItemToChest(new Key("blue"));


    Room r = new Room(01, "R");
    r.setExit(Direction.NORTH, 03, "Yellow");
    r.setExit(Direction.WEST, 02, "Green");

    Room r2 = new Room(02, "R2");
    r.setExit(Direction.SOUTH, 01, "Plain");
    r2.setExit(Direction.EAST, 01, "noColor");

    Room r3 = new Room(03, "R3");
    Room r4 = new PuzzleRoom(04, 1);
    r3.setExit(Direction.NORTH, 04, "Plain");
    r4.setExit(Direction.SOUTH, 03, "noColor");


    r.setGameObjectAt(0, 0, tl);
    r.setGameObjectAt(4, 4, br);
    r.setGameObjectAt(4, 0, tr);
    r.setGameObjectAt(0, 4, bl);
    r.setGameObjectAt(3, 3, new Box());
    r2.setGameObjectAt(3, 3, new Kitten());

    Player p = new Player(01);
    gw.player = p;
    gw.mapLayout.clear();
    gw.mapLayout.put(01, r);
    gw.mapLayout.put(02, r2);
    gw.mapLayout.put(03, r3);
    gw.mapLayout.put(04, r4);

  }
}
