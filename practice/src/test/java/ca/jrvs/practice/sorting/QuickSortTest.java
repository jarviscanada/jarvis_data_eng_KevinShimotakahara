package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {

  @Test
  public void quickSort() {
    int[] arr = {7,2,7,8,1,11,6};
    int [] expected = {1,2,6,7,7,8,11};
    QuickSort qs = new QuickSort();
    qs.quickSort(arr,0,arr.length-1);
    assertArrayEquals(arr,expected);
  }
}