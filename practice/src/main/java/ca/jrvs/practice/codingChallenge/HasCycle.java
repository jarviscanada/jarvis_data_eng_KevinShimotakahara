package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/LinkedList-Cycle-6353c6bb5d574ba3b1b959617e242f6c
 */
public class HasCycle {
  public class Node<T> {
    public Node<T> next;
    public T value;
  }
  public boolean hasCycle(Node<Integer> head){
    Node<Integer> fast = head;
    Node<Integer> slow = head;
    //O(n) complexity; n is proportional to list size and cycle length
    while(fast != null && fast.next != null && fast.next.next != slow.next) {
      fast = fast.next.next;
      slow = slow.next;
    }
    if(fast == null || fast.next == null)
      return false;
    return true;
  }
}
