package persistence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Chest persistence class author@ Monika Gill A Chest that can be locked and contain a GameObject.
 */


@XmlRootElement(name = "Chest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChestPer extends GameObjectPer {
  GameObjectPer item;

  @XmlAttribute(name = "color")
  String color;

  /**
   * The Constructor for an Unlocked Chest.
   */

  public ChestPer() {

  }

  /**
   * a persistence item.
   * 
   * @return GameObjectPer
   */
  public GameObjectPer getItem() {
    return item;
  }

  /**
   * Set the Item in the Chest.
   * 
   * @param item the item to set
   */
  public void setItem(GameObjectPer item) {
    this.item = item;
  }


  /**
   * gets the color.
   * 
   * @return the color string
   */
  public String getColor() {
    return color;
  }

  /**
   * Set the Color of the Chest.
   * 
   * @param color the chestColor to set
   */
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean activate() {
    return false;
  }



  @Override
  public GameObjectPer assign(gameworld.GameObject object) {
    if (object instanceof gameworld.Chest) {
      this.setColor(((gameworld.Chest) object).getChestColor());
    }
    this.setContainer(object.isContainer());
    this.setLocked(object.isLocked());
    this.setPushable(object.isPushable());
    this.setPickupable(object.isPickupable());
    gameworld.Chest gameChest = (gameworld.Chest) object;
    if (gameChest.getItem() instanceof gameworld.Chest) {

      // items inside the chest
      ChestPer internalItem = (ChestPer) (new ChestPer()).assign(gameChest.getItem());
      this.setItem(internalItem);
    } else if ((gameChest.getItem() instanceof gameworld.Door)) {
      DoorPer internalItem = (DoorPer) (new DoorPer()).assign(gameChest.getItem());
      this.setItem(internalItem);
    } else if ((gameChest.getItem() instanceof gameworld.Key)) {
      KeyPer internalItem = (KeyPer) (new KeyPer()).assign(gameChest.getItem());
      this.setItem(internalItem);
    }


    return this;
  }

  @Override
  public gameworld.GameObject reassign() {


    gameworld.Chest gameObject = new gameworld.Chest("");

    gameObject.setContainer(isContainer);
    gameObject.setLocked(isLocked);
    gameObject.setPushable(isPushable);
    gameObject.setChestColor(this.getColor());
    gameObject.setPickupable(this.isPickupable);


    if (this.getItem() instanceof persistence.ChestPer) {
      gameworld.Chest internalItem = (gameworld.Chest) this.getItem().reassign();
      gameObject.addItemToChest(internalItem);
    } else if ((this.getItem() instanceof persistence.DoorPer)) {
      gameworld.Door internalItem = (gameworld.Door) this.getItem().reassign();
      gameObject.addItemToChest(internalItem);
    } else if ((this.getItem() instanceof persistence.KeyPer)) {
      gameworld.Key internalItem = (gameworld.Key) this.getItem().reassign();
      gameObject.addItemToChest(internalItem);
    }

    return gameObject;
  }



}
