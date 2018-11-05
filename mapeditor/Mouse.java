package mapeditor;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Door;
import gameworld.GameObject;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Switch;
import gameworld.Table;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


/**
 * Used for the map editor mouse clicks.
 *
 * @author Rachel Hawkins
 */
public class Mouse implements MouseListener {
  private Grid grid;
  private MapFrame gui;

  private String rname = "";

  /**
   * Mouse movement of editor.
   *
   * @param gui the game gui
   * @param grid grid of the layout
   */
  public Mouse(MapFrame gui, Grid grid) {
    this.gui = gui;
    this.grid = grid;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (gui.getEditState().equals("EDIT")) {
      // finds the item that has been selected
      GameObject g = grid.getItemAt(colFromMouse(e.getX()), rowFromMouse(e.getY()), (int) e.getX(),
          (int) e.getY());
      EditingPanel edit = (EditingPanel) gui.getEdit();

      if (g == null) {
        edit.setSelected("Room: " + grid.getRoomId(colFromMouse(e.getX()), rowFromMouse(e.getY())));
        return;
      } else {
        edit.setSelected("Room " + grid.getRoomId(colFromMouse(e.getX()), rowFromMouse(e.getY()))
            + ". Item contained " + g.toString());
        gui.setSelectedObject(g);
      }


      // set selected item for the switch
      if (gui.isSwitchOn()) {
        gui.setSelectedObject(g);
        gui.setInfoBox("Selected item is " + g.toString()
            + ". If this is the correct object, click the match button to place switch.");
      }
    }

    if (gui.getEditState().equals("PLACE")) {
      this.add(e);
    }

    if (gui.getEditState().equals("TEMPLATE")) {
      template(e);
    }
  }

  /**
   * adds all types of objects, including rooms.
   *
   * @param e mouse click
   */
  private void add(MouseEvent e) {
    if (gui.getSelected().equals("Room")) {
      grid.addRoom(colFromMouse(e.getX()), rowFromMouse(e.getY()), rname);
    } else if (gui.getSelected().equals("Player")) {
      grid.addPlayer(colFromMouse(e.getX()), rowFromMouse(e.getY()));
    } else if (gui.getSelected().equals("Door")) {
      grid.addDoor(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
          gui.getSelectedColor());
    } else if (gui.getSelected().equals("Kitten")) {
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
          new Kitten());
    } else if (gui.getSelected().equals("Box")) { // Box
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(), new Box());
    } else if (gui.getSelected().equals("Chair")) { // Chair
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(), new Chair());
    } else if (gui.getSelected().equals("Chest")) { // Chest
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
          new Chest(gui.getSelectedColor()));
    } else if (gui.getSelected().equals("Key")) { // Key
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
          new Key(gui.getSelectedColor()));
    } else if (gui.getSelected().equals("Switch")) { // Switch
      // has to correspond to a game object therefore must check if one has been selected
      if (gui.getSelectedObject() == null) {
        JOptionPane.showMessageDialog(gui,
            "You need to choose a corresponding door or chest object that is unlocked "
                + "when the switch is activated, before you can place a switch.\n"
                + "To do so press the 'match' button and slect the item that the switch\n"
                + "will relate to before trying to place switch.",
            "", JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      if (gui.getSelectedObject() instanceof Door) {
        grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
            new Switch((Door) gui.getSelectedObject()));
      }
      if (gui.getSelectedObject() instanceof Chest) {
        grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(),
            new Switch((Chest) gui.getSelectedObject()));
      }

    } else if (gui.getSelected().equals("Table")) { // Table
      grid.addItem(colFromMouse(e.getX()), rowFromMouse(e.getY()), e.getX(), e.getY(), new Table());
    }
  }

  /**
   * The action needed to take for template state.
   *
   * @param e mouse event
   */
  public void template(MouseEvent e) {
    gui.setInfoBox("Selected template room equals " + gui.getTemplateRoomSelection());
    grid.addTemplateRoom(colFromMouse(e.getX()), rowFromMouse(e.getY()),
        gui.getTemplateRoomSelection());
  }

  /**
   * Distance from mouse to column.
   *
   * @param xpress the x press
   * @return the column number of the mouse position
   */
  public int colFromMouse(float xpress) {
    if (0 <= xpress && xpress <= 130) {
      return 0;
    } else if (130 <= xpress && xpress <= 260) {
      return 1;
    } else if (260 <= xpress && xpress <= 390) {
      return 2;
    }
    return -20;
  }

  /**
   * Distance from mouse to row.
   *
   * @param ypress the y press
   * @return the row number of the click.
   */
  public int rowFromMouse(float ypress) {
    if (0 <= ypress && ypress <= 130) {
      return 0;
    } else if (130 <= ypress && ypress <= 260) {
      return 1;
    } else if (260 <= ypress && ypress <= 390) {
      return 2;
    } else if (390 <= ypress && ypress <= 520) {
      return 3;
    }
    return 20;
  }

  /**
   * Not currently used.
   */
  @Override
  public void mouseEntered(MouseEvent e) {}

  /**
   * Not currently used.
   */
  @Override
  public void mouseExited(MouseEvent e) {}

  /**
   * Not currently used.
   */
  @Override
  public void mousePressed(MouseEvent e) {}

  /**
   * Currently not used.
   */
  @Override
  public void mouseReleased(MouseEvent e) {}



}
