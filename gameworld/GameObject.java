package gameworld;

import java.awt.image.BufferedImage;

/**
 * the Abstract Class of Game Objects that all intractable Objects extends.
 *
 * @author Remi
 *
 */
public abstract class GameObject {
  boolean isContainer = false;
  boolean isLocked = false;
  boolean isPushable = true;
  /**
   * Pickupable.
   */
  public boolean isPickupable = true;

  /**
   * Add item image.
   */
  public BufferedImage itemImage;

  /**
   * returns true if the Object can Be Activated.
   *
   * @param player The PlayerThat is activating this Object.
   * @return a boolean flag if the Object was Activated successfully.
   */
  public abstract boolean activate(Player player);

  /**
   * The String Description of this GameObject.
   */
  public abstract String toString();

  /**
   * Sets or Resets the Buffered Image of this gameObject.
   */
  public abstract void resetImage();

  /**
   * The Boolean flag for if this GameObject is a Container.
   *
   * @return the isContainer.
   */
  public boolean isContainer() {
    return isContainer;
  }

  /**
   * Sets the Boolean flag for if this GameObject is a Container.
   *
   * @param isContainer the isContainer to set.
   */
  public void setContainer(boolean isContainer) {
    this.isContainer = isContainer;
  }

  /**
   * The Boolean flag for if this GameObject is Locked.
   *
   * @return the isLocked.
   */
  public boolean isLocked() {
    return isLocked;
  }

  /**
   * Sets the Boolean flag for if this GameObject is Locked.
   *
   * @param isLocked the isLocked to set.
   */
  public void setLocked(boolean isLocked) {
    this.isLocked = isLocked;
  }

  /**
   * The Boolean flag for if this GameObject is able to be Pushed.
   *
   * @return the isPushable.
   */
  public boolean isPushable() {
    return isPushable;
  }

  /**
   * Sets the Boolean flag for if this GameObject is able to be Pushed.
   *
   * @param isPushable the isPushable to set.
   */
  public void setPushable(boolean isPushable) {
    this.isPushable = isPushable;
  }

  /**
   * The Boolean flag for if this GameObject is able to be Picked up.
   *
   * @return the isPickupable.
   */
  public boolean isPickupable() {
    return isPickupable;
  }

  /**
   * Sets the Boolean flag for if this GameObject is able to be Picked up.
   *
   * @param isPickupable the isPickupable to set.
   */
  public void setPickupable(boolean isPickupable) {
    this.isPickupable = isPickupable;
  }

  /**
   * Get the PNG image of the GameObject.
   *
   * @return returns the Buffered Image Associated with the GameObject.
   */
  public BufferedImage getItemImage() {
    return itemImage;
  }

  /**
   * Set the Image associated with this GameObject.
   * @param itemImage The itemImage to set.
   */
  public void setItemImage(BufferedImage itemImage) {
    this.itemImage = itemImage;
  }
}
