package persistence;

import gameworld.GameObject;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * Kitten Per.
 *
 * @author monikaraj
 *
 */
@XmlRootElement(name = "Kitten")
public class KittenPer extends GameObjectPer {

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
    gameworld.Kitten gameObject = new gameworld.Kitten();
    gameObject.setContainer(this.isContainer);
    gameObject.setLocked(this.isLocked);
    gameObject.setPushable(this.isPushable);
    gameObject.setPickupable(this.isPickupable);

    return gameObject;
  }

}
