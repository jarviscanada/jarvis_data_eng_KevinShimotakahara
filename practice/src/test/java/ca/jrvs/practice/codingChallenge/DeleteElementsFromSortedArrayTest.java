package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeleteElementsFromSortedArrayTest {

  @Test
  public void deleteElementsFromSortedArray() {
    int[] arr = {2,2,3,3};
    int expected = 2;
    DeleteElementsFromSortedArray deleter = new DeleteElementsFromSortedArray();
    assertEquals(deleter.deleteElementsFromSortedArray(arr),2);
    assertEquals(2,arr[0]);
    assertEquals(3,arr[1]);
  }
}