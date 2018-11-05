package mapeditor;

import javax.swing.JFrame;
import junit.framework.TestCase;

/**
 * Class to test the swing components of the MapEditor and Application.
 *
 * @author Rachel Hawkins
 *
 */
public class SwingTestCase extends TestCase {

  private JFrame testFrame;

  /**
   * Tear down of map editor.
   */
  public void tearDown() throws Exception {
    if (this.testFrame != null) {
      this.testFrame.dispose();
      this.testFrame = null;
    }
  }

  /**
   * Gets the frame used for testing.
   *
   * @return Test Frame.
   */
  public JFrame getTestFrame() {
    if (this.testFrame == null) {
      this.testFrame = new JFrame("Test");
    }
    return this.testFrame;
  }
}
