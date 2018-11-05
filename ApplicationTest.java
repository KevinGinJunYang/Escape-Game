import application.GameFrame;
import application.GuiFrame;
import application.HelpPanel;
import application.Main;
import application.MenuPanel;
import application.MouseAction;
import application.RenderPanel;
import application.SetupPanel;
import gameworld.GameWorld;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.junit.Test;
import renderer.Renderer;

/**
 * Applcation Tests.
 * @author yangkevi
 *
 */
public class ApplicationTest {

  /**
   * Tests the gui Frame.
   */
  @Test
  public void test_guiFrame() {
    GuiFrame game = new GuiFrame(new GameWorld());
    Renderer render = new Renderer(game);
    render.addMouseListener(render);
    game.addWindowStateListener(null);
    game.addWindowListener(game);
    game.addWindowFocusListener(null);

  }

  /**
   * Tests click.
   * @throws AWTException exception.
   */
  @Test
  public void test_Click() throws AWTException {
    GameFrame game = new GameFrame();
    new MouseAction(new RenderPanel(game));
    game.requestFocusInWindow();
    Robot r = new Robot();
    r.mousePress(InputEvent.BUTTON3_MASK);
  }



  /**
   * Tests help.
   */
  @Test
  public void test_Help() {
    new HelpPanel(new RenderPanel(new GameFrame()));
  }

  /**
   * Tests main.
   */
  @Test
  public void test_Main() {
    new Main();
  }

  /**
   * Tests menu.
   */
  @Test
  public void test_MenuPanel() {
    new MenuPanel(new GameFrame());
  }

  /**
   * Tests Render.
   */
  @Test
  public void test_RenderPanel() {
    new RenderPanel(new GameFrame());
  }

  /**
   * Tests setup.
   */
  @Test
  public void test_Setup() {
    new SetupPanel(new RenderPanel(new GameFrame()));
  }

  /*
   * @Test public public void testEventListener() { ActionListener subjectUnderTest = new
   * MyActionListener(); ActionEvent mockEvent = GuiFrame(ActionEvent.class); }
   */

  /**
   * Tests movement.
   * @throws AWTException exception.
   */
  @Test
  public void test_movement() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_W);
    r.keyPress(KeyEvent.VK_S);
    r.keyPress(KeyEvent.VK_A);
    r.keyPress(KeyEvent.VK_D);
  }


  /**
   * Tests release.
   * @throws AWTException exception.
   */
  @Test
  public void test_release() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.keyRelease(KeyEvent.VK_W);
    r.keyRelease(KeyEvent.VK_S);
    r.keyRelease(KeyEvent.VK_A);
    r.keyRelease(KeyEvent.VK_D);

  }

  /**
   * Tests message.
   */
  @Test
  public void test_Message() {
    GuiFrame game = new GuiFrame(new GameWorld());
    game.displayMessage(null);

  }

  /**
   * Tests hover.
   * @throws AWTException exception.
   */
  @Test
  public void test_hover() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.mouseMove(100, 300);

  }

}
