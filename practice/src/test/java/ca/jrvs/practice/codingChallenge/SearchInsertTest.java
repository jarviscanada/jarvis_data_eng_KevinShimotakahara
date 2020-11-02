package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchInsertTest {

  @Test
  public void searchInsert() {
    int[] input = {1,3,5,6};
    SearchInsert si = new SearchInsert();
    assertEquals(4,si.searchInsert(input,7));
  }
}