package mapeditor;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Direction;
import gameworld.Door;
import gameworld.GameObject;
import gameworld.GameWorld;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Player;
import gameworld.PuzzleRoom;
import gameworld.Room;
import gameworld.Switch;
import gameworld.Table;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
 * Grid of the editor.
 *
 * @author Rachel Hawkins
 *
 */
@SuppressWarnings("serial")
public class Grid extends JPanel {
  private MapFrame gui;

  // to hold initialized rooms
  private Room[][] grid;
  private Player player = null;


  // how big rooms will be on screen
  /**
   * Scale of each room square.
   */
  public static final int ROOM_SCALE = 130;
  /**
   * Width of grid object.
   */
  public static final int WIDTH = 390;
  /**
   * Height of grid object.
   */
  public static final int HEIGHT = 520;
  /**
   * Singular square scale.
   */
  public static final int SQUARE_SCALE = 26;

  private int selectedX;
  private int selectedY;
  private int mouseX;
  private int mouseY;

  /**
   * Room Color.
   */
  public final Color inRoom = new Color(66, 244, 161);

  /**
   * Common constructor, initialises grid.
   *
   * @param gui easy access to enclosing frame.
   *
   */
  public Grid(MapFrame gui) {
    this.gui = gui;
    initializeGrid();
  }

  /**
   * Testing constructor.
   */
  public Grid() {
    initializeGrid();
  }

  /**
   * Initialises a new grid object.
   */
  public void initializeGrid() {
    this.grid = new Room[3][4];

    // initialize room grid
    int no = 1;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        // No room name this is set when room is initialised.
        this.grid[i][j] = new Room(no, null);
        no++;
      }
    }
  }

  /**
   * Method overrides the original paint method of JPanel, to provide our own painting thing.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // ROOMS
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        // if initialized
        if (this.grid[i][j].getValid()) {
          int originX = i * ROOM_SCALE;
          int originY = j * ROOM_SCALE;

          g.setColor(inRoom);
          g.fillRect(originX, originY, ROOM_SCALE, ROOM_SCALE);

          // import and try to draw template
          try {
            if (this.grid[i][j] instanceof PuzzleRoom) {
              PuzzleRoom r = (PuzzleRoom) this.grid[i][j];
              if (r.getType() == 1) {
                BufferedImage bi = ImageIO.read(new File("src/mapeditor/puzzleImg1.png"));
                g.drawImage(bi, originX, originY, inRoom, null);
              }
              if (r.getType() == 2) {
                BufferedImage bi = ImageIO.read(new File("src/mapeditor/puzzleImg2.png"));
                g.drawImage(bi, originX, originY, inRoom, null);
              }
              if (r.getType() == 3) {
                BufferedImage bi = ImageIO.read(new File("src/mapeditor/puzzleImg3.png"));
                g.drawImage(bi, originX, originY, inRoom, null);
              }
              if (r.getType() == 4) {
                BufferedImage bi = ImageIO.read(new File("src/mapeditor/puzzleImg4.png"));
                g.drawImage(bi, originX, originY, inRoom, null);
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          ;

          // ITEMS
          for (int s = 0; s < 5; s++) {
            for (int t = 0; t < 5; t++) {
              int originSx = (s * SQUARE_SCALE) + originX;
              int originSy = (t * SQUARE_SCALE) + originY;

              // player
              if (grid[i][j].getContainsPlayer() && s == 2 && t == 2) {
                g.setColor(Color.BLUE);
                g.drawOval(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }

              if (grid[i][j].getItemAt(s, t) instanceof Door) {
                // CHECK FOR DIFFERENT COLORS
                Door door = (Door) grid[i][j].getItemAt(s, t);
                if (door.getDoorColor().equals("red")) {
                  g.setColor(Color.RED);
                } else if (door.getDoorColor().equals("blue")) {
                  g.setColor(Color.BLUE);
                } else if (door.getDoorColor().equals("green")) {
                  g.setColor(Color.GREEN);
                } else if (door.getDoorColor().equals("yellow")) {
                  g.setColor(Color.YELLOW);
                } else {
                  g.setColor(Color.WHITE);
                }
                g.fillRect(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Kitten) {
                g.setColor(Color.ORANGE);
                g.fillOval(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Box) {
                g.setColor(new Color(224, 123, 40));
                g.fillRect(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Chest) {
                g.setColor(new Color(146, 37, 163));
                g.fillRect(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Chair) {
                g.setColor(Color.PINK);
                g.fillOval(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Switch) {
                g.setColor(Color.YELLOW);
                g.fillRect(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Table) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
              if (grid[i][j].getItemAt(s, t) instanceof Key) {
                g.setColor(Color.BLUE);
                g.fillOval(originSx, originSy, SQUARE_SCALE, SQUARE_SCALE);
              }
            }
          }

          // ROOM LINES
          g.setColor(Color.WHITE);
          for (int l = 1; l < 5; l++) {
            // horizontal
            g.drawLine(originX, originY + (l * SQUARE_SCALE), originX + ROOM_SCALE,
                originY + (l * SQUARE_SCALE));
            // vertical
            g.drawLine(originX + (l * SQUARE_SCALE), originY, originX + (l * SQUARE_SCALE),
                originY + ROOM_SCALE);
          }

        }
      }
    }

    // LINES
    g.setColor(Color.BLUE);

    for (int i = 0; i < 4; i++) {
      g.drawLine(i * ROOM_SCALE, 0, i * ROOM_SCALE, HEIGHT);
    }

    for (int j = 0; j < 5; j++) {
      g.drawLine(0, j * ROOM_SCALE, WIDTH, j * ROOM_SCALE);
    }
  }

  /**
   * Creates room validity, which when the draw methods run will change color shown.
   *
   * @param x pos
   * @param y pos
   * @param name name of room
   * @return True if the room was successfully placed.
   */
  public boolean addRoom(int x, int y, String name) {
    // sets the position for remove button
    this.setSelectedX(x);
    this.setSelectedY(y);

    // sets first room as start if editor wants to change player room
    if (this.player == null) {
      addPlayer(x, y);
    }

    this.grid[x][y].setValid(true);
    this.grid[x][y].setRoomName(name);
    return true;
  }

  /**
   * Creates a template room in the square given.
   *
   * @param x x pos of selected room
   * @param y y pos of room
   * @param room selected template button
   */
  public void addTemplateRoom(int x, int y, int room) {
    // sets the position for remove button
    this.setSelectedX(x);
    this.setSelectedY(y);

    PuzzleRoom proom = new PuzzleRoom(grid[x][y].getRoomId(), room);
    grid[x][y].setValid(true);
    grid[x][y] = proom;
  }

  /**
   * Removes the selected room, only called if there is not a selected item.
   */
  public void removeRoom() {
    Room old = grid[selectedX][selectedY];
    grid[selectedX][selectedY] = new Room(old.getRoomId(), old.getRoomName());
    grid[selectedX][selectedY].setValid(false);
  }

  /**
   * Adds player, or moves depending on whether the player has been placed before.
   *
   * @param x room x
   * @param y room y
   */
  public void addPlayer(int x, int y) {

    // if player exists already, change the room
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (grid[i][j].getContainsPlayer()) {
          grid[i][j].setContainsPlayer(false);
        }
      }
    }

    grid[x][y].setContainsPlayer(true);
    this.player = new Player(this.grid[x][y].getRoomId());
  }


  /**
   * Adds two doors the correspond with each other.
   *
   * @param x room x.
   * @param y room y.
   * @param xclick clicked x position.
   * @param yclick clicked y position.
   * @param color : String representing the color of door.
   * @return true : if item was added, false : if item was rejected.
   */
  public boolean addDoor(int x, int y, float xclick, float yclick, String color) {
    // sets positions for remove button
    this.setSelectedX(x);
    this.setSelectedY(y);
    this.setMouseX((int) xclick);
    this.setMouseY((int) yclick);

    Room from = grid[x][y];

    // calculate which Direction to get
    Direction dir =
        calcDoorDirection(from.getRoomId(), findX(x, (int) xclick), findY(y, (int) yclick));
    if (dir == null) {
      gui.setInfoBox("Door cannot be placed here.");
      return false;
    }

    System.out.println(xclick + " / " + yclick);
    // check room and direction has a next door room
    int toId = getNeighbor(from.getRoomId(), dir);

    // out edge
    if (toId == -1) {
      gui.setInfoBox("Cannot place a door on outer perimeter.");
      return false;
    }
    // no neighbor
    if (toId == 0) {
      gui.setInfoBox("No room door can lead to.");
      return false;
    }

    // set door
    from.setExit(dir, toId, color);
    // set other side
    Room to = getRoomFromId(toId);
    to.setExit(Direction.getOppisite(dir), from.getRoomId(), color);
    gui.setInfoBox("Door placed");

    return true;
  }

  private Direction calcDoorDirection(int id, int x, int y) {
    if (x == 2 && y == 0) {
      return Direction.NORTH;
    } else if (x == 2 && y == 4) {
      return Direction.SOUTH;
    } else if (x == 0 && y == 2) {
      return Direction.WEST;
    } else if (x == 4 && y == 2) {
      return Direction.EAST;
    }

    return null;
  }

  /**
   * gets neighbor using original room and direction moving in.
   *
   * @param fromId from room id
   * @param dir direction moving,
   * @return to room id.
   */
  public int getNeighbor(int fromId, Direction dir) {
    // direction goes outside of bounds
    System.out.println(dir + " /" + fromId);
    if (dir == Direction.NORTH) {
      if (fromId == 1 || fromId == 5 || fromId == 9) {
        return -1;
      }
    }
    if (dir == Direction.SOUTH) {
      if (fromId == 4 || fromId == 8 || fromId == 12) {
        return -1;
      }
    }

    if (dir == Direction.WEST) {
      if (fromId == 1 || fromId == 2 || fromId == 3 || fromId == 4) {
        return -1;
      }
    }
    if (dir == Direction.EAST) {
      if (fromId == 9 || fromId == 10 || fromId == 11 || fromId == 12) {
        return -1;
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        // room match found
        if (grid[i][j].getRoomId() == fromId) {
          if (dir == Direction.NORTH && grid[i][j - 1].getValid()) {
            return grid[i][j - 1].getRoomId();
          }
          if (dir == Direction.SOUTH && grid[i][j + 1].getValid()) {
            return grid[i][j + 1].getRoomId();
          }
          if (dir == Direction.WEST && grid[i - 1][j].getValid()) {
            return grid[i - 1][j].getRoomId();
          }
          if (dir == Direction.EAST && grid[i][j + 1].getValid()) {
            return grid[i + 1][j].getRoomId();
          }
        }
      }
    }
    // has no neighbor
    return 0;
  }

  /**
   * Adds item to the room at x,y.
   *
   * @param x room x
   * @param y room y
   * @param xclick click y
   * @param yclick click x
   * @param object item to be placed.
   */
  public void addItem(int x, int y, int xclick, int yclick, GameObject object) {
    // sets positions for remove button
    this.setSelectedX(x);
    this.setSelectedY(y);
    this.setMouseX((int) xclick);
    this.setMouseY((int) yclick);

    if (!grid[x][y].getValid()) {
      gui.setInfoBox("Room has not been validated yet.");
      return;
    }

    // not place able
    GameObject obj = getItemAt(x, y, xclick, yclick);
    if (obj != null && !obj.isContainer() && !object.isPickupable()) {
      gui.setInfoBox("You can not place this object on top of another");
      return;
    }

    final Room from = grid[x][y];

    // find the x and y of the actual room from a method
    xclick = findX(x, xclick);
    yclick = findY(y, yclick);

    // place object in container
    if (obj != null && obj.isContainer() && object.isPickupable()) {
      if (obj instanceof Chest) {
        ((Chest) obj).addItemToChest(object);
      }
    }

    from.setGameObjectAt(xclick, yclick, object);
  }

  /**
   * Removes the last selected item from the specified location.
   */
  public void removeItem() {
    Room from = grid[selectedX][selectedY];

    // find the x and y of the actual room from a method
    int xclick = findX(selectedX, mouseX);
    int yclick = findY(selectedY, mouseY);

    from.setGameObjectAt(xclick, yclick, null);
  }

  /**
   * find the room index of a mouse click.
   *
   * @param x is the grid index (room)
   * @param xclick the actual click
   * @return the inner x position
   */
  public int findX(int x, int xclick) {
    return (int) (xclick - (x * 130)) / 26;
  }

  /**
   * Gets the y index of the room.
   *
   * @param y room y
   * @param yclick index y
   * @return y index of the room given
   */
  public int findY(int y, int yclick) {
    return (int) (yclick - (y * 130)) / 26;
  }

  /**
   * Creates the save object, needs a room list and player to create most basic map.
   *
   * @return the game world object to save.
   */
  public GameWorld exportSave() {
    HashMap<Integer, Room> roomList = new HashMap<Integer, Room>();

    // sets rooms into hash map
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (grid[i][j].getValid()) {
          roomList.put(grid[i][j].getRoomId(), grid[i][j]);
        }
      }
    }

    // create new game world
    GameWorld world = new GameWorld();

    // add rooms
    world.setMapLayout(roomList);

    // add player
    world.setPlayer(this.player);
    world.setStartingRoomId(player.getCurrentRoomId());

    return world;
  }

  /**
   * Resets the grid.
   */
  public void clear() {
    initializeGrid();
    this.player = null;
    revalidate();
    repaint();
  }

  // Getters
  /**
   * Getter of 2D rooms.
   *
   * @return the 2D grid of rooms
   */
  public Room[][] getGrid() {
    return this.grid;
  }

  /**
   * Getter of roomID.
   *
   * @param x position x
   * @param y position y
   * @return Room id
   */
  public int getRoomId(int x, int y) {
    return this.grid[x][y].getRoomId();
  }

  /**
   * Returns a room from the integer room id parameter.
   *
   * @param id id of room
   * @return Room object.
   */
  public Room getRoomFromId(int id) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        if (grid[i][j].getRoomId() == id) {
          return grid[i][j];
        }
      }
    }
    return null;
  }

  /**
   * Passes parameters through to the room item at().
   *
   * @param x roomx
   * @param y roomy
   * @param xclick x position.
   * @param yclick y position.
   * @return Game object object returned by room object.
   */
  public GameObject getItemAt(int x, int y, int xclick, int yclick) {
    return grid[x][y].getItemAt(findX(x, xclick), findY(y, yclick));
  }

  /**
   * SelectedX getter.
   *
   * @return selectedX
   */
  public int getSelectedX() {
    return selectedX;
  }

  /**
   * selectedX setter.
   *
   * @param selectedX new selection.
   */
  public void setSelectedX(int selectedX) {
    this.selectedX = selectedX;
  }

  /**
   * SelectedY getter.
   *
   * @return selectedY new selection.
   */
  public int getSelectedY() {
    return selectedY;
  }

  /**
   * SelectedY setter.
   *
   * @param selectedY new selectedY
   */
  public void setSelectedY(int selectedY) {
    this.selectedY = selectedY;
  }

  /**
   * getter mousex position.
   *
   * @return last mousex position.
   */
  public int getMouseX() {
    return mouseX;
  }

  /**
   * setter of last mouse x position.
   *
   * @param mouseX last mousex position
   */
  public void setMouseX(int mouseX) {
    this.mouseX = mouseX;
  }

  /**
   * getter mousey position.
   *
   * @return last mousey position.
   */
  public int getMouseY() {
    return mouseY;
  }

  /**
   * setter of last mouse y position.
   *
   * @param mouseY last mousey pos.
   */
  public void setMouseY(int mouseY) {
    this.mouseY = mouseY;
  }



}
