package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchRecursion() {
    Integer[] thing = {1,2,4,6,8,15,69,420};
    BinarySearch bs = new BinarySearch();
    Optional<Integer> expected = Optional.of(2);
    assertEquals(bs.binarySearchRecursion(thing,4),expected);

    Optional<Integer> expectedEmpty = Optional.empty();
    assertEquals(bs.binarySearchRecursion(thing,400),expectedEmpty);
  }

  @Test
  public void binarySearchIteration() {
    Integer[] thing = {1,2,4,6,8,15,69,420};
    BinarySearch bs = new BinarySearch();
    Optional<Integer> expected = Optional.of(2);
    assertEquals(bs.binarySearchIteration(thing,4),expected);

    Optional<Integer> expectedEmpty = Optional.empty();
    assertEquals(bs.binarySearchIteration(thing,400),expectedEmpty);
  }
}