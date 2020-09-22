package ca.jrvs.practice.dataStructure.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class JBSTreeTest {
    @Test
    public void chunk(){
      //it's easier to test all methods in one test function so you don't have to rebuild the tree
      JBSTree<Integer> tree = new JBSTree<>(Integer::compareTo);

      //test basic insert
      assertEquals(tree.insert(3),(Integer)3);
      assertEquals(tree.insert(4),(Integer)4);
      assertEquals(tree.insert(5),(Integer)5);
      assertEquals(tree.insert(1),(Integer)1);
      tree.insert(16);

      //test removing node with one child and preOrder
      tree.remove(4);
      Object[] result = tree.preOrder();
      Integer[] expected = {3,1,5,16};
      assertArrayEquals(result,expected);
      //moving object 4 to left child of 5
      tree.insert(4);
      //test basic removal of leaf node and inOrder
      tree.remove(1);
      Object[] result2 = tree.inOrder();
      Integer[] expected2 = {3,4,5,16};
      assertArrayEquals(result2,expected2);

      //make things interesting
      tree.insert(10);
      tree.insert(17);
      tree.insert(8);
      tree.insert(9);

      //test remove node with 2 children and postOrder
      tree.remove(5);
      Object[] result3 = tree.postOrder();
      Integer[] expected3 = {4,9,10,17,16,8,3};
      assertArrayEquals(result3,expected3);

      //test search
      assertEquals(tree.search(10),(Integer)10);

      //test findHeight
      assertEquals(tree.findHeight(),5);
    }
}