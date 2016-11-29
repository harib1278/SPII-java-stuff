package linkedlist;

/**
 * Generic helper class to represent elements of a (singly) linked lists.
 *
 * @param T the type of the data objects to store T is a generic type
 * @author Carsten Fuhs
 */
public class Element<T> {

    private T data;  // the data object encapsulated by this Element
    private Element<T> next;  // the next Element in the list

    /**
     * Constructs a new <code>Element</code> that stores <code>data</code>
     * internally and that has <code>null</code> as next element.
     *
     * @param data will be encapsulated in this <code>Element</code>
     */
    public Element(T data) {
        this(data, null);
    }

    /**
     * Constructs a new <code>Element</code> that stores <code>data</code>
     * internally and that has <code>next</code> as next element.
     *
     * @param data will be encapsulated in this <code>Element</code>
     * @param next the <code>Element</code> to be used as the next
     *  <code>Element</code>
     */
    public Element(T data, Element<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Getter for the encapsulated data object.
     *
     * @return the encapsulated data object
     */
    public T getData() {
        return this.data;
    }

    /**
     * Setter for the encapsulated data object.
     *
     * @param data the data object to store
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Getter for the next <code>Element</code> referenced by this
     * <code>Element</code>.
     *
     * @return the next <code>Element</code> referenced by this
     *  <code>Element</code>
     */
    public Element<T> getNext() {
        return this.next;
    }

    /**
     * Setter for the next <code>Element</code> referenced by this
     * <code>Element</code>.
     *
     * @param next the next <code>Element</code> to be referenced by this
     *  <code>Element</code>
     */
    public void setNext(Element<T> next) {
        this.next = next;
    }

    /**
     * Returns a <code>String</code> representation of this
     * <code>Element</code>.
     *
     * Note: This implementation of <code>toString()</code> only
     * uses the current <code>Element</code>, but not the next
     * <code>Element</code>.
     *
     * @return a <code>String</code> representation of this <code>Element</code>
     */
    @Override
    public String toString() {
        return this.data.toString(); // only prints one Element
    }
}
