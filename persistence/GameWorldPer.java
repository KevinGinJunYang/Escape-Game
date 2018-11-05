package persistence;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Game World class.
 *
 * @author monika gill
 *
 */
@XmlRootElement(name = "Game")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameWorldPer {

  HashMap<Integer, RoomPer> mapLayout;
  // HashMap<Integer, String> RoomReferences;

  PlayerPer player;


  /**
   * Constructs the Game world using Random Assignments for the Rooms and items.
   */
  public GameWorldPer() {
    super();
  }

  /**Getter of map layout.
   * @return map layout for game world.
   */
  public HashMap<Integer, RoomPer> getMapLayout() {
    return mapLayout;
  }

  /**Setter of map Layout.
   * @param mapLayout for game world.
   */
  public void setMapLayout(HashMap<Integer, RoomPer> mapLayout) {
    this.mapLayout = mapLayout;
  }

  /**Getter of player.
   * @return player object
   */
  public PlayerPer getPlayer() {
    return player;
  }

  /**Setter of player.
   * @param player player to be set.
   */
  public void setPlayer(PlayerPer player) {
    this.player = player;
  }



  /**Assign game world.
   * @param gameworld to be assigned.
   * @return gameworld persistence
   */
  public persistence.GameWorldPer assign(gameworld.GameWorld gameworld) {

    this.setPlayer(new PlayerPer().assign(gameworld.getPlayer()));

    HashMap<Integer, RoomPer> localLayout = new HashMap<>();

    for (Integer key : gameworld.getMapLayout().keySet()) {
      localLayout.put(key, (new RoomPer()).assign(gameworld.getMapLayout().get(key)));
    }
    this.setMapLayout(localLayout);

    return this;
  }


  /**Reassign gameworld.
   * @return gameworld to be reassigned.
   */
  public gameworld.GameWorld reassign() {
    gameworld.GameWorld gameWorld = new gameworld.GameWorld();
    gameworld.Player player = this.getPlayer().reassign();

    HashMap<Integer, gameworld.Room> mapLayout = new HashMap<>();
    for (Integer key : this.getMapLayout().keySet()) {
      mapLayout.put(key, this.getMapLayout().get(key).reassign());
    }

    gameWorld.setMapLayout(mapLayout);
    gameWorld.setPlayer(player);
    return gameWorld;
  }

}
