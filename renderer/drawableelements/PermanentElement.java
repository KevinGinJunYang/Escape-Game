package renderer.drawableelements;


/**
 * This abstract class PermanentElement represents Elements of a Room that are fixed and always
 * persisting.
 *
 * @author joewill
 *
 */
public abstract class PermanentElement implements DrawableElement {

  /**
   * Default width of front wall.
   */
  public static final int FRONTWALL_DEFAULT_WIDTH = 800;

  /**
   * Default height of front wall.
   */
  public static final int FRONTWALL_DEFAULT_HEIGHT = 600;

  /**
   * Number of points on front wall.
   */
  public static final int NUM_OF_POINTS = 4;

  /**
   * Value used for scaling width of front wall.
   */
  public static final int WIDTH_SCALING_VALUE = 50;

  /**
   * Value used for scaling width of front wall.
   */
  public static final int HEIGHT_SCALING_VALUE = 30;



  /**
   * Array specifying the x values of this PermanentElement for drawing on the screen.
   */
  private int[] xpoints;

  /**
   * Array specifying the y values of this PermanentElement for drawing on the screen.
   */
  private int[] ypoints;

  /**
   * Set the xpoints of this PermanentElement.
   *
   * @param xpoints Array of values representing the xpoints to draw this PermanentElement.
   * @return returns the permanent element
   */
  public PermanentElement setXPoints(int[] xpoints) {
    this.xpoints = xpoints;
    return this;
  }


  /**
   * Set the ypoints of this PermanentElement.
   *
   * @param ypoints Array of values representing the ypoints to draw this PermanentElement.
   * @return returns the y point
   *
   */
  public PermanentElement setYPoints(int[] ypoints) {
    this.ypoints = ypoints;
    return this;
  }


  /**
   * Returns the x point.
   * @return x point
   */
  public int[] getXPoints() {
    return this.xpoints;
  }

  /**
   * Returns the y point.
   * @return y point
   */
  public int[] getYPoints() {
    return this.ypoints;
  }

  /**
   * Getter for permanenet Element.
   * @return element.
   */
  public PermanentElement get() {
    return this;
  }

  // public abstract void initialisePoints();
}
