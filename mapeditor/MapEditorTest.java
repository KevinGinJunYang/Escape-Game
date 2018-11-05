package mapeditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gameworld.Direction;
import gameworld.Kitten;
import gameworld.Room;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import org.junit.jupiter.api.Test;



class MapEditorTest {


  // Testing Grid
  @Test
  void drawTest1() {
    MapFrame e = new MapFrame();
    System.out.println("1");
    e.getEdit();
    e.getEditState();
    e.getSelected();
    e.getPlacement();
    e.getRoomName();
    e.getTemplate();
    System.out.println("Gethere");
    e.getTemplateRoomSelection();
    Grid g = new Grid(e);
    g.addRoom(0, 0, "");
    g.addRoom(0, 1, "");
    g.addRoom(0, 2, "");
    g.addTemplateRoom(1, 1, 2);
    g.addItem(0, 0, 2, 2, new Kitten());


  }

  @Test
  void setRoomTest1() {
    Grid g = new Grid();
    g.addRoom(0, 0, "P");
    Room[][] rooms = g.getGrid();
    assertEquals(true, rooms[0][0].getValid());

  }

  @Test
  void setRoomTest2() {
    Grid g = new Grid();

    // test room graphics
    g.addRoom(0, 0, "test");
    g.addRoom(0, 1, "test");
    g.addPlayer(0, 1);


  }

  @Test
  void addTemplateRoomTest1() {
    Grid g = new Grid();
    g.addTemplateRoom(0, 0, 1);
    assert (g.getGrid()[0][0].getValid());
  }

  @Test
  void delRoomTest1() {
    Grid g = new Grid();

    // test room graphics
    g.addRoom(0, 0, "test");
    g.addRoom(0, 1, "test");
    g.setSelectedX(0);
    g.setSelectedY(0);
    g.removeRoom();
    assert (g.getGrid()[0][1].getValid());
    assert (!g.getGrid()[0][0].getValid());
  }

  @Test
  void testExport() {
    Grid g = new Grid();
    g.addRoom(0, 0, "");
    g.exportSave();
  }

  @Test
  void setPlayerTest1() {
    Grid g = new Grid();
    g.addRoom(0, 0, "X");
    g.addRoom(0, 1, "C");
    g.addPlayer(0, 1);
    Room[][] rooms = g.getGrid();
    assertEquals(true, rooms[0][1].getValid());
  }

  @Test
  void setDoorTest1() {
    // EAST
    MapFrame m = new MapFrame();
    Grid g = new Grid(m);
    g.addRoom(1, 1, "M");
    g.addRoom(2, 1, "P");
    g.addDoor(1, 1, 249, 197, "red");
  }

  @Test
  void setDoorTest2() {
    // NORTH
    MapFrame m = new MapFrame();
    Grid g = new Grid(m);
    g.addRoom(1, 0, "R");
    g.addRoom(1, 1, "F");
    g.addDoor(1, 1, 197, 140, "blue");

  }

  @Test
  void setDoorTest3() {
    // SOUTH
    MapFrame m = new MapFrame();
    Grid g = new Grid(m);
    g.addRoom(1, 2, "M");
    g.addRoom(1, 1, "P");
    g.addDoor(1, 1, 190, 240, "blue");
    assertEquals(g.getItemAt(1, 1, 190, 240).toString(), "blue Door");
  }

  @Test
  void setDoorTest4() {
    // WEST
    MapFrame m = new MapFrame();
    Grid g = new Grid(m);
    g.addRoom(0, 1, "M");
    g.addRoom(1, 1, "P");
    g.addDoor(1, 1, 141, 197, "blue");
    assertEquals(g.getItemAt(1, 1, 141, 197).toString(), "blue Door");
  }

  @Test
  void getNeighborTest1() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(0, 1, "P");

    assertEquals(g.getNeighbor(1, Direction.SOUTH), 2);
  }

  @Test
  void getNeighborTest2() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(1, 0, "P");

    assertEquals(g.getNeighbor(2, Direction.NORTH), 1);
  }

  @Test
  void getNeighborTest3() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(0, 1, "P");

    assertEquals(g.getNeighbor(1, Direction.EAST), 5);
  }


  @Test
  void getNeighborTest4() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(0, 1, "P");

    assertEquals(g.getNeighbor(5, Direction.WEST), 1);
  }

  // EASTSIDE
  @Test
  void getNeighborTest5() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(0, 1, "P");
    g.addRoom(0, 2, "P");
    g.addRoom(0, 3, "P");

    assertEquals(g.getNeighbor(1, Direction.WEST), -1);
    assertEquals(g.getNeighbor(2, Direction.WEST), -1);
    assertEquals(g.getNeighbor(3, Direction.WEST), -1);
    assertEquals(g.getNeighbor(4, Direction.WEST), -1);

  }

  @Test
  void getNeighborTest6() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(0, 1, "P");
    g.addRoom(0, 2, "P");
    g.addRoom(0, 3, "P");

    assertEquals(g.getNeighbor(9, Direction.EAST), -1);
    assertEquals(g.getNeighbor(10, Direction.EAST), -1);
    assertEquals(g.getNeighbor(11, Direction.EAST), -1);
    assertEquals(g.getNeighbor(12, Direction.EAST), -1);
  }

  @Test
  void getNeighborTest7() {
    Grid g = new Grid();
    g.addRoom(0, 0, "M");
    g.addRoom(1, 0, "P");
    g.addRoom(2, 0, "P");

    assertEquals(g.getNeighbor(1, Direction.NORTH), -1);
    assertEquals(g.getNeighbor(5, Direction.NORTH), -1);
    assertEquals(g.getNeighbor(9, Direction.NORTH), -1);
  }

  @Test
  void getNeighborTest8() {
    Grid g = new Grid();
    g.addRoom(0, 3, "M");
    g.addRoom(1, 3, "P");
    g.addRoom(2, 3, "P");

    assertEquals(g.getNeighbor(4, Direction.SOUTH), -1);
    assertEquals(g.getNeighbor(8, Direction.SOUTH), -1);
    assertEquals(g.getNeighbor(12, Direction.SOUTH), -1);
  }

  @Test
  void getNeighborTest9() {
    Grid g = new Grid();
    g.addRoom(1, 1, "P");

    assertEquals(g.getNeighbor(6, Direction.NORTH), 0);
    assertEquals(g.getNeighbor(6, Direction.SOUTH), 0);
    assertEquals(g.getNeighbor(6, Direction.EAST), 0);
    assertEquals(g.getNeighbor(6, Direction.WEST), 0);
  }

  @Test
  void setItem1() {
    Grid g = new Grid();
    g.addRoom(0, 0, "");
    g.addItem(0, 0, 2, 2, new Kitten());
    assert (g.getGrid()[0][0].getItemAt(0, 0) instanceof Kitten);
    g.setSelectedX(0);
    g.setSelectedY(0);
    g.setMouseX(3);
    g.setMouseY(5);
    g.getSelectedX();
    g.getSelectedY();
    g.getMouseX();
    g.getMouseY();
    g.removeItem();
    assert (g.getGrid()[0][0].getItemAt(0, 0) == null);

  }

  @Test
  void testClear() {
    Grid g = new Grid();
    g.addRoom(1, 1, "");
    g.clear();
    assert (!g.getGrid()[1][1].getValid());
  }

  @Test
  void findTest1() {
    Grid g = new Grid();
    int res = g.findX(0, 30);
    assertEquals(1, res);
  }

  @Test
  void findTest2() {
    Grid g = new Grid();
    int res = g.findY(0, 30);
    assertEquals(1, res);
  }

  @Test
  void findTest3() {
    Grid g = new Grid();
    int res = g.findX(1, 181);
    assertEquals(1, res);
  }

  @Test
  void findTest4() {
    Grid g = new Grid();
    int res = g.findX(2, 262);
    assertEquals(0, res);
  }

  @Test
  void findTest5() {
    Grid g = new Grid();
    int res = g.findY(1, 140);
    assertEquals(0, res);
  }

  @Test
  void findTest6() {
    Grid g = new Grid();
    int res = g.findY(3, 400);
    assertEquals(0, res);
  }

  // Test MapFrame

  // Test Mouse
  @Test
  void mouseTest1() {

    MapFrame m = new MapFrame();
    Grid grid = new Grid(m);

    Mouse mouse = new Mouse(null, grid);
    assertEquals(mouse.rowFromMouse(120), 0);
    assertEquals(mouse.colFromMouse(120), 0);
    assertEquals(mouse.rowFromMouse(1), 0);
    assertEquals(mouse.colFromMouse(1), 0);
    assertEquals(mouse.rowFromMouse(132), 1);
    assertEquals(mouse.colFromMouse(132), 1);
    assertEquals(mouse.rowFromMouse(240), 1);
    assertEquals(mouse.colFromMouse(240), 1);
    assertEquals(mouse.rowFromMouse(270), 2);
    assertEquals(mouse.colFromMouse(270), 2);
    assertEquals(mouse.rowFromMouse(350), 2);
    assertEquals(mouse.colFromMouse(350), 2);
    assertEquals(mouse.rowFromMouse(490), 3);
    assertEquals(mouse.rowFromMouse(510), 3);
    assertEquals(mouse.rowFromMouse(675), -20);
    assertEquals(mouse.colFromMouse(-12), -20);
    grid.addRoom(0, 0, "");
    assertNotNull(grid.getGrid()[0][0]);
    // cant test mouse?
    mouse.mouseClicked(new MouseEvent(grid, 0, 0, 0, 0, 20, 20, true));
    mouse.mouseEntered(new MouseEvent(grid, 0, 0, 0, 0, 0, 0, false));
    mouse.mouseExited(new MouseEvent(grid, 0, 0, 0, 0, 0, 0, false));
    mouse.mousePressed(new MouseEvent(grid, 0, 0, 0, 0, 0, 0, false));
    mouse.mouseReleased(new MouseEvent(grid, 0, 0, 0, 0, 0, 0, false));
    assert (grid.getRoomFromId(1).getValid());
  }

  // Test PlacementPanel
  @Test
  void placementTest1() {
    MapFrame m = new MapFrame();
    Grid grid = new Grid(m);
    PlacementPanel p = new PlacementPanel(grid, null);
    m.add(p);
    assertEquals(p.getBackground(), Color.DARK_GRAY);
  }

  // Test EditingPanel
  @Test
  void editingTest1() {
    Grid grid = new Grid();
    SwingTestCase m = new SwingTestCase();
    Frame f = m.getTestFrame();
    EditingPanel e = new EditingPanel(grid, null);
    f.add(e);
    assertEquals(e.getBackground(), Color.DARK_GRAY);
    try {
      m.tearDown();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  void templateTest1() {
    Grid grid = new Grid();
    SwingTestCase m = new SwingTestCase();
    Frame f = m.getTestFrame();
    TemplatePanel e = new TemplatePanel(grid, null);
    f.add(e);
    assertEquals(e.getBackground(), Color.DARK_GRAY);
    try {
      m.tearDown();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // Test ButtonPanel
  @Test
  void buttonTest1() {
    Grid grid = new Grid();
    SwingTestCase m = new SwingTestCase();
    Frame f = m.getTestFrame();
    ButtonPanel p = new ButtonPanel(grid, null);
    f.add(p);
    assertEquals(p.getBackground(), Color.DARK_GRAY);
    try {
      m.tearDown();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
