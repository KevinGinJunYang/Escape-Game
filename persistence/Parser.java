package persistence;

import gameworld.GameWorld;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * Parser class.
 * @Monika Gill
 *
 */

public class Parser {

  /**
   * Saves the game to XML.
   * @param gameworld gameworld.
   * @param xmlOutput xml output file
   * @throws JAXBException exception

   */
  public static void save(GameWorld gameworld, File xmlOutput) throws JAXBException {

    persistence.GameWorldPer persistenceGameWorld = new persistence.GameWorldPer();
    persistenceGameWorld.assign(gameworld);

    JAXBContext context = JAXBContext.newInstance(persistence.GameWorldPer.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    m.marshal(persistenceGameWorld, System.out); // just for testing purpose
    m.marshal(persistenceGameWorld, xmlOutput);
  }

  /**
   * Loads the game from XML.
   * @param xmlInput xml input
   * @return GameWorld
   * @throws JAXBException exception.

   */
  public static GameWorld load(File xmlInput) throws JAXBException {
    GameWorld gameWorld = new GameWorld();

    JAXBContext context = JAXBContext.newInstance(persistence.GameWorldPer.class);
    Unmarshaller um = context.createUnmarshaller();

    persistence.GameWorldPer persistenceGw = (persistence.GameWorldPer) um.unmarshal(xmlInput);

    gameWorld = persistenceGw.reassign();


    return gameWorld;

  }
}
