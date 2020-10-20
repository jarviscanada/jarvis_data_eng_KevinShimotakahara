package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {

  @Test
  public void swapTwoNumbers() {
    int[] arr = {30,2};
    int[] arr2 = {2,30};
    SwapTwoNumbers swapper = new SwapTwoNumbers();
    assertArrayEquals(arr2,swapper.swapTwoNumbers(arr));
    arr = new int[]{30,2};
    assertArrayEquals(arr2,swapper.swapTwoNumbersBitManip(arr));
  }
}