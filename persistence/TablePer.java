package persistence;

import gameworld.GameObject;
import javax.xml.bind.annotation.XmlRootElement;

/**Table Persistence version.
 * @author monika gill
 *
 */
@XmlRootElement(name = "Table")
public class TablePer extends GameObjectPer {

  @Override
  public boolean activate() {

    return false;
  }

  @Override
  public GameObjectPer assign(GameObject object) {
    this.setContainer(object.isContainer());
    this.setLocked(object.isLocked());
    this.setPushable(object.isPushable());
    this.setPickupable(object.isPickupable());
    return this;
  }

  @Override
  public GameObject reassign() {
    gameworld.Table gameObject = new gameworld.Table();
    gameObject.setContainer(this.isContainer);
    gameObject.setLocked(this.isLocked);
    gameObject.setPushable(this.isPushable);
    gameObject.setPickupable(this.isPickupable);

    return gameObject;
  }

}
