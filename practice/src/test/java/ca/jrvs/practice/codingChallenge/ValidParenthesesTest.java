package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidParenthesesTest {

  @Test
  public void isValid() {
    ValidParentheses vp = new ValidParentheses();
    assertTrue(vp.isValid(""));
    assertTrue(vp.isValid("{[()]}"));
    assertTrue(vp.isValid("()[]{}"));
    assertFalse(vp.isValid("([)]"));
    assertFalse(vp.isValid("("));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isValidExceptions() {
    ValidParentheses vp = new ValidParentheses();
    vp.isValid("()df");
  }
}