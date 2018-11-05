package mapeditor;

import gameworld.GameObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;



/**
 * Holds graphical user interface for the editing panel.
 *
 * @author Rachel Hawkins
 */
@SuppressWarnings("serial")
public class EditingPanel extends JPanel {
  private JTextPane textSelected;

  /**
   * Editing Panel.
   *
   * @param grid of layout
   * @param gui of the layout
   */
  public EditingPanel(Grid grid, MapFrame gui) {
    setPreferredSize(new Dimension(160, 500));
    setBackground(Color.DARK_GRAY);
    setVisible(true);

    this.textSelected = new JTextPane();
    textSelected.setFont(new Font("Ariel", Font.PLAIN, 13));
    textSelected.setPreferredSize(new Dimension(140, 400));
    add(textSelected, BorderLayout.NORTH);

    JPanel bufferTwo = new JPanel();
    bufferTwo.setBackground(Color.DARK_GRAY);
    add(bufferTwo, BorderLayout.CENTER);

    // =================DELETE===================//
    JButton remove = new JButton(" R e m o v e ");
    remove.setBackground(Color.WHITE);
    remove.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(remove, BorderLayout.SOUTH);;

    remove.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GameObject g = grid.getItemAt(grid.getSelectedX(), grid.getSelectedY(), grid.getMouseX(),
            grid.getMouseY());
        if (g != null) {
          gui.setInfoBox("Object : " + g.toString());
          grid.removeItem();
          gui.setSelectedObject(null);
        } else {
          grid.removeRoom();
        }
      }
    });



  }

  /**
   * Setter for selected text.
   *
   * @param s new text
   */
  public void setSelected(String s) {
    textSelected.setText(s);
  }
}
