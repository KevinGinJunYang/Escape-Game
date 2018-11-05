package application;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Door;
import gameworld.GameObject;
import gameworld.GameWorld;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Player;
import gameworld.PuzzleRoom;
import gameworld.Room;
import gameworld.Switch;
import gameworld.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import renderer.Renderer;



/**
 * Runs the main playing game frame to render.
 *
 * @author kevin
 *
 */
public class GuiFrame extends JFrame implements WindowListener, ActionListener, KeyListener {

  /**
   * SerialVersion.
   */
  private static final long serialVersionUID = 1L;
  private Renderer renderer;
  private JPanel gameBoard;
  private GameWorld game;
  private BuildGameWorld build;
  MenuPanel menu;
  JLabel label;
  JPanel playerPanel;
  JPanel inventoryPanel;
  JPanel buttonPanel;
  JButton drop;


  /**
   * Setup GUI game that runs the game frame, sets up all parts to build initial game GUI.
   *
   * @param gameWorld Takes in a game world to renderer to the screen
   */
  public GuiFrame(GameWorld gameWorld) {
    game = gameWorld;
    renderer = new Renderer(this);
    build = new BuildGameWorld();
    build.buildGame(game);
    game.setNewWinCond();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1080, 860);
    menu = new MenuPanel(this);
    setupArea();
    this.setLocationRelativeTo(null);
    renderer.requestFocusInWindow();
    renderer.addKeyListener(this);
    label = new JLabel("Inventory");
    playerPanel = new JPanel();
    inventoryPanel = new JPanel();
    buttonPanel = new JPanel();
    drop = new JButton("Drop Item");
    inventoryPanel.add(label);
    buttonPanel.add(drop);
    playerPanel.add(buttonPanel);
    playerPanel.add(inventoryPanel);
    playerPanel.setLayout(new GridLayout(2, 1));
    setupPlayerPanel();
    this.setVisible(true);

    drop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        Object[] options = {"RED KEY", "BLUE KEY", "GREEN KEY", "YELLOW KEY", "Wooden Table",
            "Wooden Chair", "Wooden Box", "A Fluffy Ball Of Joy"};

        int reply = JOptionPane.showOptionDialog(null,
            "What item would you like to Drop from your inventory?", "Drop item",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[7]);

        if (reply == 0) {
          if (game.getPlayer().getInventoryList().contains("RED KEY")) {

            GameObject ob = game.getPlayer().inventoryContains(new Key(""), "RED");
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 1) {
          if (game.getPlayer().getInventoryList().contains("BLUE KEY")) {
            GameObject ob = game.getPlayer().inventoryContains(new Key(""), "BLUE");
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 2) {
          if (game.getPlayer().getInventoryList().contains("GREEN KEY")) {
            GameObject ob = game.getPlayer().inventoryContains(new Key(""), "GREEN");
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 3) {
          if (game.getPlayer().getInventoryList().contains("YELLOW KEY")) {
            GameObject ob = game.getPlayer().inventoryContains(new Key(""), "YELLOW");
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 4) {
          if (game.getPlayer().getInventoryList().contains("Wooden Table")) {
            GameObject ob = game.getPlayer().inventoryContains(new Table(), null);
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 5) {
          if (game.getPlayer().getInventoryList().contains("Wooden Chair")) {
            GameObject ob = game.getPlayer().inventoryContains(new Chair(), null);
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 6) {
          if (game.getPlayer().getInventoryList().contains("Wooden Box")) {
            GameObject ob = game.getPlayer().inventoryContains(new Box(), null);
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        } else if (reply == 7) {
          if (game.getPlayer().getInventoryList().contains("A Fluffy Ball Of Joy")) {
            GameObject ob = game.getPlayer().inventoryContains(new Kitten(), null);
            game.dropItem(ob);
            setupPlayerPanel();
            renderScene();
            renderer.requestFocusInWindow();
          } else {
            displayMessage("You do not have that item in your inventory");
            renderer.requestFocusInWindow();
          }
        }


      }
    });
  }

  /**
   * Setup Player inventory panel.
   */
  public void setupPlayerPanel() {
    label.setFont(new Font("Serif", Font.PLAIN, 15));
    label.setText("   INVENTORY : " + game.getPlayer().getInventoryList() + "  ");
    this.getContentPane().add(BorderLayout.SOUTH, playerPanel);
  }


  /**
   * Setup Screen playing area.
   */
  public void setupArea() {
    this.gameBoard = new JPanel();
    gameBoard.setLayout(new BoxLayout(gameBoard, BoxLayout.Y_AXIS));
    this.add(gameBoard);
    this.setPreferredSize(new Dimension(600, 600));
    this.setMinimumSize(new Dimension(600, 600));
    this.setVisible(true);
    gameBoard.add(this.renderer);
    this.renderScene();
  }

  /**
   * Render screen image.
   */
  public void renderScene() {
    Player player = game.getPlayer();
    Room room = (Room) game.getCurrentRoom();
    renderer.renderImage(room, player);

  }

  @Override
  public void windowClosing(WindowEvent arg0) {
    int r = JOptionPane.showConfirmDialog(this, new JLabel("Exit Game?"), "Confirm Exit",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (r == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  /**
   * Gets object from click and returns an action given what user has chosen to click on for the
   * object.
   *
   * @param obj object
   */
  public void sendObject(GameObject obj) {
    // pop up menu
    final JPopupMenu key = new JPopupMenu("Key");
    final JPopupMenu door = new JPopupMenu("Door");
    final JPopupMenu chest = new JPopupMenu("Chest");
    final JPopupMenu kitten = new JPopupMenu("Kitten");
    final JPopupMenu table = new JPopupMenu("Table");
    final JPopupMenu chair = new JPopupMenu("Chair");
    final JPopupMenu box = new JPopupMenu("Box");
    final JPopupMenu switchObj = new JPopupMenu("Switch");
    final JMenuItem open = new JMenuItem("Open");
    final JMenuItem examineChest = new JMenuItem("Examine");
    final JMenuItem enter = new JMenuItem("Enter");
    final JMenuItem pickupKey = new JMenuItem("Pickup");
    final JMenuItem pickupKitten = new JMenuItem("Pickup");
    final JMenuItem pickupTable = new JMenuItem("Pickup");
    final JMenuItem pickupBox = new JMenuItem("Pickup");
    final JMenuItem pickupChair = new JMenuItem("Pickup");
    final JMenuItem pushTable = new JMenuItem("Push");
    final JMenuItem pushBox = new JMenuItem("Push");
    final JMenuItem pushChair = new JMenuItem("Push");
    final JMenuItem examineDoor = new JMenuItem("Examine");
    final JMenuItem examineBox = new JMenuItem("Examine");
    final JMenuItem examineChair = new JMenuItem("Examine");
    final JMenuItem examineKey = new JMenuItem("Examine");
    final JMenuItem examineKitten = new JMenuItem("Examine");
    final JMenuItem examineTable = new JMenuItem("Examine");
    final JMenuItem examineSwitch = new JMenuItem("Examine");
    final JMenuItem activate = new JMenuItem("Activate");

    key.add(pickupKey);
    key.add(examineKey);
    door.add(enter);
    door.add(examineDoor);
    chest.add(open);
    chest.add(examineChest);
    kitten.add(pickupKitten);
    kitten.add(examineKitten);
    table.add(pushTable);
    table.add(pickupTable);
    table.add(examineTable);
    switchObj.add(activate);
    switchObj.add(examineSwitch);
    chair.add(pushChair);
    chair.add(pickupChair);
    chair.add(examineChair);
    box.add(pushBox);
    box.add(pickupBox);
    box.add(examineBox);

    renderer.add(key);
    renderer.add(chest);
    renderer.add(door);
    renderer.add(kitten);
    renderer.add(table);
    renderer.add(switchObj);
    renderer.add(chair);
    renderer.add(box);

    renderer.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
          if (obj instanceof Key) {
            key.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Chest) {
            chest.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Door) {
            door.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Kitten) {
            kitten.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Table) {
            table.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Switch) {
            switchObj.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Chair) {
            chair.show(renderer, e.getX(), e.getY());
          } else if (obj instanceof Box) {
            box.show(renderer, e.getX(), e.getY());
          }

        }
      }
    });
    open.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        Chest chest = (Chest) obj;
        String color = chest.getChestColor();

        if (color.equals("red")) {
          if (chest.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
          } else {
            displayMessage("Requires a key to open");
          }

        } else if (color.equals("blue")) {
          if (chest.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
          } else {
            displayMessage("Requires a key to open");
          }
        } else if (color.equals("yellow")) {
          if (chest.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
          } else {
            displayMessage("Requires a key to open");
          }
        } else if (color.equals("green")) {
          if (chest.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();

            if (game.getWinCond() == game.getPlayer().totalAmtOfKittens()) {
              displayMessage("You have found all the kittens! \n" + "YOU WIN");

              int r = JOptionPane.showConfirmDialog(new JLabel("Restart Game?"), "Confirm Restart");

              if (r == JOptionPane.YES_OPTION) {
                new GameFrame();
              } else if (r == JOptionPane.NO_OPTION) {
                System.exit(0);
              }
            }

          } else {
            displayMessage("Requires a key to open");
          }
        } else if (color.equals("plain")) {
          if (chest.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();

            if (game.getWinCond() == game.getPlayer().totalAmtOfKittens()) {
              displayMessage("You have found all the kittens! \n" + "YOU WIN");

              int r = JOptionPane.showConfirmDialog(new JLabel("Restart Game?"), "Confirm Restart");

              if (r == JOptionPane.YES_OPTION) {
                new GameFrame();
              } else if (r == JOptionPane.NO_OPTION) {
                System.exit(0);
              }

            }

          } else {
            displayMessage("Requires a key to open");
          }
        }
      }
    });
    examineChest.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("That is a chest, maybe it can be opened?");
      }
    });
    examineDoor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A door, maybe you can go through other rooms with this");
      }
    });
    examineKey.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A key, maybe it can open something? ");
      }
    });
    examineKitten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A KITTEN! ");
      }
    });
    examineTable.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A Table, maybe its used for a puzzle?");
      }
    });
    examineBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A Box, maybe its used for a puzzle?");
      }
    });
    examineChair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A Chair, maybe its used for a puzzle?");
      }
    });
    examineSwitch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayMessage("A switch, it might do something if you activate it");
      }
    });
    enter.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Door door = (Door) obj;
        String color = door.getDoorColor();
        if (color.equals("yellow")) {
          if (obj.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
            displayMessage(game.getGuidence());

            Room room = (Room) game.getCurrentRoom();
            if (room instanceof PuzzleRoom) {
              displayMessage(((PuzzleRoom) room).getDescription());
            }
          } else {
            displayMessage("You do not have the requirements to open that door");
          }
        } else if (color.equals("red")) {
          if (obj.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
            displayMessage(game.getGuidence());

            Room room = (Room) game.getCurrentRoom();
            if (room instanceof PuzzleRoom) {
              displayMessage(((PuzzleRoom) room).getDescription());
            }
          } else {
            displayMessage("You do not have the requirements to open that door");
          }
        } else if (color.equals("blue")) {
          if (obj.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
            displayMessage(game.getGuidence());

            Room room = (Room) game.getCurrentRoom();
            if (room instanceof PuzzleRoom) {
              displayMessage(((PuzzleRoom) room).getDescription());
            }

          } else {
            displayMessage("You do not have the requirements to open that door");
          }
        } else if (color.equals("green")) {
          if (obj.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
            displayMessage(game.getGuidence());

            Room room = (Room) game.getCurrentRoom();
            if (room instanceof PuzzleRoom) {
              displayMessage(((PuzzleRoom) room).getDescription());
            }

          } else {
            displayMessage("You do not have the requirements to open that door");
          }
        } else if (color.equals("plain")) {
          if (obj.activate(game.getPlayer())) {
            setupPlayerPanel();
            renderScene();
            displayMessage(game.getGuidence());

            Room room = (Room) game.getCurrentRoom();
            if (room instanceof PuzzleRoom) {
              displayMessage(((PuzzleRoom) room).getDescription());
            } else {
              displayMessage("You do not have the requirements to open that door");
            }
          }
        }

      }
    });
    pickupKey.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (game.getPlayer().getInventoryList().contains(obj.toString())) {
          displayMessage("You already have that Key!");
        } else {
          game.pickUpItem(obj);
          setupPlayerPanel();
          renderScene();
        }

      }
    });
    pickupTable.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.pickUpItem(obj);
        setupPlayerPanel();
        renderScene();

      }
    });
    pickupChair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.pickUpItem(obj);
        setupPlayerPanel();
        renderScene();

      }
    });
    pickupBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.pickUpItem(obj);
        setupPlayerPanel();
        renderScene();

      }
    });
    pickupKitten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.pickUpItem(obj);
        setupPlayerPanel();
        renderScene();

        if (game.getWinCond() == game.getPlayer().totalAmtOfKittens()) {
          displayMessage("You have found all the kittens! \n" + "YOU WIN");

          int r = JOptionPane.showConfirmDialog(new JLabel("Restart Game?"), "Confirm Restart");

          if (r == JOptionPane.YES_OPTION) {
            new GameFrame();
          } else if (r == JOptionPane.NO_OPTION) {
            System.exit(0);
          }
        }

      }
    });
    pushTable.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.push(obj);
        renderScene();

      }
    });
    pushBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.push(obj);
        renderScene();

      }
    });
    pushChair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.push(obj);
        renderScene();

      }
    });
    activate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        obj.activate(game.getPlayer());
        renderScene();
        displayMessage("You have activated the switch");
      }
    });
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W) {
      moveUp();
    }
    if (e.getKeyCode() == KeyEvent.VK_S) {
      moveDown();
    }
    if (e.getKeyCode() == KeyEvent.VK_A) {
      moveLeft();
    }
    if (e.getKeyCode() == KeyEvent.VK_D) {
      moveRight();
    }
  }

  /**
   * Moves character right one square.
   */
  private void moveRight() {
    game.turn(game.getPlayer().getFacing().rotateRight());
    renderScene();

  }

  /**
   * Moves character left one square.
   */
  private void moveLeft() {
    game.turn(game.getPlayer().getFacing().rotateLeft());
    renderScene();


  }

  /**
   * Moves character down one square.
   */
  private void moveDown() {
    game.turn(game.getPlayer().getFacing().turnArround());
    renderScene();


  }

  /**
   * Moves character up one square.
   */
  private void moveUp() {
    game.move();
    renderScene();
  }

  /**
   * Displays the message on frame.
   *
   * @param message of input
   */
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Returns the current game for save and loading options.
   *
   * @return game world
   */
  public GameWorld getGame() {
    return game;
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void windowOpened(WindowEvent e) {}

  @Override
  public void windowIconified(WindowEvent e) {}

  @Override
  public void windowDeiconified(WindowEvent e) {}

  @Override
  public void windowDeactivated(WindowEvent e) {}

  @Override
  public void windowActivated(WindowEvent arg0) {}

  @Override
  public void windowClosed(WindowEvent arg0) {}

  @Override
  public void actionPerformed(ActionEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

}
