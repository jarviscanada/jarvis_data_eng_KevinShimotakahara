package ca.jrvs.practice.dataStructure.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class JBSTreeTest {
    @Test
    public void firstchunk(){
      JBSTree<Integer> tree = new JBSTree<>(Integer::compareTo);
      int thing = tree.insert(3);
      int thing2 = tree.insert(4);
      int thing3 = tree.insert(5);
      assertEquals(thing,3);
      assertEquals(thing2,4);
      assertEquals(thing3,5);
      assertEquals(tree.insert(1),(Integer)1);
      Object[] otherthing = tree.preOrder();
      System.out.println(otherthing[0]);
      System.out.println(otherthing[1]);
      System.out.println(otherthing[2]);
      System.out.println(otherthing[3]);
    }
//  @Test
//  public void insert() {
//  }
//
//  @Test
//  public void search() {
//  }
//
//  @Test
//  public void remove() {
//  }
//
//  @Test
//  public void preOrder() {
//  }
//
//  @Test
//  public void clr() {
//  }
//
//  @Test
//  public void inOrder() {
//  }
//
//  @Test
//  public void postOrder() {
//  }
//
//  @Test
//  public void findHeight() {
//  }
}