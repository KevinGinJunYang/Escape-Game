package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main game frame that sets the UI in its positions.
 *
 * @author kevin
 *
 */
public class GameFrame extends JFrame implements WindowListener {
  /**
   * SerialVersion.
   */
  private static final long serialVersionUID = 1L;
  private RenderPanel panel;
 
  /**
   * Game frame of GUI that is the main menu.
   */
  public GameFrame() {
    super("Game - team 17");
    panel = new RenderPanel(this);
    new MenuPanel(this);
    setSize(1080, 860);
    setMinimumSize(new Dimension(1080, 860));
    add(panel, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(null);
    addWindowListener(this);
    setVisible(true);
  }

  @Override
  public void windowClosing(WindowEvent e) {
    int confirmed =
        JOptionPane.showConfirmDialog(null, "Quit game?", "Warning", JOptionPane.YES_NO_OPTION);

    if (confirmed == JOptionPane.YES_OPTION) {
      System.exit(0);
    } 
  }

  @Override
  public void windowClosed(WindowEvent e) {}

  @Override
  public void windowIconified(WindowEvent e) {}

  @Override
  public void windowDeiconified(WindowEvent e) {}

  @Override
  public void windowActivated(WindowEvent e) {}

  @Override
  public void windowDeactivated(WindowEvent e) {}

  @Override
  public void windowOpened(WindowEvent e) {}


}
