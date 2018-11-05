package mapeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;
import persistence.Parser;

/**
 * Button Panel that gets all buttons.
 *
 * @author Rachel Hawkins
 *
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
  private JTextArea textTitle;
  private Grid grid;
  private Color background = Color.DARK_GRAY;
  protected String fname;

  /**
   * Button Panel.
   * @param grids the grid of the layout
   * @param gui the that opens the layout
   *
   */
  public ButtonPanel(Grid grids, MapFrame gui) {
    super(new GridBagLayout());
    setSize(600, 40);
    setBackground(background);

    this.grid = grids;

    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 0.3;

    this.textTitle = new JTextArea();
    textTitle.setFont(new Font("Ariel", Font.BOLD, 18));
    textTitle.setText("   M a p    E d i t o r   ");
    c.gridx = 0;
    add(textTitle, c);

    c.weightx = 0.05;


    // =================save===================//
    c.gridx = 2;
    JButton save = new JButton("  S a v e  ");
    save.setBackground(Color.WHITE);
    save.setBorder(BorderFactory.createLineBorder(background, 0, true));
    add(save, c);

    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          try {
            Parser.save(grid.exportSave(), file);
          } catch (JAXBException ex) {
            ex.printStackTrace();
          }
        }
      }
    });

    // ==========clear===================\\
    JButton clear = new JButton("  C l e a r  ");
    c.gridx = 3;
    clear.setBackground(Color.WHITE);
    clear.setBorder(BorderFactory.createLineBorder(background, 0, true));
    add(clear, c);

    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        grid.clear();
      }
    });

    // ================PLACE===================//
    c.gridx = 4;
    JButton place = new JButton("  P l a c e m e n t   M o d e  ");
    place.setBackground(Color.WHITE);
    place.setBorder(BorderFactory.createLineBorder(background, 0, true));
    add(place, c);

    place.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.changeMode("PLACE");
        gui.setInfoBox("Placement Mode Enabled");
        gui.getTemplate().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getEdit().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getPlacement().setBorder(BorderFactory.createLineBorder(Color.BLUE));
      }
    });

    // =================EDIT===================//
    c.gridx = 5;
    JButton edit = new JButton("  E d i t i n g   M o d e  ");
    edit.setBackground(Color.WHITE);
    edit.setBorder(BorderFactory.createLineBorder(background, 0, true));
    add(edit, c);

    edit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.changeMode("EDIT");
        gui.setInfoBox("Editing Mode Enabled");
        gui.getPlacement().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getTemplate().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getEdit().setBorder(BorderFactory.createLineBorder(Color.BLUE));
      }
    });


    // =================TEMPLATE===================//
    c.gridx = 6;
    JButton template = new JButton("  T e m p l a t e   M o d e  ");
    template.setBackground(Color.WHITE);
    template.setBorder(BorderFactory.createLineBorder(background, 0, true));
    add(template, c);

    template.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.changeMode("TEMPLATE");
        gui.setInfoBox("Template Mode Enabled");
        gui.getPlacement().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getEdit().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gui.getTemplate().setBorder(BorderFactory.createLineBorder(Color.BLUE));
      }
    });

  }


}
