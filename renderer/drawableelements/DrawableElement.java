package renderer.drawableelements;

import java.awt.Graphics;

/**
 * This interface DrawableElement represents a element that is used by the.
 * Renderer to draw on the screen.
 *
 * @author joewill
 */
public interface DrawableElement {

  /**
   * Draw this DrawableElements on the given Graphics.
   *
   * @param g Graphics to draw this DrawableElements element on.
   */
  public void drawElement(Graphics g);

}
