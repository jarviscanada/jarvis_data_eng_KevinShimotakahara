package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.HasCycle.Node;
import org.junit.Test;

public class HasCycleTest {

  @Test
  public void hasCycle() {
    HasCycle hasCycle = new HasCycle();
    Node<Integer> head = hasCycle.new Node<>();
    Node<Integer> current = head;
    for(int i = 0; i < 10; i++){
      current.next = hasCycle.new Node<>();
      current.value = i;
      current = current.next;
    }
    assertFalse(hasCycle.hasCycle(head));
    current.next = head.next.next;
    assertTrue(hasCycle.hasCycle(head));
    current.next = head.next.next.next;
    assertTrue(hasCycle.hasCycle(head));
  }
}