package renderer.drawableelements;

import gameworld.GameObject;
import java.awt.Graphics;
import java.awt.Point;
import renderer.Renderer;

/**
 * GameItemElement represent a GameObject that is renderer. When create the image position and
 * dimension are assigned.
 *
 * @author joewill
 *
 */
public abstract class GameItemElement extends PlaceableElement {

  /*
   * Value to use to scale the original image.
   */
  private double imageScale;

  /*
   * Scale the Image takes relative to the cell.
   */
  private double elementScale;

  /*
   * Image Dimensions
   */
  private int imageWidth;
  private int imageHeight;


  /*
   * Position of the image.
   */
  private int leftXPos;
  private int topYPos;

  /*
   * Height of cell on screen.
   */
  private int cellHeight;

  /*
   * Amount to shift the image horizontally.
   */
  private int xoffset;

  /**
   * Contruct a GameItemElement and initialise its fields to be ready to draw.
   *
   * @param gameElement GameObject this GameItemElement represent.
   * @param playerCol Column position of Player in the room.
   * @param playerRow Row position of Player in the room.
   * @param cellCol Column position of this GameItemElement in the room.
   * @param cellRow Row position of this GameItemElement in the room.
   * @param cellWidth Width of cell.
   * @param cellHeight Height of cell.
   * @param frontWallWidth Width of front wall.
   * @param frontWallHeight Height of front wall.
   * @param xoffset Value to shift the imge horizontally.
   * @param elementScale Scale of the cell width the image should occupy.
   */
  public GameItemElement(GameObject gameElement, int playerCol, int playerRow, int cellCol,
      int cellRow, int cellWidth, int cellHeight, int frontWallWidth, int frontWallHeight,
      int xoffset, double elementScale) {

    super(gameElement, playerCol, playerRow, cellCol, cellRow, cellWidth, frontWallWidth,
        frontWallHeight, xoffset);

    this.elementScale = elementScale;
    this.cellHeight = cellHeight;
    this.xoffset = xoffset;
    this.initialiseImage();

  }

  /*
   * Set the left position for this image.
   */
  private void setLeftXPos() {
    this.leftXPos = (int) (Renderer.SCREEN_WIDTH / 2 - (2.5 * super.getCellWidth()) + // left pos
        (super.getCellWidth() - this.imageWidth) / 2// offset from cell left
        + (super.getCellWidth() * super.getCellCol()) + this.xoffset); // left of cell
  }

  /*
   * Set the top position for this image.
   */
  private void setTopYPos() {
    this.topYPos = (int) (Renderer.SCREEN_HEIGHT
        - (super.getPlayerRow() - super.getCellRow()) * this.cellHeight);

  }

  /*
   * Initialise the size and dimensions of the image.
   */
  private void initialiseImage() {
    // Set iamge size
    this.setImageScale();
    this.setImageWidth();
    this.setImageHeight();

    // Set image position.
    this.setLeftXPos();
    this.setTopYPos();

  }

  /*
   * Set the width of the image.
   */
  private void setImageWidth() {
    this.imageWidth = (int) (super.getDefaultImageWidth() * this.imageScale);

  }

  /*
   * Set the height of the image.
   */
  private void setImageHeight() {
    this.imageHeight = (int) (super.getDefaultImageHeight() * this.imageScale);

  }

  /*
   * Set the scale used to scale the original image to fit in the scene.
   */
  private void setImageScale() {
    this.imageScale = (this.elementScale * super.getCellWidth()) / super.getDefaultImageWidth();
  }

  /**
   * Determine is the cooridinates of a given Point is within the bounding box of this.
   * GameItemElement.
   *
   * @param point Point to check GameItemElement against.
   * @return true id Point is within this GameItemElement otherwise false.
   */
  public boolean pointIsOnElement(Point point) {
    // determine the boundaries
    int x1 = this.leftXPos;
    int x2 = this.leftXPos + this.imageWidth;
    int y1 = this.topYPos;
    int y2 = this.topYPos + this.imageHeight;

    return x1 <= point.x && x2 >= point.x && y1 <= point.y && y2 >= point.y;

  }

  /**
   * Draw this GameItemElement on screen.
   *
   * @param g Graphics to draw this GameItemElement on.
   */
  @Override
  public void drawElement(Graphics g) {
    g.drawImage(super.getDefaultImage(), this.leftXPos, this.topYPos, this.imageWidth,
        this.imageHeight, null);

  }

}
