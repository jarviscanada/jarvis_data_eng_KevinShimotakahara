package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MissingNumberTest {

  @Test
  public void findMissingNumber() {
    Integer[] arr = {0,1,2,3,5};
    MissingNumber finder = new MissingNumber();
    assertEquals(4,(int) finder.findMissingNumber(arr));
    assertEquals(4,(int) finder.findMissingNumberGauss(arr));
    assertEquals(4,(int) finder.findMissingNumberSum(arr));
  }
}