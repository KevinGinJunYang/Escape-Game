package renderer.drawableelements.placeableelements;

import gameworld.GameObject;
import renderer.drawableelements.GameItemElement;

/**
 * TableElement represent a GameWorld.Table that is drawn on Renderer.
 *
 * @author joewill
 *
 */
public class TableElement extends GameItemElement {

  /*
   * Scale used when determining width of image relative to the cell width.
   */
  private static final double IMAGE_SCALE = 1.0;

  /**
   * Contruct a TableElement.
   *
   * @param gameElement GameObject this TableElement represent.
   * @param playerCol Current column position of the Player in the Room.
   * @param playerRow Current row position of the Player in the Room.
   * @param objectCol Column position of this TableElement in the Room.
   * @param objectRow Row position of this TableElement in the Room.
   * @param cellWidth Width of cell on Canvas.
   * @param cellHeight Height of cell on Canvas.
   * @param frontWallWidth Width of facing wall on canvas.
   * @param frontWallHeight Width of facing wall on canvas.
   * @param xoffset Value to shift the image horizontally on the canvas.
   */
  public TableElement(GameObject gameElement, int playerCol, int playerRow, int objectCol,
      int objectRow, int cellWidth, int cellHeight, int frontWallWidth, int frontWallHeight,
      int xoffset) {

    super(gameElement, playerCol, playerRow, objectCol, objectRow, cellWidth, cellHeight,
        frontWallWidth, frontWallHeight, xoffset, TableElement.IMAGE_SCALE);

  }

}
