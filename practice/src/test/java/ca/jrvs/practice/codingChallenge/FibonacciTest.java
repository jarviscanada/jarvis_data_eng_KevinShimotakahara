package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {

  @Test
  public void numDistinctWays() {
    Fibonacci fib = new Fibonacci();
    assertEquals(5, fib.numDistinctWaysRecursive(4));
    assertEquals(1, fib.numDistinctWaysRecursive(1));
    assertEquals(8, fib.numDistinctWaysRecursive(5));

    assertEquals(5, fib.numDistinctWaysDP(4));
    assertEquals(1, fib.numDistinctWaysDP(1));
    assertEquals(8, fib.numDistinctWaysDP(5));
  }
}