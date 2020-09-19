package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedJListTest {
  @Test(expected = NullPointerException.class)
  public void testAddException(){
    LinkedJList<String> al = new LinkedJList<>();
    al.add(null);
  }
  @Test
  public void add() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("does add work");
    assertEquals(al.get(0),"does add work");
  }

  @Test
  public void toArray() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("does toArray work");
    assertEquals(al.toArray()[0],"does toArray work");
  }

  @Test
  public void size() {
    LinkedJList<String> al = new LinkedJList<>();
    assertEquals(al.size(), 0);
    al.add("should still work");
    assertEquals(al.size(), 1);
  }

  @Test
  public void isEmpty() {
    LinkedJList<String> al = new LinkedJList<>();
    assertEquals(al.isEmpty(), true);
    al.add("should still work");
    assertEquals(al.isEmpty(), false);
  }

  @Test
  public void indexOf() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("Cats lick you to test if they should eat you when you die");
    assertEquals(al.indexOf("Cats lick you to test if they should eat you when you die"),0);
    assertEquals(al.indexOf("Just kidding it's because they love you."),-1);
  }

  @Test(expected = NullPointerException.class)
  public void testContainsException(){
    LinkedJList<String> al = new LinkedJList<>();
    al.add("oh hai");
    al.contains(null);
  }

  @Test
  public void contains() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("I never really liked Blues Clues as a child");
    assertEquals(al.contains("I never really liked Blues Clues as a child"),true);
    assertEquals(al.contains("But I sure liked Doom II: Hell on Earth"),false);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetException(){
    LinkedJList<String> al = new LinkedJList<>();
    al.get(4);
  }

  @Test
  public void get() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("writing this test code takes longer when you want to write a new string every time");
    assertEquals(al.get(0),"writing this test code takes longer when you want to write a new string every time");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveException() {
    LinkedJList<String> al = new LinkedJList<>();
    al.remove(0);
  }

  @Test
  public void remove() {
    LinkedJList<String> al = new LinkedJList<>();
    al.add("please kill me");
    assertEquals(al.remove(0),"please kill me");
  }

  @Test
  public void clear() {
    LinkedJList<String> al = new LinkedJList<>();
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