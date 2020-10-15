package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Middle-of-the-Linked-List-a414c9809011450cae27d342649cc256
 */
public class MiddleOfLinkedList {
  public class Node<T> {
    public Node<T> next;
    public T value;
  }
  public Node<Integer> middleNode(Node<Integer> head) {
    Node<Integer> slow = head, fast = head;
    //O(n) complexity, where n is half the length of the list
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
