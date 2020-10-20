package ca.jrvs.practice.codingChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ticket URL: https://www.notion.so/Check-if-a-String-contains-only-digits-53fddbded0bc4c1b8baab00129ff1dff
 */
public class CheckIfStringOnlyContainsDigits {
  public boolean checkIfStringOnlyContainsDigitsASCII(String string){
    char[] arr = string.toCharArray();
    //O(n) complexity
    for(char element : arr){
      if((int) element < 48 || (int) element > 57)
        return false;
    }
    return true;
  }

  public boolean checkIfStringOnlyContainsDigitsJavaAPI(String string){
    try{
      //Uses parseInt method, which has O(n) complexity (loops through each character of string)
      Integer.valueOf(string);
    } catch(NumberFormatException e){
      return false;
    }
    return true;
  }

  public boolean checkIfStringOnlyContainsDigitsRegex(String string){
    Pattern pattern = Pattern.compile("^\\d*$",Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(string);
    //matcher.find() calls search(), which is an O(n) method.
    //this is the slowest approach because the .find() method calls many different methods
    //of complexity O(n) or less.
    return matcher.find();
  }
}
