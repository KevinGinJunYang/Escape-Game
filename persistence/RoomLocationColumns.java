package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**Room location, columns.
 * @author monika gill
 *
 */
@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomLocationColumns {

  @XmlElementWrapper(name = "columns")
  @XmlElement(name = "location")
  private ArrayList<LocationPer> columns;

  /**Getter columns.
   * @return columns
   */
  public ArrayList<LocationPer> getColumns() {
    return columns;
  }

  /**Setter of columns.
   * @param columns column list to be added.
   */
  public void setColumns(ArrayList<LocationPer> columns) {
    this.columns = columns;
  }

}
