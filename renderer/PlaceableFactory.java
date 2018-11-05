package renderer;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Door;
import gameworld.GameObject;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Switch;
import gameworld.Table;
import renderer.drawableelements.PlaceableElement;
import renderer.drawableelements.placeableelements.BoxElement;
import renderer.drawableelements.placeableelements.ChairElement;
import renderer.drawableelements.placeableelements.ChestElement;
import renderer.drawableelements.placeableelements.DoorElement;
import renderer.drawableelements.placeableelements.KeyElement;
import renderer.drawableelements.placeableelements.KittenElement;
import renderer.drawableelements.placeableelements.SwitchElement;
import renderer.drawableelements.placeableelements.TableElement;

/**
 * PlaceableFactory is used to produce PlaceableElements. PlaceableElements produced by this
 * PlaceableFactory will inherit the attributes given to this PlaceableFactory.
 *
 * @author joewill
 *
 */
public class PlaceableFactory {

  /*
   * Row player is located in the room.
   */
  private final int playerRow;

  /*
   * Column Players is located in the room.
   */
  private final int playerCol;

  /*
   * Amount to shift the room left/right on the canvas.
   */
  private final int xoffset;

  /*
   * Height of the cell on the canvas.
   */
  private final int cellHeight;

  /*
   * Width of facing wall on the canvas.
   */
  private final int frontWallWidth;

  /*
   * Height of facing wall on the canvas.
   */
  private final int frontWallHeight;


  /**
   * Construct a PlaceableFactory with predefined specifications that PlaceableElements produce by
   * this PlaceableFactory will inherit.
   *
   * @param playerRow Row player is located in the room.
   * @param playerCol Column player is located in the room.
   * @param xoffset Amount to shift the Placeable left/right when drawn.
   * @param cellHeight Height of cell on canvas.
   * @param frontWallWidth Width of facing wall on the canvas.
   * @param frontWallHeight Height of facing wall on the canvas.
   *
   */
  public PlaceableFactory(int playerRow, int playerCol, int xoffset, int cellHeight,
      int frontWallWidth, int frontWallHeight) {

    this.playerRow = playerRow;
    this.playerCol = playerCol;
    this.xoffset = xoffset;
    this.cellHeight = cellHeight;
    this.frontWallWidth = frontWallWidth;
    this.frontWallHeight = frontWallHeight;
  }

  /**
   * Produce a new PlaceableElement using the attributes of this PlaceableFactory and the specified
   * parameters.
   *
   * @param element GameObject to produce an equvialent PlaceableElement of.
   * @param cellCol Cell column this GameObject is located in.
   * @param cellRow Cell row this GameObject is located in.
   * @param cellWidth Width of cell on Canvas.
   * @return new PlaceableElement using the attributes of this PlaceableFactory and the specified
   *         parameters.
   */
  public PlaceableElement makePlaceable(GameObject element, int cellCol, int cellRow,
      int cellWidth) {

    if (element instanceof Key) {
      return new KeyElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Chest) {
      return new ChestElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Door) {
      return new DoorElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Kitten) {
      return new KittenElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Switch) {
      return new SwitchElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Chair) {
      return new ChairElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Table) {
      return new TableElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);

    } else if (element instanceof Box) {
      return new BoxElement(element, this.playerRow, this.playerCol, cellCol, cellRow, cellWidth,
          this.cellHeight, this.frontWallWidth, this.frontWallHeight, this.xoffset);
    } // element this factory can't produce.
    throw new IllegalArgumentException(
        " element must be of type Key, Chest, Door, Kitten, Switch, Table, Box or Chair.");

  }

}
