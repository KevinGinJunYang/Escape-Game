package application;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Sets up game assets and movement.
 *
 * @author kevin
 *
 */
public class SetupPanel {

  private RenderPanel panel;
  private List<Image> buttons = new ArrayList<Image>();
  private List<Image> unhoveredButtons = new ArrayList<Image>();
  private List<Image> hoveredButtons = new ArrayList<Image>();
  private BufferedImage backgroundImage;

  /**
   * Sets images of starting menu.
   *
   * @param render renderer
   */
  public SetupPanel(RenderPanel render) {
    panel = render;
    try {
      //build images
      BufferedImage background = ImageIO
          .read(GameFrame.class.getResource("MenuImages/background3.png"));
      final Image play = ImageIO.read(GameFrame.class.getResource("MenuImages/play.png"));
      final Image load = ImageIO.read(GameFrame.class.getResource("MenuImages/load.png"));
      final Image help = ImageIO.read(GameFrame.class.getResource("MenuImages/help.png"));
      final Image quit = ImageIO.read(GameFrame.class.getResource("MenuImages/quit.png"));
      final Image playHover = ImageIO.read(GameFrame.class.getResource("MenuImages/playhover.png"));
      final Image loadHover = ImageIO.read(GameFrame.class.getResource("MenuImages/loadhover.png"));
      final Image helpHover = ImageIO.read(GameFrame.class.getResource("MenuImages/helphover.png"));
      final Image quitHover = ImageIO.read(GameFrame.class.getResource("MenuImages/quithover.png"));
      //add to list
      backgroundImage = background;
      buttons.add(play);
      buttons.add(load);
      buttons.add(help);
      buttons.add(quit);
      unhoveredButtons.add(play);
      unhoveredButtons.add(load);
      unhoveredButtons.add(help);
      unhoveredButtons.add(quit);
      hoveredButtons.add(playHover);
      hoveredButtons.add(loadHover);
      hoveredButtons.add(helpHover);
      hoveredButtons.add(quitHover);

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
      g.drawImage(buttons.get(i), panel.getWidth() / 2 - 120,
          panel.getHeight() / 3 - 40 + (i * (panel.getHeight() / 6)), null);
    }

  }

  /**
   * Sets buttons to hover given its position.
   *
   * @param button hover
   */
  public void setHover(String button) {
    if (button.equals("play")) {
      buttons.set(0, hoveredButtons.get(0));
      panel.repaint();
    } else if (button.equals("load")) {
      buttons.set(1, hoveredButtons.get(1));
      panel.repaint();
    } else if (button.equals("help")) {
      buttons.set(2, hoveredButtons.get(2));
      panel.repaint();
    } else if (button.equals("quit")) {
      buttons.set(3, hoveredButtons.get(3));
      panel.repaint();
    }
  }

  /**
   * Sets buttons to unhover given its position.
   *
   * @param button unhover
   */
  public void setUnhover(String button) {
    if (button.equals("play")) {
      buttons.set(0, unhoveredButtons.get(0));
      panel.repaint();
    } else if (button.equals("load")) {
      buttons.set(1, unhoveredButtons.get(1));
      panel.repaint();
    } else if (button.equals("help")) {
      buttons.set(2, unhoveredButtons.get(2));
      panel.repaint();
    } else if (button.equals("quit")) {
      buttons.set(3, unhoveredButtons.get(3));
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
    int buttonWidth = panel.getWidth() / 2 - 120;
    int buttonHeight = panel.getHeight() / 3 - 40;
    int playButton = buttonHeight + (0 * panel.getHeight() / 6);
    int loadButton = buttonHeight + (1 * panel.getHeight() / 6);
    int helpButton = buttonHeight + (2 * panel.getHeight() / 6);
    int quitButton = buttonHeight + (3 * panel.getHeight() / 6);
    boolean inWidth = x >= buttonWidth && x <= buttonWidth + 240;

    //finds button
    if (inWidth && y > playButton && y < playButton + 80) {
      return "play";
    } else if (inWidth && y > loadButton && y < loadButton + 80) {
      return "load";
    } else if (inWidth && y > helpButton && y < helpButton + 80) {
      return "help";
    } else if (inWidth && y > quitButton && y < quitButton + 80) {
      return "quit";
    }
    return "";
  }

}
