package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.Collection;

public class LinkedJList<E> implements JList{
  node<Object> head;
  node<Object> tail;
  int size = 0;

  /**
   * Appends the specified element to the end of this list (optional
   * operation).
   *
   * @param o element to be appended to this list
   * @return <tt>true</tt> (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this
   *         list does not permit null elements
   */
  @Override
  public boolean add(Object o) {
    if(o == null){
      throw new NullPointerException("This list implementation does not allow null elements.");
    }
    node<Object> n = new node<>(o);
    if(head == null){
      //adding an object to empty list
      head = n;
    }else{
      //insert at end of list
      n.setPrevious(tail);
      tail.setNext(n);
    }
    tail = n;
    size++;
    return true;
  }

  /**
   * Returns an array containing all of the elements in this list in proper
   * sequence (from first to last element).
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs.
   *
   * @return an array containing all of the elements in this list in proper
   *         sequence
   */
  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (node<Object> x = head; x != null; x = x.getNext())
      result[i++] = x.getValue();
    return result;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the index of the first occurrence of the specified element
   * in this list, or -1 if this list does not contain the element.
   * More formally, returns the lowest index <tt>i</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index.
   */
  @Override
  public int indexOf(Object o) {
    int i = 0;
    for (node<Object> x = head; x != null; x = x.getNext()){
      if (o != null){
        if (o.equals(x.getValue())){
          return i;
        }
      }else if(x == null){
        return i;
      }
      i++;
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element.
   * More formally, returns <tt>true</tt> if and only if this list contains
   * at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this
   *         list does not permit null elements
   */
  @Override
  public boolean contains(Object o) {
    if(o == null){
      throw new NullPointerException("Do not pass null arguments to this method");
    }
    for (node<Object> x = head; x != null; x = x.getNext()){
      if (o.equals(x.getValue())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range
   *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
   */
  @Override
  public E get(int index) {
    if (index >= size || index < 0){
      throw new IndexOutOfBoundsException("Index out of bounds: " + index +
                                          "; current list size: " + size);
    }
    int i = 0;
    for (node<Object> x = head; x != null; x = x.getNext()){
      if (i == index) {
        return (E)x.getValue();
      }
    }
    return null;
  }

  /**
   * Removes the element at the specified position in this list.
   * Shifts any subsequent elements to the left (subtracts one from their
   * indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index >= size || index < 0){
      throw new IndexOutOfBoundsException("Index out of bounds: " + index +
          "; current list size: " + size);
    }
    int i = 0;
    for (node<Object> x = head; x != null; x = x.getNext()){
      if (i == index) {
        if (x != null){
          if(x == head){
            head = x.getNext();
          }else{
            x.getPrevious().setNext(x.getNext());
          }
          if(x == tail){
            tail = x.getPrevious();
          }else{
            x.getNext().setPrevious(x.getPrevious());
          }
          size--;
          return (E)x.getValue();
        }
      }
      i++;
    }
    return null;
  }

  @Override
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
}
