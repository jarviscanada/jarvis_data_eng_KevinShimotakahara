package ca.jrvs.practice.dataStructure.list;
import java.util.NoSuchElementException;
import java.util.Collection;

public class LinkedJListJDeque<E> implements JDeque<E> {
  private LinkedJList<E> ll = new LinkedJList<>();
  /**
   * This is equivalent enqueue operation in Queue ADT
   * <p>
   * Inserts the specified element into the queue represented by this deque (in other words, at the
   * tail of this deque) if it is possible to do so immediately without violating capacity
   * restrictions, returning {@code true} upon success and throwing an {@code IllegalStateException}
   * if no space is currently available.
   *
   * @param e the element to add
   * @return {@code true} (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
    //In this implementation, the head of the queue is the head of the linked list object.
    //To make peek() work for both queue and stack, top of stack must be at head of linked list object.
    //This way, if user suddenly wants to think of their queue as a stack or vice versa, peek()
    //will reveal the same element regardless of interpretation.
    if (e == null){
      throw new NullPointerException("Don't add nulls to queue");
    }
    //Thus, "add" = "enqueue" = "add to end of linked list implementing deque/queue/stack"
    ll.linkLast(e);
    return true;
  }

  /**
   * This is equivalent dequeue operation in Queue ADT
   * <p>
   * Retrieves and removes the head of the queue represented by this deque (in other words, the
   * first element of this deque).
   *
   * @return the head of the queue represented by this deque
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E remove() {
    if (ll.isEmpty()){
      throw new NoSuchElementException("Queue is empty.");
    }
    return ll.remove(0);
  }

  /**
   * Pops an element from the stack represented by this deque. In other words, removes and returns
   * the first element of this deque.
   *
   * @return the element at the front of this deque (which is the top of the stack represented by
   * this deque)
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E pop() {
    return ll.remove(0);
  }

  /**
   * Pushes an element onto the stack represented by this deque (in other words, at the head of this
   * deque) if it is possible to do so immediately without violating capacity restrictions
   *
   * @param e the element to push
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public void push(E e) {
    ll.linkFirst(e);
  }

  /**
   * Retrieves, but does not remove, the head of the queue represented by this deque (in other
   * words, the first element of this deque), or returns {@code null} if this deque is empty. Also
   * can be used to "peek" at the stack interpretation of this class.
   *
   * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
   */
  @Override
  public E peek() {
    return ll.get(0);
  }
}
