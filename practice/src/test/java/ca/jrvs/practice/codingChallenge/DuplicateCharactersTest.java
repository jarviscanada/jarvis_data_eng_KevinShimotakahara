package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicateCharactersTest {

  @Test
  public void duplicateCharacters() {
    String input = "A black cat";
    String[] expected = {"a", "c"};
    DuplicateCharacters dc = new DuplicateCharacters();
    assertArrayEquals(expected,dc.duplicateCharacters(input));
  }
}