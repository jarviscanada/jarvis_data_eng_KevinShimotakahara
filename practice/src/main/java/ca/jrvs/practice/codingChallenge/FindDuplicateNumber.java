package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://www.notion.so/Find-the-Duplicate-Number-f01cd8b4ec4e417bb42aba00a53341dc
 */
public class FindDuplicateNumber {
  private HashSet<Integer> set = new HashSet<>();
  public int findDuplicateNumber(int[] arr){
    //O(n) time and space complexity; HashSets have constant search time
    for(int el : arr){
      if(set.contains(el)){
        return el;
      }
      set.add(el);
    }
    throw new IllegalArgumentException("No duplicates present in input array.");
  }
  public int findDuplicateNumberSort(int[] arr){
    //Sorting is O(nlog(n)) time complexity
    Arrays.sort(arr);
    int prev = arr[0];
    //O(n) complexity
    for(int i = 1; i < arr.length; i++){
      if(arr[i] == prev){
        return prev;
      }
      prev = arr[i];
    }
    throw new IllegalArgumentException("No duplicates present in input array.");
  }
}
