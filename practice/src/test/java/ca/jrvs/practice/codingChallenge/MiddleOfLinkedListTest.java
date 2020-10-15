package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.MiddleOfLinkedList.Node;
import org.junit.Test;

public class MiddleOfLinkedListTest {

  @Test
  public void middleNode() {
    MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();
    Node<Integer> head = middleOfLinkedList.new Node<>();
    Node<Integer> current = head;
    for(int i = 0; i < 10; i++){
      current.next = middleOfLinkedList.new Node<>();
      current.value = i;
      current = current.next;
    }
    assertEquals((int) middleOfLinkedList.middleNode(head).value,5);
  }
}