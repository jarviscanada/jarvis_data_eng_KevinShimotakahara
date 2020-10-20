package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FindLargestTest {
  private FindLargest finder;
  private Integer[] arr;
  @Before
  public void init(){
    finder = new FindLargest();
    arr = new Integer[] {1,2,3,4,5};
  }
  @Test
  public void findLargest1() {
    assertEquals((int) finder.findLargest1(arr),5);
  }

  @Test
  public void findLargest2() {
    assertEquals((int) finder.findLargest2(arr),5);
  }

  @Test
  public void findLargest3() {
    assertEquals((int) finder.findLargest3(arr),5);
  }
}