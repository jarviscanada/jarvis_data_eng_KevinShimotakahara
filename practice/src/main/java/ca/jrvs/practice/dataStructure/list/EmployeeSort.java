package ca.jrvs.practice.dataStructure.list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class EmployeeSort{
  public static void main(String[] args) {
    LinkedList<Employee> ll = new LinkedList<>();

    ll.addFirst(new Employee(1,"Bojack",51, 1000000));
    ll.addFirst(new Employee(2,"Diane",32, 60000));
    ll.addFirst(new Employee(3,"Caroline",51, 100000));
    ll.addFirst(new Employee(4,"Todd",28, 0));

    ll.sort(Comparator.naturalOrder());
    //check if worked
    ll.stream().forEach(e -> System.out.println(e.getName()));

    ll.clear();

    ll.addFirst(new Employee(1,"Bojack",51, 1000000));
    ll.addFirst(new Employee(2,"Diane",32, 60000));
    ll.addFirst(new Employee(3,"Caroline",51, 100000));
    ll.addFirst(new Employee(4,"Todd",28, 0));

    ll.sort(new EmployeeComparator());
    ll.stream().forEach(e -> System.out.println(e.getName()));
  }
}
