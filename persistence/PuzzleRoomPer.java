package persistence;

import gameworld.PuzzleRoom;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/** Puzzle Room Class, extends a room object.
 * @author Monika Gill
 *
 */

@XmlRootElement(name = "PuzzleRoom")
@XmlAccessorType(XmlAccessType.FIELD)
public class PuzzleRoomPer extends RoomPer {
  int type;
  String description;
  GameObjectPer prize;
  @XmlElementWrapper(name = "Solution")
  @XmlElement(name = "item")
  GameObjectPer[] solution;


  /**
   *Constructor for Puzzle Room Object.
   */
  public PuzzleRoomPer() {

  }

  /** Getter of type.
   * @return the type
   */
  public int getType() {
    return type;
  }

  /** Setter of type.
   * @param type the type to set
   */
  public void setType(int type) {
    this.type = type;
  }

  /**Getter of Description.
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**Setter of Description.
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**Getter of puzzle solution.
   * @return the solution
   */
  public GameObjectPer[] getSolution() {
    return solution;
  }

  /**setter of soloution.
   * @param solution the solution to set
   */
  public void setSolution(GameObjectPer[] solution) {
    this.solution = solution;
  }

  /**getter of prize.
   * @return the prize
   */
  public GameObjectPer getPrize() {
    return prize;
  }

  /**setter of prize.
   * @param prize the prize to set
   */
  public void setPrize(GameObjectPer prize) {
    this.prize = prize;
  }

  /**Assigns puzzle room.
   * @param object object assigned.
   * @return persistence puzzle room
   */
  public PuzzleRoomPer assign(gameworld.Room object) {
    if (object instanceof gameworld.PuzzleRoom) {
      this.setType(((gameworld.PuzzleRoom) object).getType());
      this.setDescription(((gameworld.PuzzleRoom) object).getDescription());


      this.setPrize(new ChestPer().assign(((gameworld.PuzzleRoom) object).getPrize()));
      ArrayList<GameObjectPer> solution = new ArrayList<>();

      for (gameworld.GameObject item : (((gameworld.PuzzleRoom) object).getSolution())) {

        if (item instanceof gameworld.Chest) {
          ChestPer internalItem = (ChestPer) (new ChestPer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Door) {
          DoorPer internalItem = (DoorPer) (new DoorPer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Key) {
          KeyPer internalItem = (KeyPer) (new KeyPer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Kitten) {
          KittenPer internalItem = (KittenPer) (new KittenPer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Table) {
          TablePer internalItem = (TablePer) (new TablePer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Chair) {
          ChairPer internalItem = (ChairPer) (new ChairPer()).assign(item);
          solution.add(internalItem);
        } else if (item instanceof gameworld.Box) {
          BoxPer internalItem = (BoxPer) (new BoxPer()).assign(item);
          solution.add(internalItem);
        }
      }
      GameObjectPer[] gobj = solution.toArray(new GameObjectPer[solution.size()]);
      this.setSolution(gobj);
    }
    return this;

  }

  /**
   * Reassign Puzzle room.
   */
  public gameworld.PuzzleRoom reassign() {
    PuzzleRoom gameObject = new gameworld.PuzzleRoom(0, 0);
    gameObject.setType(this.getType());
    gameObject.setDiscription(this.getDescription());

    ArrayList<gameworld.GameObject> solutionInventory = new ArrayList<>();
    for (GameObjectPer item : this.getSolution()) {
      solutionInventory.add(item.reassign());
      gameworld.GameObject[] gobj =
          solutionInventory.toArray(new gameworld.GameObject[solutionInventory.size()]);
      gameObject.setSolution(gobj);
    }

    return gameObject;
  }
}
