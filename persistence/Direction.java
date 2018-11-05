package persistence;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * enum class. author@ Monika Gill
 */
@XmlRootElement(name = "Direction")
public enum Direction {
/** Cardinal North Direction. */
NORTH,
/** Cardinal East Direction. */
EAST,
/** Cardinal South Direction. */
SOUTH,
/** Cardinal West Direction. */
WEST;
}

