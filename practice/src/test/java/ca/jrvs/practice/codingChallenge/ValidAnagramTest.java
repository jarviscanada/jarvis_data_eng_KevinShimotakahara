package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidAnagramTest {

  @Test
  public void validAnagram() {
    ValidAnagram va = new ValidAnagram();
    assertTrue(va.validAnagram("asdf","fdsa"));
    assertTrue(va.validAnagram("asdfff$","ff$dfsa"));
    assertFalse(va.validAnagram("asdfff","ff$dfsa"));
    assertFalse(va.validAnagram("asdfff","ff$dsa"));
  }

  @Test
  public void validAnagramLinearTime() {
    ValidAnagram va = new ValidAnagram();
    assertTrue(va.validAnagramLinearTime("asdf","fdsa"));
    assertTrue(va.validAnagramLinearTime("asdfff$","ff$dfsa"));
    assertFalse(va.validAnagramLinearTime("asdfff","ff$dfsa"));
    assertFalse(va.validAnagramLinearTime("asdfff","ff$dsa"));
  }
}