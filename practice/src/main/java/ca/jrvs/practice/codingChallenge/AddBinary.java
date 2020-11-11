package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;

public class AddBinary {
  public String addBinary(String a, String b) {
    boolean carryOver = false;
    String largerString;
    String smallerString;
    if(a.length() >= b.length()){
      largerString = a;
    }else{
      largerString = b;
    }
    if(largerString.equals(b)){
      smallerString = a;
    }else{
      smallerString = b;
    }

    LinkedList<String> stack = new LinkedList<>();
    int delta = largerString.length() - smallerString.length();
    for(int i = smallerString.length() - 1; i >= 0 ; i--){
      if(!carryOver){
        if(smallerString.charAt(i) == '0' && largerString.charAt(i+delta) == '0'){
          stack.push("0");
        }else if(smallerString.charAt(i) == '1' && largerString.charAt(i+delta) == '1'){
          stack.push("0");
          carryOver = true;
        }else{
          stack.push("1");
        }
      }else{
        if(smallerString.charAt(i) == '0' && largerString.charAt(i) == '0'){
          stack.push("1");
          carryOver = false;
        }else if(smallerString.charAt(i) == '1' && largerString.charAt(i) == '1'){
          stack.push("1");
        }else{
          stack.push("0");
        }
      }
    }

    for(int i = delta - 1; i >= 0; i--){
      if(carryOver){
        if(largerString.charAt(i) == '0'){
          stack.push("1");
          carryOver = false;
        } else{
          stack.push("0");
        }

      }else{
        stack.push(largerString.substring(i,i+1));
      }
    }

    if(carryOver)
      stack.push("1");

    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()){
      sb.append(stack.pop());
    }
    return sb.toString();
  }
}
