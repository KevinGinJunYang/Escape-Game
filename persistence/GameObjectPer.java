package persistence;

import java.awt.image.BufferedImage;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * the Abstract Class of Game Objects that all intractable Objects extends.
 *
 *
 */
@XmlTransient
@XmlSeeAlso({persistence.ChestPer.class, persistence.KeyPer.class, persistence.DoorPer.class,
    persistence.GameWorldPer.class, persistence.ChairPer.class, persistence.BoxPer.class,
    persistence.KittenPer.class, persistence.TablePer.class, persistence.SwitchPer.class})

public abstract class GameObjectPer {
  protected boolean isContainer = false;
  boolean isLocked = false;
  boolean isPushable = true;
  boolean isPickupable = true;
  BufferedImage itemImage;

  /**
   * returns true if the Object can Be Activated.
   *
   * @return a boolean flag if the Object was Activated successfully.
   */
  public abstract boolean activate();

  /**
   * Get the PNG image of the GameObject.
   *
   * @return returns the Buffered Image Associated with the GameObject.
   */
  public BufferedImage getImage() {
    return itemImage;
  }

  /**
   * Check if is container.
   * @return boolean.
   */
  public boolean isContainer() {
    return isContainer;
  }

  /**
   * Setter for container.
   * @param isContainer if container.
   */
  public void setContainer(boolean isContainer) {
    this.isContainer = isContainer;
  }

  /**
   * Check if item is pickupable.
   * @return the isPickupable
   */
  public boolean isPickupable() {
    return isPickupable;
  }

  /**
   * Setter for pickupable.
   * @param isPickupable the isPickupable to set
   */
  public void setPickupable(boolean isPickupable) {
    this.isPickupable = isPickupable;
  }



  /**
   * Check if is locked.
   * @return boolean if locked.
   */
  public boolean isLocked() {
    return isLocked;
  }

  /**
   * Setter for locked.
   * @param isLocked if is locked.
   */
  public void setLocked(boolean isLocked) {
    this.isLocked = isLocked;
  }

  /**
   * Check if pushable.
   * @return boolean.
   */
  public boolean isPushable() {
    return isPushable;
  }

  /**
   * Setter for pushable.
   * @param isPushable is pushable.
   */
  public void setPushable(boolean isPushable) {
    this.isPushable = isPushable;
  }

  /**
   * Getter for Image.
   * @return item.
   */
  public BufferedImage getItemImage() {
    return itemImage;
  }

  /**
   * Setter for image.
   * @param itemImage item image.
   */
  public void setItemImage(BufferedImage itemImage) {
    this.itemImage = itemImage;
  }

  /**
   * Assigner for object.
   * @param object object.
   * @return  object.
   */
  public abstract GameObjectPer assign(gameworld.GameObject object);

  /**
   * Assigner for gameObjects.
   * @param game game object.
   */
  public void assign(GameObjectPer game) {
    this.isContainer = game.isContainer;
    this.isLocked = game.isLocked;
    this.isPushable = game.isPushable;
    this.isPickupable = game.isPickupable;
  }

  /**
   * Reassigner.
   * @return gameobject.
   */
  public abstract gameworld.GameObject reassign();
}
