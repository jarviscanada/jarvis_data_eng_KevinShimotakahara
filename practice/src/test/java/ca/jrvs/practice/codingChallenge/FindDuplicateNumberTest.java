package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindDuplicateNumberTest {

  @Test
  public void fundDuplicateNumber() {
    int[] nums = {3,10,20,3,4,5};
    FindDuplicateNumber finder = new FindDuplicateNumber();
    assertEquals(3,finder.findDuplicateNumber(nums));
    assertEquals(3,finder.findDuplicateNumberSort(nums));
  }
}