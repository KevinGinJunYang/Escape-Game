package application;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Action controller for the mouse actions.
 *
 * @author kevin
 *
 */
public class MouseAction implements MouseListener {

  private RenderPanel panel;

  /**
   * Sets Actions to render.
   *
   * @param render
   *          sets mouse to render
   */
  public MouseAction(RenderPanel render) {
    panel = render;
  }

  /**
   * Gets mouse action of click.
   */
  public void mouseClicked(MouseEvent e) {
    panel.sendClick(e.getX(), e.getY());
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

}