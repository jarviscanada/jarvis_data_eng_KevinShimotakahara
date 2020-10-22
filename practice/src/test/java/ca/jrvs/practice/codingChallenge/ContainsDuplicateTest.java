package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsDuplicateTest {

  @Test
  public void containsDuplicate() {
    int[] arr = {1,3,2,4,5,33,2};
    int[] arr2 = {1,3,2,4,5,33};
    ContainsDuplicate tester = new ContainsDuplicate();
    assertTrue(tester.containsDuplicate(arr));
    assertFalse(tester.containsDuplicate(arr2));
  }
}