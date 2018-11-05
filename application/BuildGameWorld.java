package application;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Direction;
import gameworld.Door;
import gameworld.GameWorld;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Player;
import gameworld.PuzzleRoom;
import gameworld.Room;
import gameworld.Switch;
import gameworld.Table;

/**
 * Builds the game world that is from setup.
 *
 * @author kevin
 *
 */
public class BuildGameWorld {
  GameWorld game;
  Room room1;
  Room room2;
  Room room3;
  Room room4;
  Room room5;
  Room room6;
  Room room7;
  Room room8;
  Room room9;
  Room room10;

  Chest chestRed;
  Chest chestBlue;
  Chest chestGreen;
  Chest chestYellow;
  Player player;
  Kitten kitten;
  Chair chair;
  Table table;
  Switch redSwitch;
  Switch greenSwitch;
  Switch blueSwitch;
  Switch yellowSwitch;
  PuzzleRoom puzzle;
  Key redKey;
  Key yellowKey;
  Key blueKey;
  Key greenKey;
  Door redD;
  Door greenD;
  Door blueD;
  Door yellowD;
  Box box;

  /**
   * Builds the initial game world.
   *
   */
  public BuildGameWorld() {
    game = new GameWorld();
    buildGame(game);

  }

  /**
   * initialize variables.
   * @param gw game world.
   */
  public void buildGame(GameWorld gw) {
    player = game.getPlayer();

    room1 = new Room(01, "a");
    room2 = new Room(02, "b");
    room3 = new Room(03, "c");
    room4 = new PuzzleRoom(04, 1);
    room5 = new Room(05, "d");
    room6 = new Room(06, "e");
    room7 = new PuzzleRoom(07, 3);
    room8 = new Room(8, "f");
    room9 = new Room(9, "g");
    room10 = new PuzzleRoom(10,2);


    kitten = new Kitten();
    table = new Table();
    box = new Box();
    chair = new Chair();

    chestRed = new Chest("Red");
    chestBlue = new Chest("Blue");
    chestYellow = new Chest("Yellow");
    chestGreen = new Chest("Green");
    chestYellow.addItemToChest(new Key("Green"));
    chestGreen.addItemToChest(new Kitten());
    chestRed.addItemToChest(new Key("Yellow"));
    chestBlue.addItemToChest(new Key("Red"));

    greenKey = new Key("green");
    redKey = new Key("red");
    blueKey = new Key("blue");
    yellowKey = new Key("yellow");
    redD = new Door(02,"red");
    yellowD = new Door(5,"yellow");
    greenD = new Door(7,"green");
    blueD = new Door(9,"blue");
    room1.setExit(Direction.NORTH, 03, "Yellow");
    room3.setExit(Direction.SOUTH, redD);
    room1.setExit(Direction.WEST, 02, "Green");
    room2.setExit(Direction.EAST, 01, "noColor");
    room3.setExit(Direction.NORTH, 4, "PLAIN");
    room4.setExit(Direction.EAST, yellowD); // puzzle 1
    room5.setExit(Direction.WEST, 4, "PLAIN");
    room5.setExit(Direction.NORTH, 6, "RED");
    room6.setExit(Direction.SOUTH, greenD);
    room7.setExit(Direction.EAST, 8, "PLAIN"); // puzzle 3
    room8.setExit(Direction.SOUTH, blueD);
    room9.setExit(Direction.NORTH, 10, "Blue");
    room10.setExit(Direction.WEST, 01, "PLAIN"); // puzzle 4

    redSwitch = new Switch(redD);
    blueSwitch = new Switch(blueD);
    greenSwitch = new Switch(greenD);
    yellowSwitch = new Switch(yellowD);

    room1.setGameObjectAt(0, 0, chestRed);
    room1.setGameObjectAt(4, 3, greenKey);
    room1.setGameObjectAt(1, 3, yellowKey);
    room1.setGameObjectAt(4, 4, chestBlue);
    room1.setGameObjectAt(4, 0, chestYellow);
    room1.setGameObjectAt(0, 4, chestGreen);
    room1.setGameObjectAt(4, 2, redKey);
    room2.setGameObjectAt(0, 0, chestRed);
    room2.setGameObjectAt(4, 0, chestBlue);
    room2.setGameObjectAt(2, 3, table);
    room3.setGameObjectAt(0, 0, chestRed);
    room3.setGameObjectAt(3, 0, redSwitch);
    room5.setGameObjectAt(0, 1, redKey);
    room6.setGameObjectAt(0, 0, chestRed);
    room5.setGameObjectAt(0, 4, chestYellow);
    room6.setGameObjectAt(4, 0, yellowKey);
    room6.setGameObjectAt(4, 4, greenKey);
    room5.setGameObjectAt(4, 4, table);
    room8.setGameObjectAt(0, 1, blueSwitch);
    room9.setGameObjectAt(0, 1, chestRed);
    room9.setGameObjectAt(4, 4, kitten);
    room9.setGameObjectAt(0, 0, blueKey);
    room8.setGameObjectAt(0, 0, table);
    room8.setGameObjectAt(4, 0, chair);
    room8.setGameObjectAt(0, 4, box);



    gw.getMapLayout().put(01, room1);
    gw.getMapLayout().put(02, room2);
    gw.getMapLayout().put(03, room3);
    gw.getMapLayout().put(04, room4);
    gw.getMapLayout().put(05, room5);
    gw.getMapLayout().put(06, room6);
    gw.getMapLayout().put(07, room7);
    gw.getMapLayout().put(8, room8);
    gw.getMapLayout().put(9, room9);
    gw.getMapLayout().put(10, room10);



  }
}

