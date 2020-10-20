package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReverseLinkedListTest {
  private ReverseLinkedList.Node head;
  private ReverseLinkedList reverseLinkedList;
  @Before
  public void init(){
    reverseLinkedList = new ReverseLinkedList();
    head = reverseLinkedList.new Node();
    ReverseLinkedList.Node current = head;
    for(int i = 0; i < 4; i++){
      current.next = reverseLinkedList.new Node();
      current.value = i;
      current = current.next;
    }
    head.next.next.next.next = null;
  }
  @Test
  public void reverseLinkedListIterative() {
    head = reverseLinkedList.reverseLinkedListIterative(head);
    assertEquals(3,(int) head.value);
    assertEquals(2,(int) head.next.value);
    assertEquals(1,(int) head.next.next.value);
    assertEquals(0,(int) head.next.next.next.value);
  }

  @Test
  public void reverseLinkedListRecursive() {
    head = reverseLinkedList.reverseLinkedListRecursive(head);
    assertEquals(3,(int) head.value);
    assertEquals(2,(int) head.next.value);
    assertEquals(1,(int) head.next.next.value);
    assertEquals(0,(int) head.next.next.next.value);
  }
}