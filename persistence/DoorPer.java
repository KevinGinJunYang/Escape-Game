package persistence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * author@ Monika Gill A Door that leads to another Room.
 * 
 * 
 *
 */
@XmlRootElement(name = "Door")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoorPer extends GameObjectPer {
  @XmlAttribute(name = "id")
  int doorId;
  @XmlAttribute(name = "color")
  String doorColor;
  @XmlAttribute(name = "roomid")
  int roomid;


  // adding objecttype item attribute
  GameObjectPer item;

  /**
   * The Constructor of a Door with a reference to the room it Leads to and the Color of the Door,
   * if no color is Specified the Door will be a Plain Door.
   */

  public DoorPer() {

  }

  /**
   * Set the Color of the Door and assign the Relative PNG File to the ITemImage.
   * @param col the col of door
   */

  public void setColor(String col) {
    this.doorColor = col;
  }

  /** return color.
   * @return string
   */
  public String getColor() {
    return doorColor;
  }



  /**
   * Get the color of the Door.
   * 
   * @return The color of the Door as a String.
   */
  public GameObjectPer getItem() {
    return item;
  }

  /** sets item.
   * @param item sets the item
   */
  public void setItem(GameObjectPer item) {
    this.item = item;
  }

  /**returns the id.
   * @return int
   */
  public int getId() {
    return doorId;
  }

  /**sets the id.
   * @param id sets the id
   */
  public void setId(int id) {
    this.doorId = id;
  }

  @Override
  public boolean activate() {
    if (isLocked) {
      return false;
    }
    return true;
  }

  /**gets the room id.
   * @return int
   */
  public int getRoomid() {
    return roomid;
  }

  /**Set the RoomId to which this Door Connects to.
    * @param roomid The RoomId to which this door connects to.
   */
  public void setRoomid(int roomid) {
    this.roomid = roomid;
  }


  @Override
  public GameObjectPer assign(gameworld.GameObject object) {

    this.setContainer(object.isContainer());
    this.setLocked(object.isLocked());
    this.setPushable(object.isPushable());
    this.setPickupable(object.isPickupable());

    if (object instanceof gameworld.Door) {
      this.setId(((gameworld.Door) object).getRoomReference());
      this.setColor(((gameworld.Door) object).getDoorColor());
      persistence.DoorPer door = new persistence.DoorPer();
      this.setItem(door);
    }
    return this;
  }



  @Override
  public gameworld.GameObject reassign() {

    gameworld.Door gameObject = new gameworld.Door(this.getRoomid(), this.getColor());
    gameObject.setContainer(this.isContainer);
    gameObject.setLocked(this.isLocked);
    gameObject.setPushable(this.isPushable);
    gameObject.setPickupable(this.isPickupable);


    return gameObject;
  }

}
