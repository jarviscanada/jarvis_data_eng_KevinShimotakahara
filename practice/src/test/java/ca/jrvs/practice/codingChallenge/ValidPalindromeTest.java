package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValidPalindromeTest {
  private String t1 = "safaf";
  private String t2 = "tacocat";
  private String t3 = "asdgf5h?45(0";
  private String t4 = "aA";
  private String t5 = "aa";
  private String t6 = "";

  @Test
  public void validPalindromeA1() {
    ValidPalindrome vp = new ValidPalindrome();
    assertFalse(vp.validPalindromeA1(t1));
    assertTrue(vp.validPalindromeA1(t2));
    assertFalse(vp.validPalindromeA1(t3));
    assertFalse(vp.validPalindromeA1(t4));
    assertTrue(vp.validPalindromeA1(t5));
    assertTrue(vp.validPalindromeA1(t6));
  }

  @Test
  public void validPalindromeA2() {
    ValidPalindrome vp = new ValidPalindrome();
    assertFalse(vp.validPalindromeA2(t1));
    assertTrue(vp.validPalindromeA2(t2));
    vp.validPalindromeA2(t3);
    assertFalse(vp.validPalindromeA2(t3));
    assertFalse(vp.validPalindromeA2(t4));
    assertTrue(vp.validPalindromeA2(t5));
    assertTrue(vp.validPalindromeA2(t6));
  }
}