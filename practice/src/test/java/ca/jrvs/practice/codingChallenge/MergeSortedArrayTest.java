package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortedArrayTest {

  @Test
  public void mergeSortedArray() {
    int[] arr1 = {1,2,3,0,0,0};
    int[] arr2 = {2,5,6};
    int[] expected = {1,2,2,3,5,6};
    MergeSortedArray merger = new MergeSortedArray();
    merger.mergeSortedArray(arr1,3,arr2,3);
    assertArrayEquals(expected,arr1);
  }
}