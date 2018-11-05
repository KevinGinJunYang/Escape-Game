package gameworld;

/**
 * A Data Type to Store Pairs of Objects for Convenience i.e X,Y Coordinates.
 *
 * @author Remi
 *
 * @param <K>
 *          The First Object of the pair.
 * @param <V>
 *          The Second Object of the pair.
 */
public class Pair<K, V> {
  private K key;
  private V value;

  /**
   * Creates a new Pair.
   *
   * @param key
   *          The First Object of the pair.
   * @param value
   *          The Second Object of the pair.
   */
  public Pair(K key, V value) {
    super();
    this.key = key;
    this.value = value;
  }

  @Override
  public int hashCode() {
    int hashFirst = key != null ? key.hashCode() : 0;
    int hashSecond = value != null ? value.hashCode() : 0;

    return (hashFirst + hashSecond) * hashSecond + hashFirst;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Pair) {
      @SuppressWarnings("rawtypes")
      Pair otherPair = (Pair) other;
      return ((this.key == otherPair.key
          || (this.key != null && otherPair.key != null && this.key.equals(otherPair.key)))
          && (this.value == otherPair.value || (this.value != null && otherPair.value != null
              && this.value.equals(otherPair.value))));
    }

    return false;
  }

  public String toString() {
    return "(" + key + ", " + value + ")";
  }

  /**
   * Returns the First value.
   *
   * @return the First Value
   */
  public K getKey() {
    return key;
  }

  /**
   * Set the First value.
   *
   * @param key
   *          The Value to be set.
   */
  public void setKey(K key) {
    this.key = key;
  }

  /**
   * Returns the Second value.
   *
   * @return the Second Value
   */
  public V getValue() {
    return value;
  }

  /**
   * Set the Second value.
   *
   * @param value
   *          The Value to be set.
   */
  public void setValue(V value) {
    this.value = value;
  }
}
