package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Ticket URL: https://www.notion.so/Valid-Parentheses-60305fe438f04e51884cf3cc1b98db6e
 */
public class ValidParentheses {
  Map<Character,String> openCloseMap = new HashMap<>();
  Map<Character,Character> charMatchMap = new HashMap<>();

  public ValidParentheses(){
    openCloseMap.put('(',"open");
    openCloseMap.put('{',"open");
    openCloseMap.put('[',"open");
    openCloseMap.put(')',"close");
    openCloseMap.put('}',"close");
    openCloseMap.put(']',"close");
    charMatchMap.put('(',')');
    charMatchMap.put('{','}');
    charMatchMap.put('[',']');
  }

  public boolean isValid(String s){
    //pre-loop checks/setup O(1) complexity
    if(s.length()%2 != 0)
      return false;
    if(s.length() == 0)
      return true;

    Deque<Character> stack = new LinkedList<>();
    char current = s.charAt(0);
    if(openCloseMap.containsKey(current)){
      if(openCloseMap.get(current) == "close")
        return false;
      stack.push(current);
    } else {
      throw new IllegalArgumentException("Invalid parentheses string. Use only {}[]()");
    }

    //traverse through string chars with stack logic; every closing bracket encountered
    //should have its corresponding opening bracket at the top of the stack. O(n)
    for(int i = 1; i < s.length(); i++){
      current = s.charAt(i);
      if(openCloseMap.containsKey(current)){
        if(openCloseMap.get(current) == "close"){
          if(charMatchMap.get(stack.peek()) == current){
            //found a valid parenthesis
            stack.pop();
          } else{
            //found an invalid parenthesis
            return false;
          }
        } else {
          //add open bracket to the stack
          stack.push(current);
        }
      } else {
        throw new IllegalArgumentException("Invalid parentheses string. Use only {}[]()");
      }
    }
    return true;
  }
}
