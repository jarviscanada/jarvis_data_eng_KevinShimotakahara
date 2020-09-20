package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedJListJDequeTest {

  @Test
  public void queueTesting() {
    LinkedJListJDeque<String> queue = new LinkedJListJDeque<>();
    queue.add("First in Line");
    queue.add("Second in Line");
    queue.add("Third in Line");
    assertEquals(queue.peek(),"First in Line");
    assertEquals(queue.remove(),"First in Line");
    assertEquals(queue.peek(),"Second in Line");
  }

  @Test
  public void stackTesting() {
    LinkedJListJDeque<String> stack = new LinkedJListJDeque<>();
    stack.push("first in");
    stack.push("second in");
    stack.push("third in");
    assertEquals(stack.peek(),"third in");
    assertEquals(stack.pop(),"third in");
    assertEquals(stack.peek(),"second in");
  }
}