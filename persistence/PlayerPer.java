package persistence;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML.
 * @author monika gill
 *
 */
@XmlRootElement(name = "Player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerPer {

  @XmlAttribute(name = "id")
  int currentRoomId;
  /**
   * facer.
   */
  @XmlAttribute(name = "facing")
  public Direction facing;


  /**
   * player position.
   */
  @XmlElement(name = "playerPositon")
  public PairPer<Integer, Integer> roomLocation;
  /**
   * inventory.
   */
  @XmlElementWrapper(name = "Inventory")
  @XmlElement(name = "item")
  public GameObjectPer[] playerInventory;



  /**
   * This creates a new Player and the Specified Room.
   *
   */
  public PlayerPer() {

  }



  /**
   * Returns the Direction the Player is currently Facing.
   *
   * @return The Direction the Player is Facing.
   */
  public Direction getFacing() {
    return facing;
  }

  /**
   * sets the Direction the Player is Facing.
   *
   * @param dir The new Directional Facing.
   */
  public void setFacing(Direction dir) {
    facing = dir;
  }

  /**
   * Returns where in the Current Room the Player is Located.
   *
   * @return the X,Y Coordinates in a Pair.
   */
  public PairPer<Integer, Integer> getLocationInRoom() {
    return roomLocation;
  }

  /**
   * sets/updates where in the Current Room the Player is Located.
   *
   * @param loc room location.
   */

  public void setRoomLocation(PairPer<Integer, Integer> loc) {
    roomLocation = loc;
  }

  /**
   * Sets the CurrentRoomID to the Provided ID.
   *
   * @param roomId The RoomID that the Player is assigned.
   */

  public void setCurrentRoom(int roomId) {
    currentRoomId = roomId;
  }

  /**
   * Returns the CurrentRoomID.
   *
   * @return the RoomId that the Player is in.
   */
  public int getCurrentRoomId() {
    return currentRoomId;
  }

  /**
   * Getter for player inventory.
   * @return the playerInventory
   */
  public GameObjectPer[] getPlayerInventory() {
    return playerInventory;
  }



  /**
   * Setter for player inventory.
   * @param playerInventory the playerInventory to set
   */
  public void setPlayerInventory(GameObjectPer[] playerInventory) {
    this.playerInventory = playerInventory;
  }


  /**
   * Player assigner.
   * @param player player of game.
   * @return direction.
   */
  public persistence.PlayerPer assign(gameworld.Player player) {
    this.setCurrentRoom(player.getCurrentRoomId());
    persistence.Direction direction = null;

    switch (player.getFacing().toString()) {
      case "NORTH":
        direction = persistence.Direction.NORTH;
        break;
      case "SOUTH":
        direction = persistence.Direction.SOUTH;
        break;
      case "WEST":
        direction = persistence.Direction.WEST;
        break;
      case "EAST":
        direction = persistence.Direction.EAST;
        break;
      default:
        break;
    }
    this.setFacing(direction);

    PairPer<Integer, Integer> persPlayer = new PairPer<Integer, Integer>();
    persPlayer.setKey(player.getLocationInRoom().getKey());
    persPlayer.setValue(player.getLocationInRoom().getValue());
    this.setRoomLocation(persPlayer);
    ArrayList<GameObjectPer> playerInventory = new ArrayList<>();

    for (gameworld.GameObject item : player.getPlayerInventory()) {

      if (item instanceof gameworld.Chest) {
        ChestPer internalItem = (ChestPer) (new ChestPer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Door) {
        DoorPer internalItem = (DoorPer) (new DoorPer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Key) {
        KeyPer internalItem = (KeyPer) (new KeyPer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Kitten) {
        KittenPer internalItem = (KittenPer) (new KittenPer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Table) {
        TablePer internalItem = (TablePer) (new TablePer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Chair) {
        ChairPer internalItem = (ChairPer) (new ChairPer()).assign(item);
        playerInventory.add(internalItem);
      } else if (item instanceof gameworld.Box) {
        BoxPer internalItem = (BoxPer) (new BoxPer()).assign(item);
        playerInventory.add(internalItem);
      } else if ((item instanceof gameworld.Switch)) {
        SwitchPer internalItem = (SwitchPer) (new SwitchPer()).assign(item);
        playerInventory.add(internalItem);
      }

    }
    GameObjectPer[] gobj = playerInventory.toArray(new GameObjectPer[playerInventory.size()]);
    this.setPlayerInventory(gobj);
    return this;
  }

  /**
   * Player reassigner.
   * @return player direction.
   */
  public gameworld.Player reassign() {
    gameworld.Player player = new gameworld.Player(this.currentRoomId);

    gameworld.Direction direction = null;

    switch (this.facing.toString()) {
      case "NORTH":
        direction = gameworld.Direction.NORTH;
        break;
      case "SOUTH":
        direction = gameworld.Direction.SOUTH;
        break;
      case "WEST":
        direction = gameworld.Direction.WEST;
        break;
      case "EAST":
        direction = gameworld.Direction.EAST;
        break;
      default:
        break;
    }

    player.setFacing(direction);
    gameworld.Pair<Integer, Integer> persPlayer = new gameworld.Pair<Integer, Integer>(
        this.getLocationInRoom().getKey(), this.getLocationInRoom().getValue());// test
    player.setRoomLocation(persPlayer);
    ArrayList<gameworld.GameObject> playerInventory = new ArrayList<>();

    for (GameObjectPer item : this.getPlayerInventory()) {
      playerInventory.add(item.reassign());
      gameworld.GameObject[] gobj =
          playerInventory.toArray(new gameworld.GameObject[playerInventory.size()]);
      player.setPlayerInventory(gobj);
    }
    return player;

  }
}
