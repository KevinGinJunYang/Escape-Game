package renderer.drawableelements.permanentelements;

import java.awt.Color;
import java.awt.Graphics;
import renderer.drawableelements.PermanentElement;
import renderer.drawableelements.WallElement;

/**
 * FrontWallElement represents the facing wall on the scene.
 *
 * @author joewill
 *
 */
public class FrontWallElement extends WallElement {

  /*
   * Colour of this wall.
   */
  private final Color colour;

  /**
   * Construct a RightWallElement to be renderer.
   *
   * @param midX Mid x point of the screen.
   * @param frontWallWidth Width of the front wall.
   * @param frontWallHeight Height of the fron wall.
   * @param xoffset Amount to shift this wall on the scene.
   * @param colour Colour of this wall.
   */
  public FrontWallElement(int midX, int frontWallWidth, int frontWallHeight,
      int xoffset, Color colour) {
    this.colour = colour;
    this.initialisePoints(midX, frontWallWidth, frontWallHeight, xoffset);

  }

  /**
   * Initialise this Wall, caluclating points to be drawn and set the to the super class.
   *
   * @param midX Center x point of the screen.
   * @param frontWallWidth Width of the front wall.
   * @param frontWallHeight Height of the fron wall.
   * @param xoffset amount to shift this wall on the scene.
   */
  private void initialisePoints(int midX, int frontWallWidth, int frontWallHeight, int xoffset) {

    int leftx = this.findleftx(midX, frontWallWidth, xoffset);
    int rightx = this.findrightx(midX, frontWallWidth, xoffset);
    int topY = 0;
    int botY = frontWallHeight;

    int[] xpoints = new int[] {leftx, rightx, rightx, leftx};
    int[] ypoints = new int[] {topY, topY, botY, botY};
    super.setXPoints(xpoints);
    super.setYPoints(ypoints);
  }

  /*
   * Calculate the leftX point of this Wall.
   */
  private int findleftx(int midX, int frontWallWidth, int xoffset) {
    return midX - frontWallWidth / 2 + xoffset;
  }

  /*
   * Calculate the rightX point of this Wall.
   */
  private int findrightx(int midX, int frontWallWidth, int xoffset) {
    return midX + frontWallWidth / 2 + xoffset;
  }

  /**
   * Draw this Wall on the scene.
   *
   * @param g Graphics to drawn this wasll on.
   */
  @Override
  public void drawElement(Graphics g) {
    g.setColor(this.colour);
    g.fillPolygon(super.getXPoints(), super.getYPoints(), PermanentElement.NUM_OF_POINTS);

  }

}

