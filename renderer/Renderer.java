package renderer;

import application.GuiFrame;
import gameworld.Direction;
import gameworld.GameObject;
import gameworld.Location;
import gameworld.Pair;
import gameworld.Player;
import gameworld.Room;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import renderer.drawableelements.PermanentElement;
import renderer.drawableelements.PlaceableElement;
import renderer.drawableelements.permanentelements.FloorElement;
import renderer.drawableelements.permanentelements.FrontWallElement;
import renderer.drawableelements.permanentelements.LeftWallElement;
import renderer.drawableelements.permanentelements.RightWallElement;

/**
 * This Renderer class renders the Room of the game.
 *
 * @author joewill
 *
 */
public class Renderer extends JPanel implements MouseListener {

  /**
   * The width of the viewable areathis Renderer.
   */
  public static final int SCREEN_WIDTH = 1080;

  /**
   * The height of the viewable area this Renderer.
   */
  public static final int SCREEN_HEIGHT = 720;

  /*
   * Serialisable ID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * Colours of walls and floor.
   */
  private static final Color frontWallColour = new Color(36, 49, 71);
  private static final Color sideWallColour = new Color(30, 40, 60);
  private static final Color floorColour = new Color(32, 35, 40);

  /*
   * Default dimensions of this Renderer.
   */
  private static final int defaultFrontWallWidth = 1000;
  private static final int defaultFrontWallHeight = 600;

  /*
   * Values used when scaling scene based of player position.
   */
  private static final int heightScaleValue = 30;
  private static final int widthScaleValue = 50;

  /*
   * Value for center of row and column.
   */
  private final int centerOfRoom = 2;

  /*
   * Value used to calucate offset when shift left and right of room.
   */
  private final int offsetFactor = 100;
  /*
   * Placeable elements in the room to be drawn.
   */
  private List<PlaceableElement> placeablesInRoom;

  /*
   * Current Room this Renderer is representing.
   */
  private Room room;

  /*
   * Player character in the room.
   */
  private Player player;

  /*
   * JFrame holding this Renderer.
   */
  private final GuiFrame frame;

  /**
   * Construct a Renderer with a GuiFrame to hold this Renderer.
   *
   * @param frame JFrame containing this Renderer.
   */
  public Renderer(GuiFrame frame) {
    this.frame = frame;
    this.initialiseFrame();
  }

  /**
   * playerPosition Initialise this Renderer, setting the dimensions.
   */
  public void initialiseFrame() {

    super.setVisible(true);
    addMouseListener(this);
    setMaximumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));// TODO
    setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
  }


  /**
   * Repaints the scene based on the updated Room and Player.
   *
   * @param g Graphics to be drawn on.
   */
  @Override
  public void paintComponent(Graphics g) {
    System.out.println(2);
    if (this.room == null || this.player == null) {
      return;
    }
    Direction playerDirection = player.getFacing();

    Pair<Integer, Integer> playerPosition =
        RotateHelper.rotatePlayer(player.getLocationInRoom(), playerDirection);

    int px = playerPosition.getKey();
    int py = playerPosition.getValue();

    room.getLocationAt(px, py).togglePlayer();

    System.out.println(" relative player pos " + px + " " + py);
    Location[][] scene = RotateHelper.rotateRoom(room.getRoomLayout(), playerDirection);



    // rotate view of the room base on direction Player is facing.


    // amount to shift the view of room left/right based on Player postion.
    int xoffset = -(px - this.centerOfRoom) * (this.offsetFactor);

    int midXPoint = SCREEN_WIDTH / 2; // mid x point of the room.

    // Draw fixed elements in of the room.
    this.renderFixedElements(scene, midXPoint, defaultFrontWallWidth, defaultFrontWallHeight, px,
        py, g, xoffset);

    int frontWallHeight = defaultFrontWallHeight - (py * heightScaleValue);
    int frontWallWidth = defaultFrontWallWidth - (py * widthScaleValue);
    this.renderPlaceableElements(scene, midXPoint, px, py, frontWallWidth, frontWallHeight, g,
        xoffset);

  }

  /**
   * Sets the Room and Player to be rendered. The PLayer is used to determine the shifting of the
   * room when rendered.
   *
   * @param room Room to be rendered.
   * @param player Player in the room.
   */
  public void renderImage(Room room, Player player) {
    System.out.println(1);
    this.room = room;
    this.player = player;
    this.repaint();

  }


  /**
   * Renders all the Fixed elements of the Room. Walls and floor.
   *
   * @param room Room to render, may have been rotated.
   * @param midXPoint center x coordinate value on the canvas.
   * @param wallWidth width of the wall.
   * @param wallHeight height of the wall.
   * @param colour color of the elements.
   * @param playerX player x position.
   * @param playerY player y position.
   * @param dir player direction.
   * @param g rendering graphics.
   */
  private void renderFixedElements(Location[][] room, int midXPoint, int wallWidth, int wallHeight,
      int playerX, int playerY, Graphics g, int xoffset) {

    // width and height todraw back
    int frontWallWidth = defaultFrontWallWidth - (playerY * widthScaleValue);

    // wall Size increases as player move closer.
    int frontWallHeight = defaultFrontWallHeight - (playerY * heightScaleValue);

    // Floor
    this.renderFloor(midXPoint, frontWallWidth, frontWallHeight, floorColour, xoffset, playerY, g);
    // front wall

    this.renderFrontWall(midXPoint, frontWallWidth, frontWallHeight, frontWallColour, g, xoffset);

    // left wall
    this.renderLeftWall(midXPoint, frontWallWidth, frontWallHeight, sideWallColour, g, xoffset);

    // rightwall
    this.renderRightWall(midXPoint, frontWallWidth, frontWallHeight, sideWallColour, g, xoffset);

  }

  /**
   * Render front facing wall.
   *
   * @param midXPoint Mid x point on the screen.
   * @param frontWallWidth Width of front wall on screen.
   * @param frontWallHeight Height of front wall on screen.
   * @param colour Colour of the wall.
   * @param g Graphics to draw on.
   */
  private void renderFrontWall(int midXPoint, int frontWallWidth, int frontWallHeight, Color colour,
      Graphics g, int xoffset) {
    g.setColor(colour);
    FrontWallElement frontWall =
        new FrontWallElement(midXPoint, frontWallWidth, frontWallHeight, xoffset, frontWallColour);
    frontWall.drawElement(g);

  }

  /**
   * Render left wall.
   *
   * @param midXPoint Mid x point on the screen.
   * @param frontWallWidth Width of front wall on screen.
   * @param frontWallHeight Height of front wall on screen.
   * @param colour Colour of the wall.
   * @param g Graphics to draw on.
   */
  private void renderLeftWall(int midXPoint, int frontWallWidth, int frontWallHeight, Color colour,
      Graphics g, int xoffset) {
    PermanentElement leftWall =
        new LeftWallElement(midXPoint, frontWallWidth, frontWallHeight, xoffset, colour);
    leftWall.drawElement(g);

  }

  /**
   * Render right wall.
   *
   * @param midXPoint Mid x point on the screen.
   * @param frontWallWidth Width of front wall on screen.
   * @param frontWallHeight Height of front wall on screen.
   * @param colour Colour of the wall.
   * @param g Graphics to draw on.
   */
  private void renderRightWall(int midXPoint, int frontWallWidth, int frontWallHeight, Color colour,
      Graphics g, int xoffset) {

    PermanentElement rightWall =
        new RightWallElement(midXPoint, frontWallWidth, frontWallHeight, xoffset, colour);
    rightWall.drawElement(g);

  }

  /**
   * Render floor.
   *
   * @param midXPoint Mid x point on the screen.
   * @param frontWallWidth Width of front wall on screen.
   * @param frontWallHeight Height of front wall on screen.
   * @param colour Colour of the wall.
   * @param g Graphics to draw on.
   */
  private void renderFloor(int midXPoint, int frontWallWidth, int frontWallHeight, Color colour,
      int xoffset, int playerRow, Graphics g) {
    g.setColor(colour);

    PermanentElement floor =
        new FloorElement(midXPoint, frontWallWidth, frontWallHeight, xoffset, colour, playerRow);
    floor.drawElement(g);

  }

  /**
   * Render all the GameObjects in the room.
   *
   * @param scene Room to render.
   * @param px Column of room Play is at.
   * @param py Row of room Play is at.
   */
  private void renderPlaceableElements(Location[][] scene, int midXPoint, int px, int py,
      int frontWallWidth, int frontWallHeight, Graphics g, int xoffset) {
    // TODO for each ro
    // dive height by py
    this.placeablesInRoom = new ArrayList<>();

    //make factory to produce placeables
    PlaceableFactory placeableFactory = new PlaceableFactory(px, py, xoffset,
        this.findCellHeight(frontWallHeight, py), frontWallWidth, frontWallHeight);//// TODO pplayer

    this.findCellHeight(frontWallHeight, py);

    //draw the furthest objects first.
    for (int row = 0; row < py; row++) {
      int cellWidth =
          this.caluclateFloorWidth(frontWallWidth, frontWallHeight, py, row, xoffset) / 5;
      for (int col = 0; col < 5; col++) {
        GameObject element = scene[col][row].getItem();
        if (element != null) {
          PlaceableElement placeable = placeableFactory.makePlaceable(element, col, row, cellWidth);
          placeable.drawElement(g);
          this.placeablesInRoom.add(placeable);

        }

      }

    }

  }

  /**
   * Calculate the width of floor at the specified row.
   *
   * @param frontWallWidth Wisth of front wall.
   * @param frontWallHeight Height of front wall.
   * @param playerRow row of the player.
   * @param objectRow object of the row.
   * @param xoffset Amount to horizontally shift the drawing.
   * @return Width of the floor.
   */
  private int caluclateFloorWidth(int frontWallWidth, int frontWallHeight, int playerRow,
      int objectRow, int xoffset) {
    // double leftbase = xoffset;
    // double rightBase = getWidth() - (getWidth() + xoffset);

    int baseWidth = getWidth();// - leftbase - rightBase;

    double topWidth = frontWallWidth;
    // System.out.println("top "+frontWallWidth);
    double height = getHeight() - frontWallHeight;

    double y = height / playerRow * objectRow - height / (playerRow + 1) / 2;
    return (int) ((int) (height - y) / height * topWidth + y / height * baseWidth);

  }


  /*
   * Calculate the height of cells to be drawn on the room.
   */
  private int findCellHeight(int frontWallHeight, int playerRow) {

    return playerRow == 0 ? 0 : (Renderer.SCREEN_HEIGHT - frontWallHeight) / (playerRow);
  }

  /**
   * Get the GameObject of the PlaceableElement that was clicked on.
   *
   * @param point Point that was clicked on.
   * @return GameObject that was clicked on otherwise null.
   *
   */
  private GameObject getPlaceableClickedOn(Point point) {
    for (int i = this.placeablesInRoom.size() - 1; i >= 0; i--) {
      PlaceableElement p = placeablesInRoom.get(i);
      if (p.pointIsOnElement(point)) {
        return p.getGameObject();
      }
    }
    return null;
  }

  /*
   * Send the Object of the element that was clicked on.
   */
  private boolean passClickedOnElement(Point point) {
    GameObject gameObject = getPlaceableClickedOn(point);
    if (gameObject != null) {
      this.frame.sendObject(gameObject);
      return true;
      // System.out.println("clicked on " + gameObject.toString());
    }
    return false;
  }

  /**
   * On mouse release check if a PlaceableElement has been selected.
   *
   * @param e MouseEvent containing the coodinates of a re release.
   */
  @Override
  public void mouseReleased(MouseEvent e) {

    this.passClickedOnElement(new Point(e.getX(), e.getY()));
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

}
