package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveElementTest {

  @Test
  public void removeElement() {
    int[] arr = {3,2,2,3};
    int[] expected = {2,2};
    RemoveElement rm = new RemoveElement();
    rm.removeElement(arr,3);
    assertTrue(expected[0] == arr[0] && expected[1] == arr[1]);
  }
}