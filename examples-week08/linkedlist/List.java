package linkedlist;

import java.util.ArrayList;

/**
 * Generic implementation of a singly-linked list in Java. Can also store
 * <code>null</code> elements.
 *
 * @param <T> the type of the data objects to store
 * @author Carsten Fuhs
 */
public class List<T> {
    // the first element of this List; null if this List is empty
    private Element<T> head;

    /**
     * Constructs an empty <code>List</code>.
     */
    public List() {
        this.head = null;
    }

    /**
     * Copy constructor: Constructs a shallow copy of <code>other</other>.
     * Here "shallow copy" means that a new list structure is created (so that
     * later structural modifications to the new <code>List</code> will not
     * affect <code>other</code> and vice versa), but the stored objects
     * themselves are not duplicated.
     *
     * @param other to be copied to a new <code>List</code>
     */
    public List(List<? extends T> other) {
        this.head = copy(other.head);
    }

    /**
     * Static recursive helper method to copy a list.
     *
     * @param head its contents are to be copied to a new list
     * @param <T> the type of the list
     * @return a new list with the same contents as the list headed by
     *  <code>head</code>
     */
    private static <T> Element<T> copy(Element<? extends T> head) {
        if (head == null) {
            return null;
        }
        Element<T> copyOfNext = copy(head.getNext());
        return new Element<T>(head.getData(), copyOfNext);
    }


    /**
     * Check whether this <code>List</code> is empty.
     *
     * @return whether this <code>List</code> is empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the size of this <code>List</code>, i.e., the number of
     * its elements.
     *
     * @return the number of elements of this <code>List</code>
     */
    public int size() {
        return size(this.head);
    }

    /**
     * Recursive static helper method to compute the size of a
     * <code>List</code> via its <code>Element</code>s
     *
     * @param head the <code>Element</code> starting from which
     *  we wish to compute the size of the list
     * @param <T> the generic type of the <code>Element</code>
     */
    private static <T> int size(Element<T> head) {
        // Since the method is static, we need to explicitly declare
        // that T is a type variable (and not the name of a class)
        if (head == null) {
            return 0;
        }
        return 1 + size(head.getNext()); // compute size recursively
    }

    /**
     * Iterative implementation to determine the size of this
     * <code>List</code>.
     *
     * @return the size of this <code>List</code>
     */
    public int sizeIterative() {
        int res = 0; // results
        Element<T> current = this.head;
        while (current != null) {
            res++;
            current = current.getNext();
        }
        return res;
    }

    /**
     * Clears this <code>List</code>, i.e., removes all elements.
     */
    public void clear() {
        // Java's garbage collector will do the actual clearing of the memory
        this.head = null;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative, found: "
                                               + index);
        }
        Element<T> current = this.head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IllegalArgumentException("Index exceeds list bounds!");
            }
            current = current.getNext();
        }
        if (current == null) {
            throw new IllegalArgumentException("Index exceeds list bounds!");
        }
        return current.getData();
    }

    /**
     * Getter for the data object stored at position <code>index</code>
     * in this <code>List</code>.
     *
     * @param index the index of the data object to retrieve, where
     *  0 <= index < this.size() should hold
     * @throws IllegalArgumentException if index < 0 or index >= this.size()
     */
    public T getRecursive(int index) {
        return getRecursive(index, this.head);
    }

    /**
     * Recursive static helper method to get a data object from a list.
     *
     * @param index the position in the list structure starting at
     *  <code>head</code> from which we want to retrieve the data object
     * @param head the list structure from which we want to retrieve the
     *  data object at position <code>index</code>
     * @param <T> the generic type of the <code>Element</code>
     * @throws IllegalArgumentException if <code>index</code> is not a
     *  legal position from <code>head</code>
     */
    private static <T> T getRecursive(int index, Element<T> head) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative, found: "
                                               + index);
        }
        if (head == null) {
            throw new IllegalArgumentException("Index exceeds list bounds!");
        }
        if (index == 0) {
            return head.getData();
        }
        return getRecursive(index - 1, head.getNext());
    }

    /**
     * Iterative implementation to compute the first index in this
     * <code>List</code> where <code>value</code> is stored.
     *
     * @param value the value whose first index in this <code>List</code>
     *  we want to determine
     * @return the first index where <code>value</code> is stored in this
     *  <code>List</code>, or -1 if there is no such index
     */
    public int indexOf(T value) {
        int res = 0;
        Element<T> current = this.head;

        // the second condition of "&&" is evaluated only if the first one
        // becomes true (so no need to worry about a NullPointerException
        // due to current.getData())
        while (current != null && ! areEqual(value, current.getData())) {
            res++;
            current = current.getNext();
        }

        if (current == null) {
            return -1; // data value not found
        }
        return res;
    }

    /**
     * Static helper method to check two (potentially <code>null</code>)
     * references for object equality. Could also use Objects.equals(x,y)
     * from the Java API.
     *
     * @param x may be null
     * @param y may be null
     * @param U the type of <code>x</code> and <code>y</code>
     * @return true if <code>x</code> and <code>y</code> are both
     *  <code>null</code> or <code>x.equals(y)</code>; false otherwise
     */
    private static <U> boolean areEqual(U x, U y) {
        if (x == null) {
            return y == null;
        } else if (y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    /**
     * Find the first index in this <code>List</code> where <code>value</code>
     * is stored.
     *
     * @param value the value whose first index in this <code>List</code>
     *  we want to determine
     * @return the first index where <code>value</code> is stored in this
     *  <code>List</code>, or -1 if there is no such index
     */
    public int indexOfRecursive(T value) {
        // return -1 if value is not present in list
        return indexOfRecursive(value, this.head);
    }

    /**
     * Recursive static helper method to get first the first index
     * in a list where <code>value</code> is stored.
     *
     * @param value the value whose first index in this <code>List</code>
     *  we want to determine
     * @param head the head of the list structure
     * @param <T> the generic type of <code>value</code> and the
     *  <code>Element</code>
     * @return the first index where <code>value</code> is stored in
     *  <code>head</code>, or -1 if there is no such index
     */
    private static <T> int indexOfRecursive(T value, Element<T> head) {
        if (head == null) {
            return -1;
        }
        if (areEqual(value, head.getData())) {
            return 0;
        }
        int index = indexOfRecursive(value, head.getNext());
        if (index == -1) { // data value not found
            return -1;
        }
        return 1 + index;
    }


    /**
     * Checks whether this <code>List</code> contains <code>value</code>.
     *
     * @param value the value for which we want to know whether it is in this 
     *  <code>List</code>
     * @return whether this <code>List</code> contains <code>value</code>
     */
    public boolean contains(T value) {
        // no need to essentially duplicate our indexOf method, just call it
        return this.indexOf(value) != -1;
    }

    /**
     * Adds the value <code>value</code> at position <code>index</code> to this
     * <code>List</code>.
     *
     * @param index we want to add <code>value</code> at this position,
     *  0 <= index <= this.size() should hold
     * @param value to be added
     * @throws IllegalArgumentException if index < 0 or index > this.size()
     */
    public void add(int index, T value) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative, found: "
                                               + index);
        }
        if (index == 0) {
            this.head = new Element<T>(value, this.head);
        } else {
            Element<T> current = this.head;
            int i = 0; // current index in the list
            while (current != null) {
                if (i == index - 1) {
                    // We are now right before the position where we want
                    // value to go. So make a new Element for value;
                    // it should point to the next Element after current
                    Element<T> newElement = new Element<T>(value, current.getNext());

                    // Now we only need to make current point to newElement
                    // to ensure that we have inserted newElement
                    current.setNext(newElement);
                    return; // we're done!
                }
                current = current.getNext();
                i++;
            }
            // we did not return from within the loop
            throw new IllegalArgumentException("Index exceeds list bounds, found: "
                                               + index);
        }
    }
        
    /**
     * Adds the value <code>value</code> at position <code>index</code> to this
     * <code>List</code>.
     *
     * @param index we want to add <code>value</code> at this position,
     *  0 <= index <= this.size() should hold
     * @param value to be added
     * @throws IllegalArgumentException if index < 0 or index > this.size()
     */
    public void addRecursive(int index, T value) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative, found: "
                                               + index);
        }
        if (index == 0) {
            this.head = new Element<T>(value, this.head);
        } else {
            if (this.head == null) {
                throw new IllegalArgumentException("Index exceeds list bounds!");
            }
            addRecursive(index, value, this.head);
        }
    }

    /**
     * Static recursive helper method to add a value to a list
     * at a specified position in the list.
     *
     * @param index position where we want to add <code>value</code>;
     *  must be greater then 0
     * @param value to be added
     * @param head we want to add <code>value</code> to the list headed by
     *  <code>head</code>; non-null
     * @param <T> type of the value to be added
     * @throws IllegalArgumentException if index > 0 and index exceeds list bounds
     */
    private static <T> void addRecursive(int index, T value, Element<T> head) {
        assert head != null;
        assert index > 0;
        if (index == 1) { // add after the current head
            addAfterElement(value, head);
        } else {
            if (head == null) {
                throw new IllegalArgumentException("Index exceeds list bounds!");
            }
            addRecursive(index - 1, value, head.getNext());
        }
    }

    /**
     * Adds <code>value</code> as the last element to this <code>List</code>.
     *
     * @param value to be added as the last element to this <code>List</code>.
     */
    public void addLast(T value) {
        if (this.head == null) { // special case for the empty list
            this.head = new Element<T>(value);
        } else {
            addLast(value, this.head);
        }
    }

    /**
     * Recursive static helper method to add <code>value</code>
     * to the list with head element <code>head</code>.
     *
     * @param value to be added
     * @param head the head element of the list to which we want
     *  to add <code>value</code>; must be non-null
     */
    private static <T> void addLast(T value, Element<T> head) {
        assert head != null; // this should *always* hold, so use an assertion
        if (head.getNext() == null) {
            head.setNext(new Element<T>(value));
        } else {
            addLast(value, head.getNext());
        }
    }

    /**
     * Adds <code>newValue</code> to this <code>List</code> before the first
     * occurrence of <code>oldValue</code>, or at the end of there is no such
     * occurrence.
     *
     * @param newValue to be added
     * @param oldValue the value before which we want to store
     *  <code>newValue</code> in this <code>List</code>
     */
    public void addBefore(T newValue, T oldValue) {
        if (this.head == null || areEqual(oldValue, this.head.getData())) {
            this.head = new Element<T>(newValue);
        } else {
            addBefore(newValue, oldValue, head);
        }
    }

    /**
     * Recursive static helper method to add <code>newValue</code>
     * to <code>head</code> before <code>oldValue</code>.
     *
     * @param newValue to be added to <code>head</code>
     * @param oldValue the value before which we want to store
     *  <code>newValue</code> in <code>head</code>
     * @param head we want to store <code>newValue</code> here; non-null
     * @param <T> the type for the data that we want to store
     */
    private static <T> void addBefore(T newValue, T oldValue, Element<T> head) {
        assert head != null;
        if (head.getNext() == null) { // oldValue not found, add at the end
            head.setNext(new Element<T>(newValue));
        } else if (areEqual(oldValue, head.getNext().getData())) { // add here!
            addAfterElement(newValue, head);
        } else { // not there yet, so call addBefore recursively
            addBefore(newValue, oldValue, head.getNext());
        }
    }

    /**
     * Static helper method that inserts <code>newValue</code> after
     * <code>head</code>.
     *
     * @param newValue to be added to <code>head</code>
     * @param head we want to add <code>newValue</code> here; non-null
     * @param <T> the type of the value to be added
     */
    private static <T> void addAfterElement(T newValue, Element<T> head) {
        assert head != null;

        // make newElement point to the element following head,
        // before which we want to add newElement
        Element<T> newElement = new Element<T>(newValue, head.getNext());

        // and make head point to to newElement
        head.setNext(newElement);
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices). Returns the element that was removed
     * from the list.
     */
    public T remove(int index) {
        // iterative implementation analogous to add
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative, found: "
                                               + index);
        }
        if (index == 0) {
            if (this.head != null) {
                T result = this.head.getData();
                this.head = this.head.getNext();
                return result;
            }
            throw new IllegalArgumentException("Index exceeds list bounds, found: "
                                              + index);
        } else {
            Element<T> current = this.head;
            int i = 0; // current index in the list
            while (current != null) {
                if (i == index - 1) {
                    // We are now right before the index where we want to
                    // remove the Element from the list
                    Element<T> toRemove = current.getNext();
                    if (toRemove == null) {
                        throw new IllegalArgumentException("Index exceeds list bounds, found: "
                                                            + index);
                    }
                    // take toRemove out of the pointer sequence from current
                    // and point to its successor instead
                    current.setNext(toRemove.getNext());
                    return toRemove.getData(); // we're done!
                }
                current = current.getNext();
                i++;
            }
            // we did not return from within the loop
            throw new IllegalArgumentException("Index exceeds list bounds, found: "
                                               + index);
        }
    }

    
    /**
     * Produces a <code>String</code> representation of this <code>List</code>.
     *
     * @return a <code>String</code> representation of this <code>List</code>
     *  with its values in the order in which they are stored
     */
    @Override
    public String toString() {
        return "[" + toStringContents(this.head) + "]";
    }

    /**
     * Static recursive helper method to obtain a <code>String</code>
     * representation of a list.
     *
     * @param head we want to represent the list starting here
     * @return a <code>String</code> representation of the list starting
     *  with <code>head</code>
     */
    private static String toStringContents(Element<?> head) {
        // note the unbounded wildcard in the method signature:
        // it does not matter what kind of objects are stored in head
        if (head == null) {
            return "";
        } else if (head.getNext() == null) { // just 1 element, so no comma
            return head.toString();
        } else {
            // use recursion to print content of the whole list
            return head.toString() + ", " + toStringContents( head.getNext() );
        }
    }

    /**
     * Produces a <code>String</code> representation of this <code>List</code>.
     *
     * @return a <code>String</code> representation of this <code>List</code>
     *  with its values in <i>reverse</i> order
     */
    public String toStringReverse() {
        return "[" + toStringContentsReverse(this.head) + "]";
    }

    /**
     * Static recursive helper method to obtain a <code>String</code>
     * representation of a list in reverse.
     *
     * @param head we want to represent the list starting here
     * @return a <code>String</code> representation of the list starting
     *  with <code>head</code>, in reverse
     */
    private static String toStringContentsReverse(Element<?> head) {
        if (head == null) {
            return "";
        } else if (head.getNext() == null) {
            return head.toString();
        } else {
            // the only difference to toStringContents(...) is
            // in the calls in the following line (and their order)
            return toStringContentsReverse( head.getNext() ) + ", " + head.toString();
        }
    }

    /**
     * Deletes the first occurrence of <code>value</code> from
     * this <code>List</code>. Does not modify anything if this
     * <code>List</code> does not contain <code>value</code>.
     *
     * @param value to be deleted from this <code>List</code>
     * @return whether an element got deleted from this <code>List</code>
     */
    public boolean delete(T value) {
        if (this.head == null) {
            return false;
        }
        if (areEqual(value, this.head.getData())) {
            this.head = this.head.getNext();
            return true;
        }
        return delete(value, this.head);
    }

    /**
     * Recursive static helper method to delete a value from a list.
     *
     * @param value to be deleted; must not be equal to the first value
     *  in <code>head</code>
     * @param head list in which the deletion is to take place; non-null
     * @param <T> the type of the value to be deleted
     * @return whether an element was deleted
     */
    private static <T> boolean delete(T value, Element<T> head) {
        assert head != null;
        assert ! areEqual(value, head.getData());
        if (head.getNext() == null) {
            return false;
        }
        if (areEqual(value, head.getNext().getData())) {
            // unlink the element with value in it
            head.setNext(head.getNext().getNext());
            return true;
        }
        return delete(value, head.getNext());
    }

    /**
     * Deletes the first element from this <code>List</code>.
     * Has no effect if this <code>List</code> is empty.
     *
     * @return whether an element was deleted
     */
    public boolean deleteFirst() {
        if (this.head == null) {
            return false;
        }
        this.head = this.head.getNext();
        return true;
    }

    /**
     * Creates a copy of this <code>List</code> as an <code>ArrayList</code>.
     *
     * @return an <code>ArrayList</code> that stores the same elements
     *  in the same order as this <code>List</code>
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> res = new ArrayList<T>();
        fillArrayList(res, this.head);
        return res;
    }

    /**
     * Static recursive helper method to copy a list to an <code>ArrayList</code>.
     *
     * @param target we want to copy the list starting with <code>head</code> here
     * @param head to be copied
     * @param <T> the type of the data in the list
     */
    private <T> void fillArrayList(ArrayList<? super T> target, Element<T> head) {
        if (head == null) {
            return;
        }
        target.add(head.getData());
        fillArrayList(target, head.getNext());
    }
}
