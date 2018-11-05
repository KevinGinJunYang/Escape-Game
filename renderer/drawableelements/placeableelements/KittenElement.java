package renderer.drawableelements.placeableelements;

import gameworld.GameObject;
import renderer.drawableelements.GameItemElement;

/**
 * KittenElement represent a GameWorld.Kitten that is drawn on Renderer.
 *
 * @author joewill
 *
 */
public class KittenElement extends GameItemElement {

  /*
   * Scale used when determining width of image relative to the cell width.
   */
  private static final double IMAGE_SCALE = 0.5;

  /**
   * Construct a KittenElement.
   *
   * @param gameElement GameObject this KittenElement represent.
   * @param playerCol Current column position of the Player in the Room.
   * @param playerRow Current row position of the Player in the Room.
   * @param objectCol Column position of this KittenElement in the Room.
   * @param objectRow Row position of this KittenElement in the Room.
   * @param cellWidth Width of cell on canvas.
   * @param cellHeight Height of cell on canvas.
   * @param frontWallWidth Width of facing wall on canvas.
   * @param frontWallHeight Width of facing wall on canvas.
   * @param xoffset Value to shift the image horizontally on the canvas.
   */
  public KittenElement(GameObject gameElement, int playerCol, int playerRow, int objectCol,
      int objectRow, int cellWidth, int cellHeight, int frontWallWidth, int frontWallHeight,
      int xoffset) {

    super(gameElement, playerCol, playerRow, objectCol, objectRow, cellWidth, cellHeight,
        frontWallWidth, frontWallHeight, xoffset, KittenElement.IMAGE_SCALE);

  }

}
