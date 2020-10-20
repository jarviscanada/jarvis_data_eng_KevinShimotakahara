package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToIntegerTest {

  @Test
  public void stringToInteger() {
    StringToInteger s2i = new StringToInteger();
    assertEquals(-1234,s2i.stringToInteger("-1234"));
    assertEquals(1234,s2i.stringToInteger("1234"));
    assertEquals(1234,s2i.stringToInteger("+1234"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenExceptionThrown_thenExpectationSatisfied() {
    StringToInteger s2i = new StringToInteger();
    s2i.stringToInteger("123456789101112");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenExceptionThrown_thenExpectationSatisfied2() {
    StringToInteger s2i = new StringToInteger();
    s2i.stringToIntegerFromScratch("123456789101112");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenExceptionThrown_thenExpectationSatisfied3() {
    StringToInteger s2i = new StringToInteger();
    s2i.stringToIntegerFromScratch("2147483648");
  }


  @Test
  public void stringToIntegerFromScratch() {
    StringToInteger s2i = new StringToInteger();
    assertEquals(-1234,s2i.stringToInteger("-1234"));
    assertEquals(1234,s2i.stringToInteger("1234"));
    assertEquals(1234,s2i.stringToInteger("+1234"));
  }
}