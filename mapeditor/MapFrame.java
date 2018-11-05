package mapeditor;

import gameworld.GameObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


/**
 * Map frame is the main frame component of MapEditor.
 *
 * @author Rachel Hawkins
 *
 */
@SuppressWarnings("serial")
public class MapFrame extends JFrame {
  private JTextPane info;
  private JFrame frame;
  private JPanel modePanel;
  private JPanel placementPanel;
  private JPanel editPanel;
  private JPanel templatePanel;
  private Mouse mouse;

  private Grid grid;

  private GameObject selectedObject;
  private String selected = "Room";
  private String roomName;
  private String selectedColor = "plain";
  private String editState;

  private int templateSelected = 1;

  private boolean switchOn = false;

  /**
   * MapFrame.
   */
  public MapFrame() {
    this.editState = "PLACE";

    frame = new JFrame("Map Editor");
    frame.setVisible(true);
    frame.setSize(941, 601);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel buffer = new JPanel();
    buffer.setPreferredSize(new Dimension(20, 600));
    buffer.setBackground(Color.DARK_GRAY);
    frame.add(buffer, BorderLayout.WEST);

    // left panel, change graphics from within grids overridden paint component
    this.grid = new Grid(this);
    grid.setBackground(Color.LIGHT_GRAY);
    grid.setVisible(true);
    frame.add(grid, BorderLayout.CENTER);

    mouse = new Mouse(this, grid);
    grid.addMouseListener(this.mouse);

    // adds the buttons on the right side of the pane.
    ButtonPanel buttons = new ButtonPanel(grid, this);
    frame.add(buttons, BorderLayout.NORTH);

    this.modePanel = new JPanel();
    modePanel.setBackground(Color.DARK_GRAY);
    modePanel.setPreferredSize(new Dimension(520, 550));

    // sets both the placement and editing panels side by side.
    placementPanel = new PlacementPanel(this.grid, this);
    editPanel = new EditingPanel(this.grid, this);
    templatePanel = new TemplatePanel(grid, this);

    templatePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    editPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    placementPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    modePanel.add(placementPanel, BorderLayout.WEST);
    modePanel.add(editPanel, BorderLayout.CENTER);
    modePanel.add(templatePanel, BorderLayout.EAST);
    frame.add(modePanel, BorderLayout.EAST);

    // sets up the information pane at the bottom of the screen
    this.info = new JTextPane();
    info.setBackground(Color.DARK_GRAY);
    info.setForeground(Color.WHITE);
    info.setPreferredSize(new Dimension(660, 28));
    info.setFont(new Font("Ariel", Font.PLAIN, 15));
    info.setEditable(false);
    frame.add(info, BorderLayout.SOUTH);


  }


  /**
   * Sets the bottom text panel.
   *
   * @param s the text to be displayed.
   */
  public void setInfoBox(String s) {
    this.info.setText("               " + s);
  }

  /**
   * Sets the mode of the map editor, can be place, edit, template or fin.
   *
   * @param s what mode the editor should be in.
   */
  public void changeMode(String s) {
    if (s.equals("PLACE") || s.equals("EDIT") || s.equals("FIN")) {
      this.editState = s;
    }
  }

  /**
   * Shows what the current text in editstate text box is.
   *
   * @return String currently contained in editstate.
   */
  public String getEditState() {
    return this.editState;
  }

  /**
   * Getter for selected.
   *
   * @return String of selected
   */
  public String getSelected() {
    return this.selected;
  }

  /**
   * Setter for selected.
   *
   * @param s sets the selected string
   */
  public void setSelected(String s) {
    this.selected = s;
  }

  /**
   * Getters of selected color, used for doors etc.
   *
   * @return string of color, red, blue, green, yellow, plain
   */
  public String getSelectedColor() {
    return this.selectedColor;
  }

  /**
   * Setter of selected color.
   *
   * @param s current selected color.
   */
  public void setSelectedColor(String s) {
    this.selectedColor = s;
  }

  /**
   * Getter for the selected object.
   *
   * @return Selected object.
   */
  public GameObject getSelectedObject() {
    return this.selectedObject;
  }

  /**
   * Setter of selected object.
   *
   * @param o new selected object.
   */
  public void setSelectedObject(GameObject o) {
    this.selectedObject = o;
  }

  /**
   * Gets Room name.
   *
   * @return the current room name.
   */
  public String getRoomName() {
    return roomName;
  }

  /**
   * Sets room name.
   *
   * @param roomName the new room name.
   */
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  /**
   * Getter for the placement panel held in mode.
   *
   * @return Placement Panel object.
   */
  public JPanel getPlacement() {
    return this.placementPanel;
  }

  /**
   * Getter for the editing panel held in mode.
   *
   * @return Editing Panel object.
   */
  public JPanel getEdit() {
    return this.editPanel;
  }

  /**
   * Gets the Template Panel held in mode.
   *
   * @return Template Panel object.
   */
  public JPanel getTemplate() {
    return templatePanel;
  }

  /**
   * Switch getter, next item selected will be the corresponding switch item.
   *
   * @return if switch is on.
   */
  public boolean isSwitchOn() {
    return switchOn;
  }

  /**
   * Switch on setter.
   *
   * @param on on or off.
   */
  public void setSwitchOn(boolean on) {
    this.switchOn = on;
  }

  /**
   * sets room selection.
   *
   * @return selected room
   */
  public int getTemplateRoomSelection() {
    return this.templateSelected;
  }

  /**
   * gets room selection.
   *
   * @param m new room selection
   */
  public void setTemplateRoomSelection(int m) {
    this.templateSelected = m;
  }

  /**
   * Run the game.
   * @param args argument
   */
  public static void main(String[] args) {
    new MapFrame();
  }

}
