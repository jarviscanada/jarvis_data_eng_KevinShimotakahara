package ca.jrvs.practice.dataStructure.list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;


public class ArrayJList<E> implements JList<E> {

  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * The array buffer into which the elements of the ArrayList are stored.
   * The capacity of the ArrayList is the length of this array buffer.
   */
  transient Object[] elementData; // non-private to simplify nested class access
  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  private int size;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param  initialCapacity  the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity
   *         is negative
   */
  public ArrayJList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
          initialCapacity);
    }
  }

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public ArrayJList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Appends the specified element to the end of this list (optional
   * operation).
   *
   * Double elementData size if elementData is full.
   */
  @Override
  public boolean add(E e) {
    int eldLen = elementData.length;
    if(eldLen <= size){
      //need to double elementData size
      int minCapacity = eldLen + 1;
      if (eldLen > 0) {
        elementData = Arrays.copyOf(elementData, eldLen << 1);
      }else{
        elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
      }
    }
    elementData[size] = e;
    size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(Object o) {
    Object[] es = elementData;
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (es[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (o.equals(es[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    Object[] es = elementData;
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (es[i] == null) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (o.equals(es[i])) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public E get(int index) {
    return (E)elementData[index];
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
    final Object[] es = elementData;
    if(index >= size){
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }else {
      @SuppressWarnings("unchecked") E oldValue = (E) es[index];
      final int newSize;
      if ((newSize = size - 1) > index)
        //this method takes the portion of the array after the index of element being removed
        //and copies it back into elementData starting at the index we are removing the element from
        System.arraycopy(es, index + 1, es, index, newSize - index);
      //this both updates size and nullifies the last element of the array that should now be empty
      es[size = newSize] = null;
      return oldValue;
    }
  }

  @Override
  public void clear() {
    elementData = new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  public static void main(String[] args) {
    //just some quick and dirty validation...
    ArrayJList<String> arrayJList = new ArrayJList(3);
    arrayJList.add("0");
    arrayJList.add("1");
    arrayJList.add("2");
    arrayJList.add("3");
    System.out.println(Arrays.toString(arrayJList.toArray()));
    arrayJList.remove(2);
    System.out.println(Arrays.toString(arrayJList.toArray()));
    System.out.println(arrayJList.size()+" "+arrayJList.contains("1")+" "+arrayJList.contains("2"));
    System.out.println(arrayJList.get(2)+" "+arrayJList.indexOf("3")+" "+arrayJList.indexOf("lol"));
    arrayJList.clear();
    System.out.println(Arrays.toString(arrayJList.toArray()));
    arrayJList.remove(1);
  }
}