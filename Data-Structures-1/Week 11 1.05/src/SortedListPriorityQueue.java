import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.text.Position;


/** Implementation of a priority queue by means of a sorted list */
public class SortedListPriorityQueue implements PriorityQueue {
  protected List L;
  protected Comparator c;
  protected Position actionPos; // variable used by subclasses
  /** Inner class for entries */
  protected static class MyEntry implements Entry {
    protected Object k; // key
    protected Object v; // value
    public MyEntry(Object key, Object value) {
      k = key;
      v = value;
    }
    // methods of the Entry interface
    public Object key() { return k; }
    public Object value() { return v; }
  }
  /** Inner class for a default comparator using the natural ordering */
  protected static class DefaultComparator implements Comparator {
    public DefaultComparator() { /* default constructor */ }
    public int compare(Object a, Object b)
      throws ClassCastException { 
      return ((Comparable) a).compareTo(b);
    }
  }
  /** Creates the priority queue with the default comparator. */
  public SortedListPriorityQueue () {
    L = new NodeList();
    c = new DefaultComparator();
  }
  /** Creates the priority queue with the given comparator. */
  public SortedListPriorityQueue (Comparator comp) {
    L = new NodeList();
    c = comp;
  }
  /** Returns but does not remove an entry with minimum key. */
  public Entry min () throws EmptyPriorityQueueException {
    if (L.isEmpty())
      throw new
        EmptyPriorityQueueException("priority queue is empty");
    else
      return (Entry) L.first().element();
  }

  /** Inserts a key-value pair and return the entry created. */
  public Entry insert (Object k, Object v)
    throws InvalidKeyException {
    // auxiliary key-checking method (could throw exception):
    checkKey(k);
    Entry entry = new MyEntry(k, v);
    insertEntry(entry); // auxiliary insertion method
    return entry;
  }
  /** Auxiliary method used for insertion. */
  protected void insertEntry(Entry e) {
    Object k = e.key();
    if (L.isEmpty()) {
      actionPos = L.insertFirst(e); // insert into empty list
    }
    else if (c.compare(k, key(L.last())) > 0) {
      actionPos = L.insertLast(e); // insert at the end of the list
    }
    else {
      Position curr = L.first();
      while (c.compare(k, key(curr))> 0) {
        curr = L.next(curr);    // advance toward insertion position
      }
      actionPos = L.insertBefore(curr, e); // useful for subclasses
    }
  }
  /** Removes and returns an entry with minimum key. */
  public Entry removeMin() throws EmptyPriorityQueueException {
    if (L.isEmpty())
      throw new
        EmptyPriorityQueueException("priority queue is empty");
    else
      return (Entry) (L.remove(L.first()));
  }
  protected Object key(Position pos) {
    return ((Entry) pos.element()).key();
  }
}