package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * Ticket URL: https://www.notion.so/String-to-Integer-atoi-437dcec88b4e41129e20221184ed5076
 */
public class StringToInteger {
  Map<Integer,Integer> asciiInt = new HashMap();
  int radix = 10;

  public StringToInteger(){
    //populate asciiInt map
    for(int i = 0; i <= 9; i++){
      asciiInt.put(48+i,i);
    }
  }

  public int stringToInteger(String string){
    int result;
    //use parseInt method of Integer class; O(n), n = string length
    try {
      result = Integer.parseInt(string);
    }
    catch (NumberFormatException e)
    {
      throw new IllegalArgumentException("Cannot convert string to int. Make sure string only contains numbers; negative symbol is okay too.");
    }
    return result;
  }

  public int stringToIntegerFromScratch(String string) {
    boolean negative = false;
    int i = 0;
    int ascii;
    long result = 0; //initialize as long in case value > 2^31/2 - 1
    if (string.length() > 11)
      throw new IllegalArgumentException("Your number is too large to fit into int.");
    if (string.charAt(0) == '-') {
      negative = true;
      i++;
    } else if (string.charAt(0) != '+') {
      throw new IllegalArgumentException("Cannot convert string to int. Use only 0-9, +/-");
    } else
      i++;
    //O(n) complexity; static hash table is being used, thus guaranteed O(1) search
    for(;i < string.length(); i++){
      ascii = (int) string.charAt(i);
      if(asciiInt.containsKey(ascii)){
        result += Math.pow(radix,string.length()-i)*asciiInt.get(ascii);
      } else {
        throw new IllegalArgumentException("Cannot convert string to int. Use only 0-9, +/-");
      }
    }

    if (result > Math.pow(2,31)-1)
      throw new IllegalArgumentException("The integer value " + result + " is too large to fit into int primitive type.");

    if (negative)
      result *= -1;
    return (int) result;
  }
}
