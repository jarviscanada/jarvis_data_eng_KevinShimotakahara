package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddBinaryTest {

  @Test
  public void addBinary() {
    String a = "110010";
    String b = "10111";
    String expected = "1001001";
    AddBinary ab = new AddBinary();
    assertEquals(expected,ab.addBinary(a,b));
  }
}