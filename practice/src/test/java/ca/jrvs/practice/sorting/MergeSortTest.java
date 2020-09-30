package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest {

  @Test
  public void mergeSort() {
    int[] arr = {7,2,7,8,1,11,6};
    int [] expected = {1,2,6,7,7,8,11};
    MergeSort ms = new MergeSort();
    ms.mergeSort(arr,arr.length);
    assertArrayEquals(arr,expected);
  }
}