package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class RemoveDuplicatesFromLinkedListTest {

  @Test
  public void removeDuplicatesFromLinkedList() {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(1);
    l.add(10);
    l.add(2);
    l.add(1);
    l.add(1);
    l.add(1);
    l.add(1);
    l.add(7);
    l.add(1);

    List<Integer> expected = new LinkedList<>();
    expected.add(1);
    expected.add(10);
    expected.add(2);
    expected.add(7);

    RemoveDuplicatesFromLinkedList remover = new RemoveDuplicatesFromLinkedList();
    remover.removeDuplicatesFromLinkedList(l);

    assertEquals(expected,l);
  }
}