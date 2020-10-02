package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Queue;
import org.junit.Before;
import org.junit.Test;

public class QueueUsingStackTest {

  @Test
  public void testAllTheThings() {
    QueueUsingStack testQueue = new QueueUsingStack();
    //Approach 1
    testQueue.enqueueA1(1);
    testQueue.enqueueA1(2);
    testQueue.enqueueA1(3);
    testQueue.enqueueA1(4);

    assertEquals(testQueue.dequeueA1(),1);
    assertEquals(testQueue.dequeueA1(),2);
    assertEquals(testQueue.dequeueA1(),3);
    assertEquals(testQueue.dequeueA1(),4);

    //Approach 2
    testQueue.enqueueA2(1);
    testQueue.enqueueA2(2);
    testQueue.enqueueA2(3);
    testQueue.enqueueA2(4);

    assertEquals(testQueue.dequeueA2(),1);
    assertEquals(testQueue.dequeueA2(),2);
    assertEquals(testQueue.dequeueA2(),3);
    assertEquals(testQueue.dequeueA2(),4);
  }

}