package application;

import gameworld.GameWorld;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.junit.jupiter.api.Test;
import renderer.Renderer;



class ApplicationTest {

  @Test
  void test_guiFrame() {
    GuiFrame game = new GuiFrame(new GameWorld());
    Renderer render = new Renderer(game);
    render.addMouseListener(render);
    game.addWindowStateListener(null);
    game.addWindowListener(game);
    game.addWindowFocusListener(null);

  }

  @Test
  void test_Click() throws AWTException {
    GameFrame game = new GameFrame();
    new MouseAction(new RenderPanel(game));
    game.requestFocusInWindow();
    Robot r = new Robot();
    r.mousePress(InputEvent.BUTTON3_MASK);
  }



  @Test
  void test_Help() {
    new HelpPanel(new RenderPanel(new GameFrame()));
  }

  @Test
  void test_Main() {
    new Main();
  }

  @Test
  void test_MenuPanel() {
    new MenuPanel(new GameFrame());
  }

  @Test
  void test_RenderPanel() {
    new RenderPanel(new GameFrame());
  }

  @Test
  void test_Setup() {
    new SetupPanel(new RenderPanel(new GameFrame()));
  }

  /*
   * @Test public void testEventListener() { ActionListener subjectUnderTest = new
   * MyActionListener(); ActionEvent mockEvent = GuiFrame(ActionEvent.class); }
   */

  @Test
  void test_movement() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_W);
    r.keyPress(KeyEvent.VK_S);
    r.keyPress(KeyEvent.VK_A);
    r.keyPress(KeyEvent.VK_D);
  }


  @Test
  void test_release() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.keyRelease(KeyEvent.VK_W);
    r.keyRelease(KeyEvent.VK_S);
    r.keyRelease(KeyEvent.VK_A);
    r.keyRelease(KeyEvent.VK_D);

  }

  @Test
  void test_Message() {
    GuiFrame game = new GuiFrame(new GameWorld());
    game.displayMessage(null);

  }

  @Test
  void test_hover() throws AWTException {
    new Main();
    Renderer renderer = new Renderer(new GuiFrame(new GameWorld()));
    renderer.requestFocusInWindow();
    Robot r = new Robot();
    r.mouseMove(100, 300);

  }

}
