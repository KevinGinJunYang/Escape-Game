package persistence;

import java.awt.image.BufferedImage;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Key with a Reference to the Object it Unlocks (Currently Just Doors).
 *
 *
 *
 */
@XmlRootElement(name = "Key")
@XmlAccessorType(XmlAccessType.FIELD)
public class KeyPer extends GameObjectPer {
  @XmlAttribute(name = "id")
  int id;
  @XmlAttribute(name = "color")
  String color;

  GameObjectPer item;

  /**
   * A Key with a id and Color.
   */
  public KeyPer() {

  }

  /**
   * Set the Color of the Key and assign the Relative PNG File to the ITemImage.
   * @param col color to set.
   */

  public void setColor(String col) {
    this.color = col;
  }

  /**
   * Get the Color of the Key in String Format.
   *
   * @return the String Describing the Color
   */
  public String getColor() {
    return color;

  }

  /**
   * Setter for ID.
   * @param id the id to set.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get the ID of the Door or Chest that this key unlocks.
   *
   * @return A Integer Value.
   */
  public int getId() {
    return id;
  }

  @Override
  public void setItemImage(BufferedImage img) {
    this.itemImage = img;
  }

  @Override
  public BufferedImage getItemImage() {
    return this.itemImage;
  }

  /**
   * Returns item.
   * @return item.
   */
  public GameObjectPer getItem() {
    return item;
  }

  /**
   * Setter for item.
   * @param item item to set.
   */
  public void setItem(GameObjectPer item) {
    this.item = item;
  }

  @Override
  public boolean activate() {
    return false;
  }



  @Override
  public GameObjectPer assign(gameworld.GameObject object) {

    if (object instanceof gameworld.Key) {

      this.setColor(((gameworld.Key) object).getKeyColor());
      persistence.KeyPer key = new persistence.KeyPer();
      this.setItem(key);
    }
    return this;
  }


  @Override
  public gameworld.GameObject reassign() {

    gameworld.Key gameObject = new gameworld.Key(this.getColor());

    return gameObject;

  }
}
