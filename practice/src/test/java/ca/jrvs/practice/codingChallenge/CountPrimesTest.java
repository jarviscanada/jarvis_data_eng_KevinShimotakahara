package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountPrimesTest {

  @Test
  public void countPrimes() {
    CountPrimes countPrimes = new CountPrimes();
    assertEquals(4,countPrimes.countPrimes(10));
  }
}