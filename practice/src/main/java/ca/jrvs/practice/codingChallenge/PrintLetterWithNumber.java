package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/Print-letter-with-number-f8eed1152c1e4c7bb0e5ee0d3e9d0cdd
 */
public class PrintLetterWithNumber {
  private Map<Character,Integer> map = new HashMap<>();

  public PrintLetterWithNumber(){
    //Building map is O(1) complexity for time and space
    for(int i = 1; i <= 26; i++) {
      map.put((char) (i + 96),i);
    }
    for(int i = 27; i <= 52; i++) {
      map.put((char) (i + 38),i);
    }
  }

  public String printLetterWithNumber(String string){
    StringBuilder sb = new StringBuilder();
    char[] arr = string.toCharArray();
    //O(n) complexity to loop through each character in string
    for(char character : arr){
      sb.append(character).append(map.get(character));
    }
    return sb.toString();
  }
}
