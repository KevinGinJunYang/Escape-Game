package application;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Action controller for the mouse movement.
 *
 * @author kevin
 *
 */
public class MouseMotion implements MouseMotionListener {

  private RenderPanel panel;

  /**
   * Sets render to mouse motion.
   *
   * @param render
   *          to mouse movement
   */
  public MouseMotion(RenderPanel render) {
    panel = render;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    panel.sendHover(e.getX(), e.getY());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
