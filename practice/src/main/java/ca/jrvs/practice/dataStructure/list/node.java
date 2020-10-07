package ca.jrvs.practice.dataStructure.list;

public class node<E> {
  private E value;
  private node next;
  private node previous;

  public node(E value){
    this.value = value;
  }

  public node(node previous, E value, node next){
    this.value = value;
    this.next = next;
    this.previous = previous;
  }

  public E getValue() {
    return value;
  }

  public void setValue(E value) {
    this.value = value;
  }

  public node getNext() {
    return next;
  }

  public void setNext(node next) {
    this.next = next;
  }

  public node getPrevious() {
    return previous;
  }

  public void setPrevious(node previous) {
    this.previous = previous;
  }
}
