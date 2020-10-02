package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.RemoveNthLastNodeFromLinkedList.ListNode;
import org.junit.Test;

public class RemoveNthLastNodeFromLinkedListTest {

  @Test
  public void removeNthFromEnd() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);

    RemoveNthLastNodeFromLinkedList remover = new RemoveNthLastNodeFromLinkedList();
    assertEquals(1, remover.removeNthFromEnd(head,2).val);
    assertEquals(4,head.next.next.val);

    head = remover.removeNthFromEnd(head,3);
    assertEquals(2,head.val);
  }
}