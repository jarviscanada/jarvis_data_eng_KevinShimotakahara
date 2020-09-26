package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoSumTest {

  @Test
  public void twoSumA1() {
    int[] nums = {1,2,3,4};
    TwoSum ts = new TwoSum();
    int[] expected = {0,3};
    int[] result = ts.twoSumA1(nums,5);
    assertArrayEquals(expected,result);
  }

  @Test
  public void twoSumA2() {
    int[] nums = {1,2,3,4};
    TwoSum ts = new TwoSum();
    int[] expected = {0,3};
    assertArrayEquals(expected,ts.twoSumA2(nums,5));
  }

  @Test
  public void twoSumA3() {
    int[] nums = {1,2,3,4};
    TwoSum ts = new TwoSum();
    int[] expected = {2,1};
    int[] result = ts.twoSumA3(nums,5);
    assertArrayEquals(expected,result);
  }
}