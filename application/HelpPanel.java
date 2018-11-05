package application;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Sets up help panel.
 *
 * @author kevin
 *
 */
public class HelpPanel {

  private RenderPanel panel;
  private List<Image> buttons = new ArrayList<Image>();
  private List<Image> unhoveredButtons = new ArrayList<Image>();
  private List<Image> hoveredButtons = new ArrayList<Image>();
  private BufferedImage backgroundImage;

  /**
   * Sets images of starting menu.
   * @param render renderer
   */
  public HelpPanel(RenderPanel render) {
    panel = render;
    try {
      BufferedImage background = ImageIO
          .read(GameFrame.class.getResource("MenuImages/helpbackground.png"));
      final Image back = ImageIO.read(GameFrame.class.getResource("MenuImages/back.png"));
      final Image backhover = ImageIO.read(GameFrame.class.getResource("MenuImages/backhover.png"));
      backgroundImage = background;
      buttons.add(backhover);
      unhoveredButtons.add(backhover);
      hoveredButtons.add(back);
      ;

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Draws the background and buttons of start menu.
   *
   * @param g graphics
   */
  public void redraw(Graphics g) {
    // draw background
    g.drawImage(backgroundImage, 0, 0, (int) panel.getWidth(), (int) panel.getHeight(), null);
    // draw buttons
    for (int i = 0; i < buttons.size(); i++) {
      g.drawImage(buttons.get(i), panel.getWidth() / 2 + 240,
          panel.getHeight() / 3 - 200 + (i * (panel.getHeight() / 6)), null);
    }
  }

  /**
   * Sets buttons to hover given its position.
   *
   * @param button back button
   */
  public void setHover(String button) {
    if (button.equals("back")) {
      buttons.set(0, hoveredButtons.get(0));
      panel.repaint();
    }
  }

  /**
   * Sets buttons to unhover given its position.
   *
   * @param button back button
   */
  public void setUnhover(String button) {
    if (button.equals("back")) {
      buttons.set(0, unhoveredButtons.get(0));
      panel.repaint();
    }
  }

  /**
   * finds the button area and returns what button it corresponds to.
   *
   * @param x
   *          pos
   * @param y
   *          pos
   * @return button type
   */
  public String getButtonArea(int x, int y) {
    int buttonWidth = panel.getWidth() / 2 + 240;
    int buttonHeight = panel.getHeight() / 3 - 200;
    int playButton = buttonHeight + (0 * panel.getHeight() / 6);
    boolean inWidth = x >= buttonWidth && x <= buttonWidth + 240;
    if (inWidth && y > playButton && y < playButton + 80) {
      return "back";
    }
    return "";
  }

}
