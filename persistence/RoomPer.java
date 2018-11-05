package persistence;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * A room in the gameworld.
 *
 * @author monika gill
 */
@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Direction.class, PuzzleRoomPer.class})
public class RoomPer {

  /**
   * Room per.
   */
  public RoomPer() {
    super();
  }

  String roomName;
  @XmlElementWrapper(name = "rows")
  @XmlElement(name = "row")
  private ArrayList<RoomLocationColumns> roomLayout;

  @XmlAttribute(name = "room_id")
  private Integer roomId;

  @XmlElementWrapper(name = "exits")
  @XmlElement(name = "exit")
  private ArrayList<PairPer<Direction, Integer>> exitsList;

  /**
   * Getter for room layout.
   * @return the room layout
   */
  public ArrayList<RoomLocationColumns> getRoomLayout() {
    return roomLayout;
  }

  /**
   * Setter for room layout.
   * @param roomLayout room layout.
   */
  public void setRoomLayout(ArrayList<RoomLocationColumns> roomLayout) {
    this.roomLayout = roomLayout;
  }

  /**
   * List of exists.
   * @return lists of exits
   */
  public ArrayList<PairPer<Direction, Integer>> getExitsList() {
    return this.exitsList;
  }

  /**
   * Set exit list.
   * @param exits exits.
   */
  public void setExitsList(ArrayList<PairPer<Direction, Integer>> exits) {
    this.exitsList = exits;
  }

  /**
   * Getter for room id.
   * @return room id
   */
  public Integer getRoomId() {
    return this.roomId;
  }


  /**
   * Setter for room id.
   * @param roomId room id
   */
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  /**
   * Getter for room name.
   * @return the roomName
   */
  public String getRoomName() {
    return roomName;
  }

  /**
   * Setter for room name.
   * @param roomName the roomName to set
   */
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  /**
   * Assgigner for room.
   * @param room to assign.
   * @return Persistence room
   */
  public persistence.RoomPer assign(gameworld.Room room) {
    // Set Id
    this.setRoomId(room.getRoomId());
    // set room name
    this.setRoomName(room.getRoomName());
    // set exits
    ArrayList<PairPer<Direction, Integer>> exitLists = new ArrayList<>();

    for (gameworld.Pair<gameworld.Direction, Integer> pair : room.getExits()) {
      persistence.PairPer<persistence.Direction, Integer> localPair = null;

      switch (pair.getKey().toString()) {
        case "NORTH":
          localPair = new PairPer<>(Direction.NORTH, pair.getValue());
          break;
        case "SOUTH":
          localPair = new PairPer<>(Direction.SOUTH, pair.getValue());
          break;
        case "WEST":
          localPair = new PairPer<>(Direction.WEST, pair.getValue());
          break;
        case "EAST":
          localPair = new PairPer<>(Direction.WEST, pair.getValue());
          break;
        default:
          break;
      }

      exitLists.add(localPair);
    }

    this.setExitsList(exitLists);

    // set roomLocations
    ArrayList<LocationPer> columns = new ArrayList<>();
    RoomLocationColumns columnsLocation = new RoomLocationColumns();

    ArrayList<RoomLocationColumns> rows = new ArrayList<>();

    LocationPer auxLocation = null;
    for (gameworld.Location[] location : room.getRoomLayout()) {
      columns = new ArrayList<>();
      for (gameworld.Location locationColumn : location) {
        auxLocation = new LocationPer();
        auxLocation.assign(locationColumn);
        columns.add(auxLocation);
      }
      columnsLocation = new RoomLocationColumns();
      columnsLocation.setColumns(columns);
      rows.add(columnsLocation);
    }
    this.setRoomLayout(rows);
    return this;// add a return type and change assign void type

  }

  /**
   * Reassign rooms.
   * @return gameworld Room
   */
  public gameworld.Room reassign() {
    gameworld.Room returnRoom = new gameworld.Room();


    // set Exits
    ArrayList<gameworld.Pair<gameworld.Direction, Integer>> exits = new ArrayList<>();

    for (PairPer<Direction, Integer> pair : this.getExitsList()) {
      gameworld.Pair<gameworld.Direction, Integer> localPair = null;

      switch (pair.getKey().toString()) {
        case "NORTH":
          localPair = new gameworld.Pair<>(gameworld.Direction.NORTH, pair.getValue());
          break;
        case "SOUTH":
          localPair = new gameworld.Pair<>(gameworld.Direction.SOUTH, pair.getValue());
          break;
        case "WEST":
          localPair = new gameworld.Pair<>(gameworld.Direction.WEST, pair.getValue());
          break;
        case "EAST":
          localPair = new gameworld.Pair<>(gameworld.Direction.WEST, pair.getValue());
          break;
        default:
          break;
      }

      exits.add(localPair);
    }

    returnRoom.setExits(exits);

    // set Id
    returnRoom.setRoomId(this.getRoomId());
    // set room name
    returnRoom.setRoomName(this.getRoomName());
    // Set Room Layout
    int rows = 0;
    int columns = 0;

    rows = this.getRoomLayout().size();
    if (rows > 0) {
      columns = this.getRoomLayout().get(0).getColumns().size();
    }

    gameworld.Location[][] returnLocation = new gameworld.Location[rows][columns];

    for (int auxRow = 0; auxRow < rows; auxRow++) {
      for (int auxColumn = 0; auxColumn < columns; auxColumn++) {
        returnLocation[auxRow][auxColumn] =
            this.getRoomLayout().get(auxRow).getColumns().get(auxColumn).reassign();
      }
    }

    returnRoom.setRoomLayout(returnLocation);

    return returnRoom;

  }
}
