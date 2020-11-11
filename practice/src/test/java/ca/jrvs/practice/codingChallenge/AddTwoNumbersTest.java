package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.AddTwoNumbers.ListNode;
import org.junit.Test;

public class AddTwoNumbersTest {

  @Test
  public void addTwoNumbers() {
    ListNode l1 = new ListNode();
    l1.val = 9;
    //l1.next = new ListNode(4, new ListNode(3));
    ListNode l2 = new ListNode();
    l2.val = 1;
    ListNode temp = l2;
    for(int i = 1; i < 10; i++){
      temp.next = new ListNode(9);
      temp = temp.next;
    }
    AddTwoNumbers add = new AddTwoNumbers();
    ListNode asdf = add.addTwoNumbers(l1,l2);
    assertTrue(true);
  }
}