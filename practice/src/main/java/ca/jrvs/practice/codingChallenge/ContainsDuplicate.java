package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

/**
 * https://www.notion.so/Contains-Duplicate-9c2a9fb1f8c84fce900756fea45c27b2
 */
public class ContainsDuplicate {
  public boolean containsDuplicate(int[] arr){
    //Space complexity is O(n) because we are making hash set with max
    // cardinality of arr.length
    HashSet<Integer> set = new HashSet<>();
    //O(n)
    for(int num : arr){
      //O(1) provided decent load factor
      if(set.contains(num))
        return true;
      //O(1) operation
      set.add(num);
    }
    return false;
  }
}
