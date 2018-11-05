package persistence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Location class.
 * @author monika gill
 *
 */
@XmlRootElement(name = "Location")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationPer {
  GameObjectPer item;
  boolean player;



  /**Setter of player.
   * @param player player to be set.
   */
  public void setPlayer(boolean player) {
    this.player = player;
  }


  /**
   * A Location is constructed assuming nothing is occupying it.
   */
  public LocationPer() {

  }


  /**
   * returns true if the player is in this Location.
   *
   * @return a boolean flag if the Player is here.
   */
  public boolean getPLayer() {
    return player;
  }


  /**
   * Sets the GameObject in this Location.
   *
   * @param o the GameObject to be placed in this Location.
   */

  public void setItem(GameObjectPer o) {
    this.item = o;
  }

  /**
   * Returns the Game Object in this Location without removing it from the Location.
   *
   * @return The GameObject in this Location, null otherwise.
   */
  public GameObjectPer getItem() {
    return item;
  }

  /**Assigns location.
   * @param location location to be assigned.
   * @return persistence location.
   */
  public persistence.LocationPer assign(gameworld.Location location) {
    this.setPlayer(location.getPlayer());
    persistence.GameObjectPer object = null;

    if (location.getItem() != null) {
      switch (location.getItem().getClass().getSimpleName()) {
        case "Key":
          object = new persistence.KeyPer().assign(location.getItem());
          break;
        case "Door":
          object = new persistence.DoorPer().assign(location.getItem());
          break;
        case "Chest":
          object = new persistence.ChestPer().assign(location.getItem());
          break;
        default:
          break;
      }
    }
    this.setItem(object);

    return this;
  }

  /**Reassigns location.
   * @return Game world location.
   *
   */
  public gameworld.Location reassign() {
    gameworld.Location returnLocation = new gameworld.Location();
    returnLocation.setPlayer(this.player);
    if (this.getItem() == null) {
      returnLocation.setItem(null);
    } else {
      returnLocation.setItem(this.getItem().reassign());
    }
    return returnLocation;
  }
}
