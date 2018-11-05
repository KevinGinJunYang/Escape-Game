package persistence;

import gameworld.GameObject;
import javax.xml.bind.annotation.XmlRootElement;

/** A box persistence object.
 * @author monika gill
 *
 */
@XmlRootElement(name = "Box")
public class BoxPer extends GameObjectPer {

  
  /**
   * persistence constructor for Box.
   */
  public BoxPer() {
    
  }
  
  @Override
  public boolean activate() {
    return false;
  }

  @Override
  public GameObjectPer assign(GameObject object) {
    this.setContainer(object.isContainer());
    this.setLocked(object.isLocked());
    this.setPushable(object.isPushable());

    return this;
  }

  @Override
  public gameworld.GameObject reassign() {
    gameworld.Box gameObject = new gameworld.Box();
    gameObject.setContainer(this.isContainer);
    gameObject.setLocked(this.isLocked);
    gameObject.setPushable(this.isPushable);
   
    return gameObject;
  }
 

}
