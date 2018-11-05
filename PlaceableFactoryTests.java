import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gameworld.Box;
import gameworld.Chair;
import gameworld.Chest;
import gameworld.Door;
import gameworld.Key;
import gameworld.Kitten;
import gameworld.Switch;
import gameworld.Table;
import org.junit.Test;
import renderer.PlaceableFactory;
import renderer.drawableelements.PlaceableElement;
import renderer.drawableelements.placeableelements.BoxElement;
import renderer.drawableelements.placeableelements.ChairElement;
import renderer.drawableelements.placeableelements.ChestElement;
import renderer.drawableelements.placeableelements.DoorElement;
import renderer.drawableelements.placeableelements.KeyElement;
import renderer.drawableelements.placeableelements.KittenElement;
import renderer.drawableelements.placeableelements.SwitchElement;
import renderer.drawableelements.placeableelements.TableElement;


/**
 * Tests for items.
 *
 * @author yangkevi
 *
 */
public class PlaceableFactoryTests {

  /**
   * Test an valid contructrion of a Placeable using PlaceableFactory.makePlaceable();
   */
  @Test
  public void testValidMakePlaceable() {
    PlaceableFactory factory = new PlaceableFactory(2, 2, 0, 50, 800, 500);

    PlaceableElement key = factory.makePlaceable(new Key("red"), 4, 4, 60);
    assertTrue(key instanceof KeyElement);

    PlaceableElement chest = factory.makePlaceable(new Chest("red"), 4, 4, 60);
    assertTrue(chest instanceof ChestElement);

    PlaceableElement door = factory.makePlaceable(new Door(123, "red"), 4, 4, 60);
    assertTrue(door instanceof DoorElement);

    PlaceableElement switchEle = factory.makePlaceable(new Switch(new Door(123, "red")), 4, 4, 60);
    assertTrue(switchEle instanceof SwitchElement);

    PlaceableElement table = factory.makePlaceable(new Table(), 4, 4, 60);
    assertTrue(table instanceof TableElement);

    PlaceableElement chair = factory.makePlaceable(new Chair(), 4, 4, 60);
    assertTrue(chair instanceof ChairElement);

    PlaceableElement box = factory.makePlaceable(new Box(), 4, 4, 60);
    assertTrue(box instanceof BoxElement);

    PlaceableElement kitten = factory.makePlaceable(new Kitten(), 4, 4, 60);
    assertTrue(kitten instanceof KittenElement);

  }

  /**
   * Test an invalid contructrion of a Placeable using PlaceableFactory.makePlaceable();
   */
  @Test
  public void testInvalidMakePlaceable() {
    PlaceableFactory factory = new PlaceableFactory(2, 2, 0, 50, 800, 500);
    try {
      factory.makePlaceable(null, 4, 4, 60);
      fail();

    } catch (IllegalArgumentException e) {
      assert true;
    }

  }

}
