package ca.jrvs.practice.dataStructure.list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ListIterator;

public class LinkedListAPIs {

  public static void main(String[] args) {

    LinkedList<String> ll = new LinkedList<>();

    ll.addFirst("Add to beginning of list");
    ll.addFirst("Do it again");
    ll.addLast("Add to end of list");

    ll.add(2,"Inject into index 2 of list");

    //create an array with ll
    String[] b = {"one","two","three"};
    String[] a = ll.toArray(b);

    //print array
    System.out.println(Arrays.toString(a));
    //print linked list
    System.out.println(ll.toString());
    //print b
    System.out.println(Arrays.toString(b));

    String thing = ll.get(3);

    //Demonstrate that thing and output of set point to same location in heap,
    //thus implying that set method returns the value you are replacing
    System.out.println(thing == ll.set(3,"Changing what I got"));

    //sort elements by lexographical order
    ll.sort(Comparator.naturalOrder());
    System.out.println(ll.toString());

    //use iterator
    for(Iterator<String> it = ll.iterator(); it.hasNext(); ){
      System.out.println(it.next());
    }
  }
}
