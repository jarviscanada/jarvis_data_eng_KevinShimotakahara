package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayJListTest {
  @Test(expected = NullPointerException.class)
  public void testAddException(){
    ArrayJList<String> al = new ArrayJList<>();
    al.add(null);
  }

  @Test
  public void add() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("does add work");
    assertEquals(al.get(0),"does add work");
  }

  @Test
  public void toArray() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("does toArray work");
    assertEquals(al.toArray()[0],"does toArray work");
  }

  @Test
  public void size() {
    ArrayJList<String> al = new ArrayJList<>();
    assertEquals(al.size(), 0);
    al.add("should still work");
    assertEquals(al.size(), 1);
  }

  @Test
  public void isEmpty() {
    ArrayJList<String> al = new ArrayJList<>();
    assertEquals(al.isEmpty(), true);
    al.add("should still work");
    assertEquals(al.isEmpty(), false);
  }

  @Test
  public void indexOf() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("Cats lick you to test if they should eat you when you die");
    assertEquals(al.indexOf("Cats lick you to test if they should eat you when you die"),0);
    assertEquals(al.indexOf("Just kidding it's because they love you."),-1);
  }

  @Test(expected = NullPointerException.class)
  public void testContainsException(){
    ArrayJList<String> al = new ArrayJList<>();
    al.add("oh hai");
    al.contains(null);
  }

  @Test
  public void contains() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("I never really liked Blues Clues as a child");
    assertEquals(al.contains("I never really liked Blues Clues as a child"),true);
    assertEquals(al.contains("But I sure liked Doom II: Hell on Earth"),false);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetException() {
    ArrayJList<String> al = new ArrayJList<>();
    al.remove(0);
  }

  @Test
  public void get() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("writing this test code takes longer when you want to write a new string every time");
    assertEquals(al.get(0),"writing this test code takes longer when you want to write a new string every time");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveException() {
    ArrayJList<String> al = new ArrayJList<>();
    al.remove(0);
  }

  @Test
  public void remove() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("please kill me");
    assertEquals(al.remove(0),"please kill me");
  }

  @Test
  public void clear() {
    ArrayJList<String> al = new ArrayJList<>();
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");
    al.add("the crazy cat lady bought a new cat.");

    al.clear();
    assertEquals(al.size(),0);
  }
}