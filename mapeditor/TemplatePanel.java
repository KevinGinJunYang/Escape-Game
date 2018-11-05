package mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Panel that controls all template puzzle rooms.
 *
 * @author Rachel Hawkins
 *
 */
@SuppressWarnings("serial")
public class TemplatePanel extends JPanel {

  private ImageIcon one = null;
  private ImageIcon two = null;
  private ImageIcon three = null;
  private ImageIcon four = null;

  /**
   * The Panel that controls all template puzzle rooms.
   *
   * @param grid the grid of the layout
   * @param gui of the layout
   */
  public TemplatePanel(Grid grid, MapFrame gui) {
    setPreferredSize(new Dimension(160, 500));
    setBackground(Color.DARK_GRAY);
    setVisible(true);

    one = new ImageIcon("src/mapeditor/puzzleImg1.png");
    two = new ImageIcon("src/mapeditor/puzzleImg2.png");
    three = new ImageIcon("src/mapeditor/puzzleImg3.png");
    four = new ImageIcon("src/mapeditor/puzzleImg4.png");

    JButton first = new JButton(one);
    first.setBackground(Color.DARK_GRAY);
    add(first);

    first.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setTemplateRoomSelection(1);
      }

    });

    JButton second = new JButton(two);
    second.setBackground(Color.DARK_GRAY);
    add(second);

    second.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setTemplateRoomSelection(2);
      }

    });

    JButton third = new JButton(three);
    third.setBackground(Color.DARK_GRAY);
    add(third);

    third.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setTemplateRoomSelection(3);
      }

    });

    JButton fourth = new JButton(four);
    fourth.setBackground(Color.DARK_GRAY);
    add(fourth);

    fourth.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setTemplateRoomSelection(4);
      }

    });


  }

}
