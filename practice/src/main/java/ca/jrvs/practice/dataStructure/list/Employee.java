package ca.jrvs.practice.dataStructure.list;

public class Employee implements Comparable<Employee> {
  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee() {
  }

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  @Override
  public int compareTo(Employee o) {
    int diff = this.getAge() - o.getAge();
    if(diff < 0){
      return -1;
    }else if(diff > 0){
      return 0;
    }else{
      long diff2 = this.getSalary() - o.getSalary();
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