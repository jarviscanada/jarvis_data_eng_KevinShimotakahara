package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintLetterWithNumberTest {

  @Test
  public void printLetterWithNumber() {
    PrintLetterWithNumber printer  = new PrintLetterWithNumber();
    assertEquals("a1c3Z52",printer.printLetterWithNumber("acZ"));
  }
}