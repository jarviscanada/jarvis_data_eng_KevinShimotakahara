package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckIfStringOnlyContainsDigitsTest {
  private CheckIfStringOnlyContainsDigits digitCheck;

  @Before
  public void init(){
    digitCheck = new CheckIfStringOnlyContainsDigits();
  }

  @Test
  public void checkIfStringOnlyContainsDigitsASCII() {
    assertTrue(digitCheck.checkIfStringOnlyContainsDigitsASCII("1234"));
    assertFalse(digitCheck.checkIfStringOnlyContainsDigitsASCII("12f34"));
  }

  @Test
  public void checkIfStringOnlyContainsDigitsJavaAPI() {
    assertTrue(digitCheck.checkIfStringOnlyContainsDigitsJavaAPI("1234"));
    assertFalse(digitCheck.checkIfStringOnlyContainsDigitsJavaAPI("12f34"));
  }

  @Test
  public void checkIfStringOnlyContainsDigitsRegex() {
    assertTrue(digitCheck.checkIfStringOnlyContainsDigitsRegex("1234"));
    assertFalse(digitCheck.checkIfStringOnlyContainsDigitsRegex("12f34"));
  }
}