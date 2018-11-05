import static org.junit.jupiter.api.Assertions.assertEquals;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Door;
import gameworld.GameObject;
import gameworld.Pair;
import gameworld.PuzzleRoom;
import gameworld.Switch;
import gameworld.Table;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import persistence.ChestPer;
import persistence.Parser;
import persistence.RoomPer;




/**
 * Tests persistence.
 * @author yangkevi
 *
 */
public class PersistenceTest {

  /**
   * Tests room 1.
   */
  @Test
  public void room_Test_01() {

    gameworld.Room room = new gameworld.Room(1, "green");
    persistence.RoomPer roomTest = new RoomPer();
    roomTest.assign(room);

    assertEquals(new Integer(1), roomTest.getRoomId());

  }

  /**
   * Tests room 2.
   */
  @Test
  public void room_Test_02() {


    gameworld.Room room = new gameworld.Room(1, "green");
    persistence.RoomPer roomTest = new RoomPer();
    gameworld.Room roomTest2 = new gameworld.Room();
    roomTest.assign(room);
    roomTest2 = roomTest.reassign();
    assertEquals(roomTest2.getRoomName().toString(), roomTest.getRoomName().toString());

  }

  /**
   * Chest Test.
   */
  @Test
  public void chest_Test_01() {


    gameworld.Chest chest = new gameworld.Chest("green");
    persistence.ChestPer chestTest = new ChestPer();
    chestTest.assign(chest);

    assertEquals(new String("green"), chestTest.getColor());

  }

  /**
   * Tests chest 2.
   */
  @Test
  public void chest_Test_02() {


    gameworld.Chest chest = new gameworld.Chest("green");
    persistence.ChestPer chestTest = new ChestPer();
    chestTest.assign(chest);
    gameworld.Chest chest2 = (Chest) chestTest.reassign();
    assertEquals(new String("green"), chest2.getChestColor());

  }

  /**
   * Tests assign room.
   */
  @Test
  public void testAssign_Room() {

    gameworld.Room room = new gameworld.Room(1, "green");

    gameworld.Chest chest = new gameworld.Chest("red");

    ArrayList<gameworld.Pair<gameworld.Direction, Integer>> exitList = new ArrayList<>();
    gameworld.Direction dir = gameworld.Direction.randomDir();
    exitList.add(new gameworld.Pair<gameworld.Direction, Integer>(dir, 1));
    room.setExits(exitList);

    gameworld.Key key = new gameworld.Key("red");
    chest.addItemToChest(key);
    gameworld.Location[][] loc = new gameworld.Location[2][2];
    gameworld.Location location = new gameworld.Location();
    location.setItem(chest);

    for (int i = 0; i < loc.length; i++) {
      for (int j = 0; j < loc.length; j++) {
        loc[i][j] = new gameworld.Location();
      }
    }

    room.setRoomLayout(loc);
    room.setGameObjectAt(0, 1, chest);

    persistence.RoomPer roomTest = new RoomPer();
    roomTest.assign(room);

    assertEquals(new Integer(1), roomTest.getRoomId());


  }

  /**
   * Tests room.
   */
  @Test
  public void testreAssign_Room() {
    gameworld.Room room = new gameworld.Room(1, "green");
    gameworld.Chest chest = new gameworld.Chest("red");
    ArrayList<gameworld.Pair<gameworld.Direction, Integer>> exitList = new ArrayList<>();
    gameworld.Direction dir = gameworld.Direction.randomDir();
    exitList.add(new gameworld.Pair<gameworld.Direction, Integer>(dir, 1));
    room.setExits(exitList);

    gameworld.Key key = new gameworld.Key("red");
    chest.addItemToChest(key);
    gameworld.Location[][] loc = new gameworld.Location[2][2];
    gameworld.Location location = new gameworld.Location();
    location.setItem(chest);
    for (int i = 0; i < loc.length; i++) {
      for (int j = 0; j < loc.length; j++) {
        loc[i][j] = new gameworld.Location();
      }
    }

    room.setRoomLayout(loc);
    room.setGameObjectAt(0, 1, chest);

    persistence.RoomPer roomTest = new RoomPer();
    roomTest.assign(room);
    gameworld.Room room1 = new gameworld.Room(0, "");
    room1 = roomTest.reassign();
    assertEquals(1, room1.getRoomId());

  }

  /**
   * Tests box.
   */
  @Test
  public void testAssign_Box() {
    gameworld.Box box = new gameworld.Box();
    box.setPushable(true);
    persistence.BoxPer testbox = new persistence.BoxPer();
    testbox.assign(box);
    assertEquals(true, testbox.isPushable());


  }

  /**
   * Tests reassign box.
   */
  @Test
  public void testReAssign_Box() {
    gameworld.Box box = new gameworld.Box();
    box.setPushable(true);

    persistence.BoxPer testbox = new persistence.BoxPer();
    testbox.assign(box);
    gameworld.Box box2 = new gameworld.Box();
    box2 = (Box) testbox.reassign();
    assertEquals(true, box2.isPushable());


  }

  /**
   * Tests chair.
   */
  @Test
  public void testAssign_Chair() {
    gameworld.Chair chair = new gameworld.Chair();
    chair.setPushable(true);
    chair.setPushable(true);
    chair.setPickupable(true);
    chair.setLocked(false);
    persistence.ChairPer testchair = new persistence.ChairPer();
    testchair.assign(chair);
    assertEquals(true, testchair.isPushable());
    assertEquals(false, testchair.isLocked());


  }

  /**
   * Tests chair.
   */
  @Test
  public void testReAssign_Chair() {
    gameworld.Chair chair = new gameworld.Chair();
    chair.setPushable(true);
    chair.setPushable(true);
    chair.setPickupable(true);
    chair.setLocked(false);
    persistence.ChairPer testchair = new persistence.ChairPer();
    gameworld.Chair chair2 = new gameworld.Chair();
    testchair.assign(chair);
    chair2 = (Chair) testchair.reassign();
    assertEquals(true, chair2.isPushable());
    assertEquals(false, chair2.isLocked());

  }

  /**
   * Tests table.
   */
  @Test
  public void testAssign_Table() {
    gameworld.Table table = new gameworld.Table();
    table.setPushable(true);
    table.setPushable(true);
    table.setPickupable(true);
    table.setLocked(false);
    persistence.TablePer testtable = new persistence.TablePer();
    testtable.assign(table);
    assertEquals(true, testtable.isPushable());
    assertEquals(false, testtable.isLocked());


  }

  /**
   * Tests table.
   */
  @Test
  public void testReAssign_Table() {
    gameworld.Table table = new gameworld.Table();
    table.setPushable(true);
    table.setPushable(true);
    table.setPickupable(true);
    table.setLocked(false);
    persistence.TablePer testtable = new persistence.TablePer();
    testtable.assign(table);
    gameworld.Table table2 = new gameworld.Table();
    table2 = (Table) testtable.reassign();
    assertEquals(true, table2.isPushable());
    assertEquals(false, table2.isLocked());


  }

  /**
   * Tests player.
   */
  @Test
  public void testAssign_Player() {
    gameworld.Player player = new gameworld.Player(1);
    gameworld.Room room = new gameworld.Room(1, "Red");
    player.setCurrentRoomId(room.getRoomId());
    player.setFacing(gameworld.Direction.EAST);
    gameworld.Chest chestin = new gameworld.Chest("green");
    Pair<Integer, Integer> location = new Pair<Integer, Integer>(2, 2);
    player.setRoomLocation(location);
    GameObject[] playerInventory = new GameObject[10];
    playerInventory[0] = chestin;
    player.setPlayerInventory(playerInventory);
    persistence.PlayerPer testplayer = new persistence.PlayerPer();
    testplayer.assign(player);
    assertEquals(testplayer.getCurrentRoomId(), 1);
    assertEquals(testplayer.getFacing(), persistence.Direction.EAST);
    assertEquals(testplayer.getPlayerInventory()[0].getClass(),
        new persistence.ChestPer().getClass());
  }


  /**
   * Tests player 2.
   */
  @Test
  public void testAssign2_Player() {
    gameworld.Player player = new gameworld.Player(1);
    gameworld.Room room = new gameworld.Room(1, "Red");
    player.setCurrentRoomId(room.getRoomId());
    player.setFacing(gameworld.Direction.WEST);
    gameworld.Chest chestin = new gameworld.Chest("green");
    Pair<Integer, Integer> location = new Pair<Integer, Integer>(2, 2);
    player.setRoomLocation(location);
    GameObject[] playerInventory = new GameObject[10];
    playerInventory[0] = chestin;
    player.setPlayerInventory(playerInventory);
    persistence.PlayerPer testplayer = new persistence.PlayerPer();
    testplayer.assign(player);
    assertEquals(testplayer.getCurrentRoomId(), 1);
    assertEquals(testplayer.getFacing(), persistence.Direction.WEST);
    assertEquals(testplayer.getPlayerInventory()[0].getClass(),
        new persistence.ChestPer().getClass());
  }


  /**
   * Tests player 3.
   */
  @Test
  public void testAssign3_Player() {
    gameworld.Player player = new gameworld.Player(1);
    gameworld.Room room = new gameworld.Room(1, "Red");
    player.setCurrentRoomId(room.getRoomId());
    player.setFacing(gameworld.Direction.NORTH);
    gameworld.Chest chestin = new gameworld.Chest("green");
    Pair<Integer, Integer> location = new Pair<Integer, Integer>(2, 2);
    player.setRoomLocation(location);
    GameObject[] playerInventory = new GameObject[10];
    playerInventory[0] = chestin;
    player.setPlayerInventory(playerInventory);
    persistence.PlayerPer testplayer = new persistence.PlayerPer();
    testplayer.assign(player);
    assertEquals(testplayer.getCurrentRoomId(), 1);
    assertEquals(testplayer.getFacing(), persistence.Direction.NORTH);
    assertEquals(testplayer.getPlayerInventory()[0].getClass(),
        new persistence.ChestPer().getClass());
  }


  /**
   * Tests player 4.
   */
  @Test
  public void testAssign4_Player() {
    gameworld.Player player = new gameworld.Player(1);
    gameworld.Room room = new gameworld.Room(1, "Red");
    player.setCurrentRoomId(room.getRoomId());
    player.setFacing(gameworld.Direction.SOUTH);
    gameworld.Chest chestin = new gameworld.Chest("green");
    Pair<Integer, Integer> location = new Pair<Integer, Integer>(2, 2);
    player.setRoomLocation(location);
    GameObject[] playerInventory = new GameObject[10];
    playerInventory[0] = chestin;
    player.setPlayerInventory(playerInventory);
    persistence.PlayerPer testplayer = new persistence.PlayerPer();
    testplayer.assign(player);
    assertEquals(testplayer.getCurrentRoomId(), 1);
    assertEquals(testplayer.getFacing(), persistence.Direction.SOUTH);
    assertEquals(testplayer.getPlayerInventory()[0].getClass(),
        new persistence.ChestPer().getClass());
  }

  /**
   * Tests player reassign.
   */
  @Test
  public void testReAssign_Player() {
    gameworld.Player player = new gameworld.Player(1);
    gameworld.Room room = new gameworld.Room(1, "Red");
    player.setCurrentRoomId(room.getRoomId());
    player.setFacing(gameworld.Direction.EAST);
    gameworld.Chest chestin = new gameworld.Chest("green");
    Pair<Integer, Integer> location = new Pair<Integer, Integer>(2, 2);
    player.setRoomLocation(location);
    GameObject[] playerInventory = new GameObject[10];
    playerInventory[0] = chestin;
    player.setPlayerInventory(playerInventory);
    persistence.PlayerPer testplayer = new persistence.PlayerPer();
    testplayer.assign(player);
    gameworld.Player player2 = new gameworld.Player(0);
    player2 = testplayer.reassign();

    assertEquals(player2.getCurrentRoomId(), 1);
    assertEquals(player2.getFacing(), gameworld.Direction.EAST);
    assertEquals(player2.getPlayerInventory()[0].getClass(),
        new gameworld.Chest("green").getClass());
  }

  /**
   * Tests puzzle.
   */
  @Test
  public void testAssign_PuzzleRoom() {
    gameworld.PuzzleRoom pr = new PuzzleRoom(1, 1);
    GameObject[] solution = new GameObject[10];
    solution[0] = new gameworld.Kitten();
    pr.setSolution(solution);
    pr.setDiscription("You found the kitten");
    persistence.PuzzleRoomPer persPuzzle = new persistence.PuzzleRoomPer();
    persPuzzle.assign(pr);

    assertEquals(persPuzzle.getDescription(), "You found the kitten");
    assertEquals(persPuzzle.getSolution()[0].getClass(), new persistence.KittenPer().getClass());
  }


  /**
   * Tests puzzle room.
   */
  @Test
  public void testReAssign_PuzzleRoom() {
    gameworld.PuzzleRoom pr = new PuzzleRoom(1, 1);
    GameObject[] solution = new GameObject[10];
    solution[0] = new gameworld.Kitten();
    pr.setSolution(solution);
    pr.setDiscription("You found the kitten");
    persistence.PuzzleRoomPer persPuzzle = new persistence.PuzzleRoomPer();
    persPuzzle.assign(pr);
    gameworld.PuzzleRoom pr2 = new PuzzleRoom(0, 0);
    pr2 = persPuzzle.reassign();
    assertEquals(pr2.getDescription(), "You found the kitten");
    assertEquals(pr2.getSolution()[0].getClass(), new gameworld.Kitten().getClass());
  }


  /**
   * TESTS DOOR.
   */
  @Test
  public void testAssign_Door() {
    gameworld.Door door = new gameworld.Door(1, "red");
    door.setContainer(true);
    door.setLocked(true);
    persistence.DoorPer doortest = new persistence.DoorPer();
    doortest.assign(door);
    assertEquals(doortest.getId(), 1);
    assertEquals(doortest.getColor(), "red");

  }


  /**
   * TESTS REASSIGN DOOR.
   */
  @Test
  public void testReAssign_Door() {
    gameworld.Door door = new gameworld.Door(1, "red");
    door.setContainer(true);
    door.setLocked(true);
    persistence.DoorPer doortest = new persistence.DoorPer();
    doortest.assign(door);
    gameworld.Door door2 = new gameworld.Door(0, "");
    door2 = (Door) doortest.reassign();
    assertEquals(door2.getDoorColor(), "red");

  }


  /**
   * Tests parser.
   */
  @Test
  public void testParser_marshall() {
    String gamexml = "./WholeTest2-jaxb.xml";
    gameworld.GameWorld gameworld = new gameworld.GameWorld();
    GameObject[] inventory = new GameObject[3];
    gameworld.Pair<Integer, Integer> loc = new gameworld.Pair<>(2, 2);


    GameObject chest = new gameworld.Chest("chest");
    inventory[0] = (GameObject) chest;
    gameworld.Player p = new gameworld.Player(1);
    p.setRoomLocation(loc);
    File file = new File(gamexml);

    gameworld.setPlayer(p);
    try {
      Parser.save(gameworld, file);
    } catch (JAXBException e) {

      e.printStackTrace();
    }
  }


  /**
   * Tests parser unmarshall.
   */
  @Test
  public void testParser_unmarshall() {
    String gamexml = "./WholeTest2-jaxb.xml";
    gameworld.GameWorld gameworld = new gameworld.GameWorld();
    GameObject[] inventory = new GameObject[3];
    gameworld.Pair<Integer, Integer> loc = new gameworld.Pair<>(2, 2);


    GameObject chest = new gameworld.Chest("chest");
    inventory[0] = (GameObject) chest;
    gameworld.Player p = new gameworld.Player(1);
    p.setRoomLocation(loc);
    File file = new File(gamexml);

    gameworld.setPlayer(p);
    try {
      Parser.save(gameworld, file);
    } catch (JAXBException e) {

      e.printStackTrace();
    }

    try {
      Parser.load(file);
    } catch (JAXBException e) {

      e.printStackTrace();
    }

  }

  /**
   * Tests switch.
   */
  @Test
  public void testAssign_Switch() {
    gameworld.Chest chest = new gameworld.Chest("red");
    chest.setContainer(true);
    chest.setLocked(false);
    persistence.SwitchPer swtest = new persistence.SwitchPer();
    Switch sw = new Switch(chest);
    swtest.assign(sw);
    assertEquals(sw.isLocked(), false);

  }


}
