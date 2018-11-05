package renderer.drawableelements.permanentelements;

import java.awt.Color;
import java.awt.Graphics;
import renderer.Renderer;
import renderer.drawableelements.AccessibleElement;
import renderer.drawableelements.PermanentElement;

/**
 * FloorElement represents the floor on the scene.
 *
 * @author joewill
 *
 */
public class FloorElement extends AccessibleElement {

  private final int playerRow;
  private final Color colour;

  /**
   * Construct a Floor to be rendered.
   *
   * @param midXPoint mid x point of the scene.
   * @param frontWallWidth Width of the front wall.
   * @param frontWallHeight Height of the fron wall.
   * @param xoffset Amount to shift this wall on the scene.
   * @param colour Colour of this wall.
   * @param playerRow row of the player.
   */
  public FloorElement(int midXPoint, int frontWallWidth, int frontWallHeight, int xoffset,
      Color colour, int playerRow) {
    this.colour = colour;
    this.playerRow = playerRow;
    this.initialise(midXPoint, frontWallWidth, frontWallHeight, xoffset);
  }

  /*
   * Initialise this FloorElement calulating the oints to draw it.
   */
  private void initialise(int midXPoint, int frontWallWidth, int frontWallHeight, int xoffset) {

    int topLeftX = findTopLeftX(midXPoint, frontWallWidth, xoffset);
    int topRightX = findTopRightX(midXPoint, frontWallWidth, xoffset);
    int botLeftX = xoffset;
    int botRightX = findBotRightX(midXPoint, frontWallWidth, xoffset);

    int[] xpoints = new int[] {topLeftX, topRightX, botRightX, botLeftX};
    int[] ypoints = new int[] {frontWallHeight, frontWallHeight, Renderer.SCREEN_HEIGHT,
        Renderer.SCREEN_HEIGHT};

    super.setXPoints(xpoints);
    super.setYPoints(ypoints);

  }

  /*
   * Calculate the topLeftX point of this Wall.
   */
  private int findTopLeftX(int midXPoint, int frontWallWidth, int xoffset) {
    return midXPoint - frontWallWidth / 2 + xoffset;
  }

  /*
   * Calculate the topRightX point of this Wall.
   */
  private int findTopRightX(int midXPoint, int frontWallWidth, int xoffset) {
    return midXPoint + frontWallWidth / 2 + xoffset;
  }

  /*
   * Calculate the botRightX point of this Wall.
   */
  private int findBotRightX(int midXPoint, int frontWallWidth, int xoffset) {
    return Renderer.SCREEN_WIDTH + xoffset;
  }

  /**
   * Draw this FloorElement.
   *
   * @param g Graphics to draw on.
   */
  @Override
  public void drawElement(Graphics g) {
    g.setColor(this.colour);
    g.fillPolygon(super.getXPoints(), super.getYPoints(), PermanentElement.NUM_OF_POINTS);

    g.setColor(this.colour.brighter().brighter());

    // draw gird lines.
    int[] xpoints = super.getXPoints();
    int[] ypoints = super.getYPoints();

    int topCellWidth = computeCellWidth(xpoints[0], xpoints[1]);
    int botCellWidth = computeCellWidth(xpoints[3], xpoints[2]);
    int cellHeight = computeCellheight(ypoints[0], ypoints[3]);

    for (int i = 1; i < 5; i++) {
      // vertical lines
      g.drawLine(xpoints[0] + i * topCellWidth, ypoints[0], xpoints[3] + i * botCellWidth,
          ypoints[3]);

      // horizontal lines
      g.drawLine(xpoints[3], ypoints[0] + i * cellHeight, xpoints[2], ypoints[0] + i * cellHeight);
    }

  }

  /*
   * Calculate width of cell.
   */
  private int computeCellWidth(int left, int right) {
    return (int) (right - left) / 5;
  }

  /*
   * Calculate width of height.
   */
  private int computeCellheight(int top, int bot) {
    return (int) (bot - top) / (5 - (4 - this.playerRow));
  }
}
