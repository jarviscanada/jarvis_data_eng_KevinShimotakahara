package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Ticket URL: https://www.notion.so/Valid-Anagram-6e47efaa7bc545c7a93eedf9747fa39e
 */
public class ValidAnagram {
  public boolean validAnagram(String A, String B){
    if(A.length() != B.length())
      return false;
    //O(n*log(n)) for merge sort
    char[] a = A.toCharArray();
    char[] b = B.toCharArray();
    Arrays.sort(a);
    Arrays.sort(b);
    //O(n) operation
    return Arrays.equals(a,b);
  }

  public boolean validAnagramLinearTime(String A, String B){
    if(A.length() != B.length())
      return false;
    //Instead of sorting, we can count character occurrences in both strings
    // and compare them
    Map<Character, Integer> occurrenceMap = new HashMap<>();
    Integer temp;
    char currentChar;
    //O(n) loop; build the hashmap containing character occurrence tallies
    for(int i = 0; i < A.length(); i++){
      currentChar = A.charAt(i);
      if(occurrenceMap.containsKey(currentChar)){
        temp = occurrenceMap.get(currentChar) + 1;
        occurrenceMap.put(currentChar,temp);
      } else {
        occurrenceMap.put(currentChar,1);
      }
      currentChar = B.charAt(i);
      if(occurrenceMap.containsKey(currentChar)){
        temp = occurrenceMap.get(currentChar) - 1;
        occurrenceMap.put(currentChar,temp);
      } else {
        occurrenceMap.put(currentChar,-1);
      }
    }
    //iterate through entryset of hashmap
    Iterator it = occurrenceMap.entrySet().iterator();
    while(it.hasNext()){
      Map.Entry pair = (Map.Entry) it.next();
      if((Integer) pair.getValue() != 0)
        return false;
    }
    return true;
  }
}
