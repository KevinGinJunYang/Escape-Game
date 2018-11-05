package renderer.drawableelements;

import gameworld.GameObject;
import java.awt.Point;
import java.awt.image.BufferedImage;

import renderer.Renderer;


/**
 * PlaceableElement is represents a DrawableElement that can be placed in the room.
 *
 * @author joewill
 *
 */
public abstract class PlaceableElement implements DrawableElement {

  /*
   * GameObject this PlaceableElement represents.
   */
  protected final GameObject gameElement;

  /*
   * Row of the Player in the room.
   */
  private final int playerRow;

  /*
   * Position of this PlaceableElement in the room.
   */
  private final int objectCol;
  private final int objectRow;

  /*
   * Width of the floor on the screen where this PlaceableElement is located.
   */
  int floorWidth;
  private final int cellWidth;
  private final int frontWallHeight;


  /**
   * Construct a PlaceableElement and calculate the size and postion to draw on the scene.
   *
   * @param gameElement element of the game.
   * @param playerCol Column position of Player in the room.
   * @param playerRow Row position of Player in the room.
   * @param objectCol Column position of this GameItemElement in the room.
   * @param objectRow Row position of this GameItemElement in the room.
   * @param cellWidth Width of cell.
   * @param frontWallWidth Width of front wall.
   * @param frontWallHeight Height of front wall.
   * @param xoffset Value to shift the imge horizontally.
   */
  public PlaceableElement(GameObject gameElement, int playerCol, int playerRow, int objectCol,
      int objectRow, int cellWidth, int frontWallWidth, int frontWallHeight, int xoffset) {
    this.gameElement = gameElement;
    this.playerRow = playerRow;

    this.objectCol = objectCol;
    this.objectRow = objectRow;
    this.frontWallHeight = frontWallHeight;
    this.floorWidth =
        calculateFloorWidth(frontWallWidth, frontWallHeight, playerRow, objectRow, xoffset);
    this.cellWidth = this.floorWidth / (5);

  }


  /**
   * Get the default image for this PlaceableElement.
   *
   * @return Default image for this PlaceableElement.
   */
  public BufferedImage getDefaultImage() {
    return this.gameElement.getItemImage();

  }

  /**
   * Get the width of the default image of this PlaceableElement.
   *
   * @return Width of the default image of this PlaceableElement.
   *
   */
  public int getDefaultImageWidth() {
    return this.gameElement.getItemImage().getWidth();

  }

  /**
   * Get the height of the default image of this PlaceableElement.
   *
   * @return height of the default image of this PlaceableElement.
   *
   */
  public double getDefaultImageHeight() {
    return this.gameElement.getItemImage().getHeight();

  }

  /**
   * Get the width of the cell this PlaceableElement occupies.
   *
   * @return width of the cell this PlaceableElement occupies.
   */
  public int getCellWidth() {
    return this.cellWidth;
  }

  /**
   * Get the column of this PlaceableElement in the room.
   *
   * @return column of the cell this PlaceableElement occupies.
   */
  public int getCellCol() {
    return this.objectCol;

  }

  /**
   * Get the row of this PlaceableElement in the room.
   *
   * @return row of the cell this PlaceableElement occupies.
   */
  public int getCellRow() {
    return this.objectRow;

  }

  /**
   * Get the row the Player occupy in the room.
   *
   * @return Row the Player occupy in the room.
   */
  public int getPlayerRow() {
    return this.playerRow;

  }

  /*
   * Caluculate the width of the floor width drawn on the screen for the row this DrawableElement is
   * located on.
   */
  private static int calculateFloorWidth(int frontWallWidth, int frontWallHeight, int playerRow,
      int objectRow, int xoffset) {
    int baseWidth = Renderer.SCREEN_WIDTH;// - leftbase - rightBase;

    double topWidth = frontWallWidth;

    double height = Renderer.SCREEN_HEIGHT - frontWallHeight;

    double y =
        height / playerRow * objectRow - height / (playerRow + 1) / 2 + (playerRow - objectRow);

    // use interpolation to get width based on height of trapezium.
    return (int) ((int) (height - y) / height * topWidth + y / height * baseWidth);
  }

  /**
   * Get the GameObject this DrawableElement represent.
   *
   * @return GameObject this DrawableElement represent.
   *
   */
  public GameObject getGameObject() {
    return this.gameElement;
  }

  /**
   * Determine if a Point is on this DrawableElement.
   *
   * @param point Point to check against this DrawableElement.
   *
   * @return returns point.
   */
  public abstract boolean pointIsOnElement(Point point);



  /**
   * Getter for wall.
   * @return wall height.
   */
  public int getFrontWallheight() {
    return this.frontWallHeight;
  }
}
