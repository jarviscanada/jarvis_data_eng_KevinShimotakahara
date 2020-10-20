package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket URL: https://www.notion.so/Two-Sum-206143e2af8a4ea8960e83842f00c312
 */
public class TwoSum {
  public int[] twoSumA1(int[] nums, int target) {
    //brute force approach is O(n^2) complexity
    for(int i = 0; i < nums.length; i++){
      for(int j = 0; j < nums.length; j++){
        if(i != j && nums[i]+nums[j] == target)
          return new int[] {i,j};
      }
    }
    return null;
  }

  public int[] twoSumA2(int[] nums, int target){
    //sorted array version
    //Sort can be as good as O(n*log(n)) if merge sort is used
    Arrays.sort(nums);

    //This is O(n*log(n)) complexity because we use one for loop O(n) and perform a binary search
    //O(log(n)) every iteration
    for(int i = 0; i < nums.length; i++){
      int j = Arrays.binarySearch(nums,target - nums[i]);
      if(j > 0){
        //A match exists, but it may be the same index as i. If it's the same index as i,
        //We need to check if the sibling indices of i contain a valid solution. This will
        //work because the array is sorted.
        if(i != j)
          return new int[] {i,j};

        if(i > 0 && nums[i-1] == nums[i] || i-1 < nums.length && nums[i+i] == nums[i]){
          return new int[] {i,j};
        }
      }
    }
    return null;
  }

  public int[] twoSumA3(int[] nums, int target){
    Map<Integer,Integer> numsMap = new HashMap<>();
    //Since searching a hashmap is O(1) complexity, this solution is O(n) due to a single for loop.
    //It is also possible to build your hashmap as you traverse the array, and doing this is
    //especially elegant because it guarantees the get command doesn't return the current element
    //your loop is on
    for(int i = 0; i < nums.length; i++){
      if(numsMap.containsKey(target - nums[i]))
        return new int[] {i,numsMap.get(target - nums[i])};
      numsMap.put(nums[i],i);
    }
    return null;
  }

}
