package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrStrTest {

  @Test
  public void strStr() {
    String haystack = "hello", needle = "ll";
    StrStr strstr = new StrStr();
    assertEquals(2,strstr.strStr(haystack,needle));
  }
}