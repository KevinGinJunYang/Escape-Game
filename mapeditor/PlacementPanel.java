package mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * First panel in the frame, used to place items. State name is PLACE.
 *
 * @author Rachel Hawkins
 */
@SuppressWarnings("serial")
public class PlacementPanel extends JPanel {

  // presentation
  /**
   * Font used.
   */
  public static final Font font = new Font("Ariel", Font.PLAIN, 15);
  /**
   * Background color.
   */
  public static final Color backgroundColor = Color.DARK_GRAY;
  /**
   * Foreground color.
   */
  public static final Color foregroundColor = Color.WHITE;



  /**
   * Sets up the panels graphical elements.
   *
   * @param grid access to grid.
   * @param gui easy access to enclosing frame.
   */
  public PlacementPanel(Grid grid, MapFrame gui) {
    setLayout(new GridLayout(10, 2));

    setPreferredSize(new Dimension(150, 500));
    setBackground(backgroundColor);
    setVisible(true);

    JRadioButton room = new JRadioButton("Room");
    room.setBackground(backgroundColor);
    room.setForeground(foregroundColor);
    room.setFont(font);
    add(room);

    JRadioButton player = new JRadioButton("Player");
    player.setBackground(backgroundColor);
    player.setForeground(foregroundColor);
    player.setFont(font);
    add(player);

    JRadioButton door = new JRadioButton("Door");
    door.setBackground(backgroundColor);
    door.setForeground(foregroundColor);
    door.setFont(font);
    add(door);

    JRadioButton kitten = new JRadioButton("Kitten");
    kitten.setBackground(backgroundColor);
    kitten.setForeground(foregroundColor);
    kitten.setFont(font);
    add(kitten);

    JRadioButton box = new JRadioButton("Box");
    box.setBackground(backgroundColor);
    box.setForeground(foregroundColor);
    box.setFont(font);
    add(box);

    JRadioButton chair = new JRadioButton("Chair");
    chair.setBackground(backgroundColor);
    chair.setForeground(foregroundColor);
    chair.setFont(font);
    add(chair);

    JRadioButton chest = new JRadioButton("Chest");
    chest.setBackground(backgroundColor);
    chest.setForeground(foregroundColor);
    chest.setFont(font);
    add(chest);

    JRadioButton key = new JRadioButton("Key");
    key.setBackground(backgroundColor);
    key.setForeground(foregroundColor);
    key.setFont(font);
    add(key);

    JRadioButton switchs = new JRadioButton("Switch");
    switchs.setBackground(backgroundColor);
    switchs.setForeground(foregroundColor);
    switchs.setFont(font);
    add(switchs);

    JRadioButton table = new JRadioButton("Table");
    table.setBackground(backgroundColor);
    table.setForeground(foregroundColor);
    table.setFont(font);
    add(table);

    ButtonGroup grouping = new ButtonGroup();
    grouping.add(room);
    grouping.add(player);
    grouping.add(door);
    grouping.add(kitten);
    grouping.add(box);
    grouping.add(chair);
    grouping.add(chest);
    grouping.add(key);
    grouping.add(switchs);
    grouping.add(table);

    room.setSelected(true);

    room.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Room");
      }
    });

    player.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        gui.setSelected("Player");
      }

    });

    door.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Door");
      }
    });

    kitten.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Kitten");
      }
    });

    box.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Box");
      }
    });

    chair.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Chair");
      }
    });

    chest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Chest");
      }
    });

    switchs.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Switch");
      }
    });

    key.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Key");
      }
    });

    table.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gui.setSelected("Table");
      }
    });

    JPanel bufferOne = new JPanel();
    bufferOne.setBackground(Color.DARK_GRAY);
    add(bufferOne);

    JPanel bufferTwo = new JPanel();
    bufferTwo.setBackground(Color.DARK_GRAY);
    add(bufferTwo);

    JButton red = new JButton(" R e d ");
    red.setBackground(Color.WHITE);
    red.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(red);

    JButton blue = new JButton(" B l u e ");
    blue.setBackground(Color.WHITE);
    blue.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(blue);

    JButton green = new JButton(" G r e e n ");
    green.setBackground(Color.WHITE);
    green.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(green);

    JButton yellow = new JButton(" Y e l l o w ");
    yellow.setBackground(Color.WHITE);
    yellow.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(yellow);

    red.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedColor().equals("red")) {
          gui.setSelectedColor("plain");

          red.setForeground(Color.BLACK);
          red.setBackground(Color.WHITE);

          return;
        }
        gui.setSelectedColor("red");

        red.setForeground(Color.WHITE);
        blue.setForeground(Color.BLACK);

        red.setBackground(Color.RED);
        blue.setBackground(Color.WHITE);
        green.setBackground(Color.WHITE);
        yellow.setBackground(Color.WHITE);
      }
    });
    blue.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedColor().equals("blue")) {
          gui.setSelectedColor("plain");

          blue.setForeground(Color.BLACK);
          blue.setBackground(Color.WHITE);

          return;
        }

        gui.setSelectedColor("blue");

        red.setForeground(Color.BLACK);
        blue.setForeground(Color.WHITE);

        red.setBackground(Color.WHITE);
        blue.setBackground(Color.BLUE);
        green.setBackground(Color.WHITE);
        yellow.setBackground(Color.WHITE);
      }
    });
    green.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedColor().equals("green")) {
          gui.setSelectedColor("plain");

          green.setBackground(Color.WHITE);

          return;
        }

        gui.setSelectedColor("green");

        red.setForeground(Color.BLACK);
        blue.setForeground(Color.BLACK);

        red.setBackground(Color.WHITE);
        blue.setBackground(Color.WHITE);
        green.setBackground(Color.GREEN);
        yellow.setBackground(Color.WHITE);
      }
    });
    yellow.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedColor().equals("yellow")) {
          gui.setSelectedColor("plain");

          yellow.setBackground(Color.WHITE);
          return;
        }

        gui.setSelectedColor("yellow");

        red.setForeground(Color.BLACK);
        blue.setForeground(Color.BLACK);

        red.setBackground(Color.WHITE);
        blue.setBackground(Color.WHITE);
        green.setBackground(Color.WHITE);
        yellow.setBackground(Color.YELLOW);
      }
    });

    JPanel bufferThree = new JPanel();
    bufferThree.setBackground(Color.DARK_GRAY);
    add(bufferThree);

    JPanel bufferFour = new JPanel();
    bufferFour.setBackground(Color.DARK_GRAY);
    add(bufferFour);

    JButton corespond = new JButton("M a t c h");
    corespond.setBackground(Color.WHITE);
    corespond.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0, true));
    add(corespond);

    corespond.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // on
        if (corespond.getBackground() == Color.CYAN) {
          gui.setSwitchOn(false);
          corespond.setBackground(Color.WHITE);
          gui.setInfoBox("Match unset, create the switch object.");
          // so we can place switch
          gui.changeMode("PLACE");
          return;
        }
        // off
        gui.setInfoBox(
            "Match has been set, choose item that corresponds to the switch that will be placed");
        gui.setSwitchOn(true);
        corespond.setBackground(Color.CYAN);
        // so we can set selected
        gui.changeMode("EDIT");
      }
    });
  }


}
