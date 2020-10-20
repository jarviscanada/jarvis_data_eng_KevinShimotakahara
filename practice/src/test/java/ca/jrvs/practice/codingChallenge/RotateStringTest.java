package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  @Test
  public void rotateString() {
    RotateString rs = new RotateString();
    assertTrue(rs.rotateString("asdf","asdf"));
    assertTrue(rs.rotateString("afsdf","fsdfa"));
    assertFalse(rs.rotateString("fsdf","fsdfa"));
    assertFalse(rs.rotateString("afsaf","fsdfa"));
  }
}