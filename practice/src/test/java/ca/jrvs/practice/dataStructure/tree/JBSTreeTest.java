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

      thing3 = tree.insert(16);
      tree.remove(4);
      Object[] otherthing = tree.preOrder();
      thing3 = tree.insert(4);
      tree.remove(1);
      otherthing = tree.preOrder();
      tree.insert(10);
      tree.insert(17);
      tree.insert(8);
      tree.insert(9);
      tree.remove(5);
      otherthing = tree.preOrder();
      System.out.println(otherthing.toString());
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