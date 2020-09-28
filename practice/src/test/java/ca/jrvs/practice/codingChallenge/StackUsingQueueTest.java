package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackUsingQueueTest {

  @Test
  public void pushPopA1() {
    StackUsingQueue stack = new StackUsingQueue();
    stack.pushA1(1);
    stack.pushA1(2);
    stack.pushA1(3);

    assertEquals(stack.popA1(),3);
    assertEquals(stack.popA1(),2);
    assertEquals(stack.popA1(),1);
  }

  @Test
  public void pushPopA2() {
    StackUsingQueue stack = new StackUsingQueue();
    stack.pushA2(1);
    stack.pushA2(2);
    stack.pushA2(3);

    assertEquals(stack.popA2(),3);
    assertEquals(stack.popA2(),2);
    assertEquals(stack.popA2(),1);
  }
}