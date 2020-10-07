package ca.jrvs.practice.dataStructure.list;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
  @Override
  public int compare(Employee o1, Employee o2) {
    int diff = o1.getAge() - o2.getAge();
    if(diff < 0){
      return -1;
    }else if(diff > 0){
      return 0;
    }else{
      long diff2 = o1.getSalary() - o2.getSalary();
      if(diff2 < 0){
        return -1;
      }else if(diff2 > 0){
        return 1;
      }else{
        return 0;
      }
    }
  }
}
