package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestCommonPrefixTest {

  @Test
  public void longestCommonPrefix() {
    String[] input = {"flower","flow","flight"};
    String expected = "fl";
    LongestCommonPrefix lcp = new LongestCommonPrefix();
    assertEquals(expected,lcp.longestCommonPrefix(input));
  }
}