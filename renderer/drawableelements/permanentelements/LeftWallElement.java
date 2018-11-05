package renderer.drawableelements.permanentelements;

import java.awt.Color;
import java.awt.Graphics;
import renderer.Renderer;
import renderer.drawableelements.PermanentElement;
import renderer.drawableelements.WallElement;

/**
 * LeftWallElement represents the left wall on the scene.
 *
 * @author joewill
 *
 */
public class LeftWallElement extends WallElement {

  /*
   * Colour of this wall.
   */
  private final Color colour;

  /**
   * Construct a RightWallElement to be renderer.
   *
   * @param midXPoint mid x point of the scene.
   * @param frontWallWidth Width of the front wall.
   * @param frontWallHeight Height of the fron wall.
   * @param xoffset Amount to shift this wall on the scene.
   * @param colour Colour of this wall.
   */
  public LeftWallElement(int midXPoint, int frontWallWidth, int frontWallHeight,
      int xoffset, Color colour) {
    this.colour = colour;
    this.initialisePoints(midXPoint, frontWallWidth, frontWallHeight, xoffset);
  }

  /**
   * Initialise this Wall, caluclating points to be drawn and set the to the super class.
   *
   * @param midXPoint Center x point of the screen.
   * @param frontWallWidth Width of the front wall.
   * @param frontWallHeight Height of the fron wall.
   * @param xoffset amount to shift this wall on the scene.
   */
  private void initialisePoints(int midXPoint, int frontWallWidth, int frontWallHeight,
      int xoffset) {

    int leftX = 0;
    int rightX = findRightX(midXPoint, frontWallWidth, xoffset);
    int midX = xoffset;
    int topY = 0;
    int midY = frontWallHeight;
    int botY = Renderer.SCREEN_HEIGHT;

    int[] xpoints = new int[] {leftX, rightX, rightX, midX, leftX};
    int[] ypoints = new int[] {topY, topY, midY, botY, botY};

    super.setXPoints(xpoints);
    super.setYPoints(ypoints);
  }

  /*
   * Calculate the rightX point of this Wall.
   */
  private int findRightX(int midX, int frontWallWidth, int xoffset) {
    return midX - frontWallWidth / 2 + xoffset;
  }

  /**
   * Draw this Wall on the scene.
   *
   * @param g Graphics to drawn this wasll on.
   */
  @Override
  public void drawElement(Graphics g) {
    g.setColor(this.colour);
    g.fillPolygon(super.getXPoints(), super.getYPoints(), PermanentElement.NUM_OF_POINTS + 1);

  }

}
