package application;

import gameworld.GameWorld;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;
import persistence.Parser;




//import persistence.LoadProject_fromXML;

/**
 * Renders the game window and game application.
 *
 * @author kevin
 *
 */
public class RenderPanel extends JPanel {

  /**
   * SerialVersion.
   */
  private static final long serialVersionUID = 1L;
  /**
   * setup panel getter.
   */
  public boolean setupPanel;
  /**
   * help panel getter.
   */
  public boolean helpPanel;
  private SetupPanel setup;
  private HelpPanel help;
  private GameFrame game;
  private MouseAction mouse;
  private Handler handler;
  private MouseMotion mouseMotion;
  private String hoveredButton = "";
  @SuppressWarnings("unused")
  private GuiFrame guiGame;
  private GameWorld gameWorld;
  private int mouseX;
  private int mouseY;
  private int hoverX;
  private int hoverY;


  /**
   * Set up game frame to render.
   *
   * @param g
   *          game frame of main menu to render
   */
  public RenderPanel(GameFrame g) {
    this.game = g;
    setup = new SetupPanel(this);
    help = new HelpPanel(this);
    setupPanel = true;
    helpPanel = false;
    handler = new Handler();
    setUpMouseKeys();
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (setupPanel) {
      setup.redraw(g);
    }
    if (helpPanel == true) {
      help.redraw(g);
    }
  }

  /**
   * Sets up to allow mouse interactions with UI.
   */
  public void setUpMouseKeys() {
    //set mouse click
    mouse = new MouseAction(this);
    this.addMouseListener(mouse);
    //set mouse movement
    mouseMotion = new MouseMotion(this);
    this.addMouseMotionListener(mouseMotion);
  }

  /**
   * Gets click.
   *
   * @param x
   *          position of x click
   * @param y
   *          position of y click
   */
  public void sendClick(int x, int y) {
    mouseX = x;
    mouseY = y;
    handler.mouseListener();
  }

  /**
   * Gets hover.
   *
   * @param x
   *          position of hover in x
   * @param y
   *          position of hover in y
   */
  public void sendHover(int x, int y) {
    hoverX = x;
    hoverY = y;
    handler.mouseMoved();
  }

  /**
   * Handler for the movement of the mouse.
   *
   * @author kevin
   *
   */
  private class Handler implements ActionListener {
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Gets the corresponding button to hover then moved.
     */
    public void mouseMoved() {
      if (setupPanel) {
        if (setup.getButtonArea(hoverX, hoverY).equals("play")) {
          hoveredButton = "play";
          setup.setHover("play");
        } else if (setup.getButtonArea(hoverX, hoverY).equals("load")) {
          hoveredButton = "load";
          setup.setHover("load");
        } else if (setup.getButtonArea(hoverX, hoverY).equals("help")) {
          hoveredButton = "help";
          setup.setHover("help");
        } else if (setup.getButtonArea(hoverX, hoverY).equals("quit")) {
          hoveredButton = "quit";
          setup.setHover("quit");
        } else if (help.getButtonArea(hoverX, hoverY).equals("back")) {
          hoveredButton = "back";
          setup.setHover("back");
        } else {
          setup.setUnhover(hoveredButton);
          hoveredButton = "";
        }
      } else if (helpPanel) {
        if (help.getButtonArea(hoverX, hoverY).equals("back")) {
          hoveredButton = "back";
          help.setHover("back");
        } else {
          help.setUnhover(hoveredButton);
          hoveredButton = "";
        }
      }

    }

    /**
     * Gets the correspond button to do actions when clicked.
     */
    public void mouseListener() {
      String s = setup.getButtonArea(mouseX, mouseY);
      String h = help.getButtonArea(mouseX, mouseY);
      if (setupPanel) {
        if (s.equals("play")) {
          game.dispose();
          new GuiFrame(new GameWorld());
        } else if (s.equals("quit")) {
          int confirmed = JOptionPane.showConfirmDialog(null, "Quit game?", "Warning",
              JOptionPane.YES_NO_OPTION);

          if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
          }
        } else if (s.equals("load")) {
          JFileChooser fileChooser = new JFileChooser();
          if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
              gameWorld = Parser.load(file);
              guiGame = new GuiFrame(gameWorld);
              game.dispose();
            } catch (JAXBException e) {
              e.printStackTrace();
            }
          }
        } else if (s.equals("help")) {
          setupPanel = false;
          helpPanel = true;
          repaint();
        }

      } else if (helpPanel) {
        if (h.equals("back")) {
          helpPanel = false;
          setupPanel = true;
          repaint();
        }
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension dimension = new Dimension(1080, 720);
    return dimension;
  }

  /**
   * Checks if in start menu.
   *
   * @return boolean
   */
  public boolean isStartMode() {
    return setupPanel;
  }

  /**
   * Sets gameMode.
   */
  public void setGameMode() {
    setupPanel = false;
  }

  /**
   * Sets game to game menu.
   */
  public void setStartMode() {
    setupPanel = true;
  }

}
