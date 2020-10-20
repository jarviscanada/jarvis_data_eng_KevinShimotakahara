package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;

/**
 * Ticket URL: https://www.notion.so/Find-Largest-Smallest-0d59a05b6d714210ab284495e2af730c
 */
public class FindLargest {

  public Integer findLargest1(Integer[] arr){
    Integer candidate = arr[0];
    //O(n) complexity
    for(int i = 1; i < arr.length; i++){
      if(arr[i] > candidate)
        candidate = arr[i];
    }
    return candidate;
  }
  public Integer findLargest2(Integer[] arr){
    Integer[] candidate = {arr[0]};
    //O(n) complexity
    Arrays.stream(arr).forEach(integer -> {
      if(integer > candidate[0])
        candidate[0] = integer;
    });
    return candidate[0];
  }

  public Integer findLargest3(Integer[] arr){
    //O(n) complexity
    return Collections.max(Arrays.asList(arr));
  }
}
