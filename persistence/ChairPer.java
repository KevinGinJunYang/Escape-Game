package persistence;

import gameworld.GameObject;
import javax.xml.bind.annotation.XmlRootElement;

/** A Chair persistence object.
 * @author monikaraj
 *
 */

@XmlRootElement(name = "Chair")
public class ChairPer extends GameObjectPer {

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
    gameworld.Chair gameObject = new gameworld.Chair();
    gameObject.setContainer(this.isContainer);
    gameObject.setLocked(this.isLocked);
    gameObject.setPushable(this.isPushable);
    gameObject.setPickupable(this.isPickupable);
   
    return gameObject;
  }

}
