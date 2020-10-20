package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Ticket URL: https://www.notion.so/Reverse-Linked-List-9cdc659fbb61437597ae969770c7c54d
 */
public class ReverseLinkedList {
  public class Node{
    Integer value;
    Node next;
  }

  public Node reverseLinkedListIterative(Node head){
    //check if case is trivial
    if(head == null || head.next == null)
      return head;

    Deque<Node> stack = new LinkedList<>();
    Node current = head;
    //push all but tail nodes onto the stack O(n)
    while(current.next != null){
      stack.push(current);
      current = current.next;
    }
    head = current;
    //pop and append all nodes to list in new order O(n)
    while(!stack.isEmpty()){
      current.next = stack.pop();
      current = current.next;
    }
    //make tail node's next value null
    current.next = null;
    return head;
  }

  public Node reverseLinkedListRecursive(Node head){
    if(head == null || head.next == null){
      //You either have an empty list, or reached the base case
      return head;
    }
    Node newHead = reverseLinkedListRecursive(head.next);
    //reverse direction of head.next's next value
    head.next.next = head;
    //make current node's next value point to null (i.e. it is tentatively
    // made the tail of the reversed list)
    head.next = null;
    //bubble the new head up the call stack
    return newHead;
  }
}
