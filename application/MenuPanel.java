package application;

import gameworld.GameWorld;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import persistence.Parser;



/**
 * Creates the Menu panel for the GUI.
 *
 * @author kevin
 *
 */
public class MenuPanel implements ActionListener {

  boolean mouse = false;
  private JMenuBar menuBar;
  private JMenu m1;
  private JMenu m2;
  private GameFrame game;
  private GuiFrame guiGame;
  boolean menuOn = false;
  boolean gameOn = false;
  GameWorld gameWorld;

  /**
   * Sets the game menu on game menu.
   *
   * @param g game frame
   *
   */
  public MenuPanel(GameFrame g) {
    game = g;
    menuBar = new JMenuBar();
    m1 = new JMenu("Game");
    m2 = new JMenu("Help");
    menuBar.add(m1);
    menuBar.add(m2);
    gameWorld = new GameWorld();
    menuOn = true;
    gameOn = false;
    setupMenuBar();
  }

  /**
   * Sets up game menu in game.
   *
   * @param g game frame
   */
  public MenuPanel(GuiFrame g) {
    guiGame = g;
    menuBar = new JMenuBar();
    m1 = new JMenu("Game");
    m2 = new JMenu("Help");
    menuBar.add(m1);
    menuBar.add(m2);
    gameWorld = new GameWorld();
    gameOn = true;
    menuOn = false;
    setupMenuBar();
  }

  /**
   * Sets up the buttons and adds to menu bar.
   */
  public void setupMenuBar() {
    JMenuItem newGame = new JMenuItem("New");
    newGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        restartGame();
      }
    });

    JMenuItem save = new JMenuItem("Save");
    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          try {
            gameWorld = guiGame.getGame();
            Parser.save(gameWorld, file);
          } catch (JAXBException e) {
            e.printStackTrace();
          }
        }
      }
    });

    JMenuItem load = new JMenuItem("Load");
    load.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          try {
            gameWorld = Parser.load(file);
            if (menuOn) {
              game.dispose();
              menuOn = false;
              gameOn = true;
            } else if (gameOn) {
              guiGame.dispose();
              gameOn = false;
              menuOn = true;
            }
            guiGame = new GuiFrame(gameWorld);

          } catch (JAXBException e) {
            e.printStackTrace();
          }
        }
      }
    });

    JMenuItem instructions = new JMenuItem("Instructions");
    instructions.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        if (guiGame != null) {
          JOptionPane.showMessageDialog(guiGame,
              "Collect all the kittens in the maze to Win! \n" + "'W' to move NORTH\n"
                  + "'A' to move WEST\n" + "'S' to move SOUTH\n" + "'D' to move EAST\n"
                  + "Right-click to interact with objects\n"
                  + "Entering through doors and opening chests uses items");
        } else {
          JOptionPane.showMessageDialog(game,
              "Collect all the kittens in the maze to Win! \n" + "'W' to move NORTH\n"
                  + "'A' to move WEST\n" + "'S' to move SOUTH\n" + "'D' to move EAST\n"
                  + "Right-click to interact with objects\n"
                  + "Entering through doors and opening chests uses items");
        }
      }
    });
    m1.add(newGame);
    m1.add(save);
    m1.add(load);
    m2.add(instructions);

    if (guiGame != null) {
      guiGame.getContentPane().add(BorderLayout.NORTH, menuBar);
    } else {
      game.getContentPane().add(BorderLayout.NORTH, menuBar);
    }

  }

  /**
   * Resets the game when clicked and asks for confirmation.
   */
  public void restartGame() {

    if (guiGame != null) {
      int r = JOptionPane.showConfirmDialog(game, new JLabel("Restart Game?"), "Confirm Restart",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

      if (r == JOptionPane.YES_OPTION) {
        guiGame.dispose();
        new GameFrame();
      }
    } else {
      int r = JOptionPane.showConfirmDialog(game, new JLabel("Restart Game?"), "Confirm Restart",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

      if (r == JOptionPane.YES_OPTION) {
        game.dispose();
        new GameFrame();
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {}

}
