package persistence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Pair.
 * @author monika gill A Data Type to Store Pairs of Objects for Convenience i.e X,Y Coordinates.
 * @param <K> The First Object of the pair.
 * @param <V> The Second Object of the pair.
 */
@XmlRootElement(name = "pair")
@XmlAccessorType(XmlAccessType.FIELD)
public class PairPer<K, V> {
  private K key;
  private V value;

  /**
   * Creates a new Pair.
   *
   * @param key The First Object of the pair.
   * @param value The Second Object of the pair.
   */
  public PairPer(K key, V value) {
    super();
    this.key = key;
    this.value = value;
  }

  /**
   * Player per.
   */
  public PairPer() {
    super();
  }

  /**
   * Returns the First value.
   *
   * @return the First value
   */
  public K getKey() {
    return key;
  }

  /**
   * Set the First value.
   *
   * @param key The value to be set.
   */
  public void setKey(K key) {
    this.key = key;
  }

  /**
   * Returns the Second value.
   *
   * @return the Second value
   */
  public V getValue() {
    return value;
  }

  /**
   * Set the Second value.
   *
   * @param value The value to be set.
   */
  public void setValue(V value) {
    this.value = value;
  }

  /**
   * Assign to gameworld.
   * @param pair pair.
   */
  public void assign(gameworld.Pair<K, V> pair) {
    this.setKey(pair.getKey());
    this.setValue(pair.getValue());

  }
}

