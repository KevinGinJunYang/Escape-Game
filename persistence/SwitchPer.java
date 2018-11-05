package persistence;

import gameworld.GameObject;
import gameworld.Switch;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * Switch for XML.
 * @author monika gill
 *
 */
@XmlRootElement(name = "Switch")
public class SwitchPer extends GameObjectPer {
  GameObjectPer itemToUnlock = null;
  PuzzleRoomPer puzzleRoom = null;

  /**
   * Creates a new switch that unlocks a Specific Door.
   *
   */
  public SwitchPer() {

  }

  /**
   * Item to unlock.
   * @return the itemToUnlock
   */
  public GameObjectPer getItemToUnlock() {
    return itemToUnlock;
  }

  /**
   * Seting item to unlock.
   * @param itemToUnlock the itemToUnlock to set
   */
  public void setItemToUnlock(GameObjectPer itemToUnlock) {
    this.itemToUnlock = itemToUnlock;
  }

  /**
   * Puzzle room.
   * @return the r
   */
  public PuzzleRoomPer getPuzzleRoom() {
    return puzzleRoom;
  }

  /**
   * Setting the puzzle room.
   * @param r the r to set
   */
  public void setPuzzleRoom(PuzzleRoomPer r) {
    this.puzzleRoom = r;
  }



  @Override
  public GameObjectPer assign(GameObject object) {

    GameObject itemToUnlockGw = null;
    if (object instanceof gameworld.Switch) {
      itemToUnlockGw = ((Switch)object).getItemToUnlock();
      this.setPuzzleRoom((new PuzzleRoomPer()).assign(((Switch)object).getRoom()));
    }

    if (itemToUnlockGw instanceof gameworld.Door) {

      itemToUnlock.assign(itemToUnlockGw);
    }
    if (object instanceof gameworld.Chest) {
      itemToUnlock.assign(itemToUnlockGw);
    }

    this.setContainer(object.isContainer());
    this.setLocked(object.isLocked());
    this.setPushable(object.isPushable());
    this.setPickupable(object.isPickupable());

    return this;
  }

  @Override
  public GameObject reassign() {

    GameObject objectReturned = null;

    if (this.puzzleRoom != null) {

      objectReturned = new Switch((gameworld.PuzzleRoom)this.puzzleRoom.reassign());

    } else if (this.itemToUnlock instanceof DoorPer) {
      objectReturned = new Switch((gameworld.Door)this.itemToUnlock.reassign());
    } else if (this.itemToUnlock instanceof ChestPer) {
      objectReturned = new Switch((gameworld.Chest)this.itemToUnlock.reassign());
    } else {
      return null;
    }

    objectReturned.setContainer(this.isContainer);
    objectReturned.setLocked(this.isLocked);
    objectReturned.setPushable(this.isPushable);
    objectReturned.setPickupable(this.isPickupable);

    return objectReturned;
  }

  @Override
  public boolean activate() {

    return false;
  }


}
