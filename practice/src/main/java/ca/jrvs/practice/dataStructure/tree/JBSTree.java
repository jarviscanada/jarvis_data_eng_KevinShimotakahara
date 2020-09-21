package ca.jrvs.practice.dataStructure.tree;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;
  private JBSTree.Node<E> root;
  private boolean start = true;
  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (root == null) {
      root = new JBSTree.Node<>(object, null);
      return object;
    }
    JBSTree.Node<E> current = root;
    while (current != null) {
      switch (comparator.compare(current.getValue(), object)) {
        case 0:
          //object already exists
          throw new IllegalArgumentException("Error: "
              + "You tried to insert a value that is already in the tree.");
        case 1:
          //object is less than object stored in current node
          if (current.left != null) {
            //keep digging
            current = current.left;
          }else{
            //you found the insertion point
            current.left = new JBSTree.Node<E>(object,current);
            return object;
          }
        case -1:
          //object is greater than object stored in current node
          if(current.right != null){
            //keep digging
            current = current.right;
          }else{
            //you found the insertion point
            current.right = new JBSTree.Node<E>(object,current);
            return object;
          }
      }
    }
      return object;
  }

    /**
     * Search and return an object, return null if not found
     *
     * @param object to be found
     * @return the object if exists or null if not
     */
    @Override
    public E search (E object){
      //JBSTree.Node<E>
      if (root == null)
        return null;
      return (E) root.search(object, comparator, searchOptions.VALUE);
    }

    public JBSTree.Node<E> searchNode (E object){
      //JBSTree.Node<E>
      if (root == null)
        return null;
      return (Node<E>) root.search(object, comparator,searchOptions.NODE);
    }

    /**
     * Remove an object from the tree.
     *
     * @param object to be removed
     * @return removed object
     * @throws IllegalArgumentException if the object not exists
     */
    @Override
    public E remove (E object){
      //find object you want to remove
      JBSTree.Node<E> result = searchNode(object);
      if (result == null)
        throw new IllegalArgumentException(object + " doesn't exist in tree.");
      if(result.right == null && result.left == null){
        //easy remove case
        result = null;
      }else if(result.right == null ^ result.left == null){
        //medium remove case (object has 1 child)
        if (result.parent == null){
          //if parent is single child root, then just set result to root to delete root
          root = result;
        }
        if (result.right != null){
          //check if result is left child of parent or right child of parent
          switch(comparator.compare(result.parent.value,result.value)){
            case 1:
              //parent value is greater than result value, meaning result is left child of parent
              result.parent.left = result.right;
              result.right.parent = result.parent;
            case -1:
              //parent value is less than result value, meaning result is right child of parent
              result.parent.right = result.right;
              result.right.parent = result.parent;
          }
        }else{
          //check if result is left child of parent or right child of parent
          switch(comparator.compare(result.parent.value,result.value)){
            case 1:
              //parent value is greater than result value, meaning result is left child of parent
              result.parent.left = result.left;
              result.left.parent = result.parent;
            case -1:
              //parent value is less than result value, meaning result is right child of parent
              result.parent.right = result.left;
              result.left.parent = result.parent;
          }
        }
      }else{
        //hard remove case (result has two children)
        //STEP 1: find successor node
        JBSTree.Node<E> successor = result.right;
        while(successor.left != null){
          successor = successor.left;
        }
        //successor may have right child; but it's easy to find right child new parent
        if(successor.right != null) {
          successor.parent.left = successor.right;
          successor.right.parent = successor.parent;
        }
        //STEP 2: re-jig all connections so that successor is where result is being removed from
        successor.parent = result.parent;
        successor.left = result.left;
        successor.right = result.right;
        successor.left.parent = successor;
        successor.right.parent = successor;

        if (result.parent == null){
          root = successor;
        }else{
          //check if result is left child of parent or right child of parent
          switch(comparator.compare(result.parent.value,result.value)){
            case 1:
              //parent value is greater than result value, meaning result is left child of parent
              result.parent.left = successor;
            case -1:
              //parent value is less than result value, meaning result is right child of parent
              result.parent.right = successor;
          }
        }
      }
      return object;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in pre-order
     */
    @Override
    public E[] preOrder () {
      JBSTree.Node<E> current = root;
      ArrayList<E> outputs = new ArrayList<>();

      //check if root is null
      if (current == null) return (E[]) outputs.toArray();

      //run recursive center left right traversal of trees
      clr(current,outputs);

      return (E[]) outputs.toArray();
    }

    public void clr(JBSTree.Node<E> current, ArrayList<E> outputs){
      if (current != null){
        outputs.add(current.getValue());
      }
      //deal with left branch
      if (current.left != null){
        if (current.left.left == null && current.left.right == null){
          //left node is a leaf node; can add its value to current function call
          outputs.add(current.left.getValue());
        }else{
          //left node is not a leaf node; must call another clr
          clr(current.left,outputs);
        }
      }
      //deal with right branch
      if (current.right != null){
        if (current.right.left == null && current.right.right == null){
          //right node is a leaf node; can add its value to current function call
          outputs.add(current.right.getValue());
        }else{
          //right node is not a leaf node; must call another clr
          clr(current.right,outputs);
        }
      }
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in-order
     */
    @Override
    public E[] inOrder () {
      return null;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects pre-order
     */
    @Override
    public E[] postOrder () {
      return null;
    }

    /**
     * traverse through the tree and find out the tree height
     * @return height
     * @throws NullPointerException if the BST is empty
     */
    @Override
    public int findHeight () {
      return 0;
    }

    static final class Node<E> {

      E value;
      Node<E> left;
      Node<E> right;
      Node<E> parent;

      public Node(E value, Node<E> parent) {
        this.value = value;
        this.parent = parent;
      }

      public Object search(E object, Comparator<E> comparator, searchOptions option) {
        switch (comparator.compare(value, object)) {
          case 0:
            switch (option) {
              case VALUE:
                return value;
              case NODE:
                return this;
            }

          case 1:
            if (left == null) {
              //reached end of tree and did not find
              return null;
            } else {
              //keep digging
              return left.search(object, comparator, option);
            }
          case -1:
            if (right == null) {
              //reached end of tree and did not find
              return null;
            } else {
              //keep digging
              return right.search(object, comparator, option);
            }
          default:
            return null;
        }
      }

      public E getValue() {
        return value;
      }

      public void setValue(E value) {
        this.value = value;
      }

      public Node<E> getLeft() {
        return left;
      }

      public void setLeft(Node<E> left) {
        this.left = left;
      }

      public Node<E> getRight() {
        return right;
      }

      public void setRight(Node<E> right) {
        this.right = right;
      }

      public Node<E> getParent() {
        return parent;
      }

      public void setParent(Node<E> parent) {
        this.parent = parent;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (!(o instanceof Node)) {
          return false;
        }
        Node<?> node = (Node<?>) o;
        return getValue().equals(node.getValue()) &&
            Objects.equals(getLeft(), node.getLeft()) &&
            Objects.equals(getRight(), node.getRight()) &&
            getParent().equals(node.getParent());
      }

      @Override
      public int hashCode() {
        return Objects.hash(getValue(), getLeft(), getRight(), getParent());
      }
    }
}
