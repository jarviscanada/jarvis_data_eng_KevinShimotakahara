package ca.jrvs.practice.codingChallenge;

public class SearchInsert {
  public int searchInsert(int[] nums, int target) {
    if(nums.length == 0)
      return 1;
    if(nums.length == 1 && nums[0] == target)
      return 0;
    int hi = nums.length - 1, lo = 0, mid = -1;
    //O(log(n)) time complexity
    while(lo != hi){
      mid = (lo+hi)/2;
      if(nums[mid] == target)
        return mid;
      if(nums[mid] > target){
        hi = mid - 1;
      } else{
        lo = mid + 1;
      }
    }
    mid = lo;
    if(nums[mid] > target)
      return mid;
    return mid + 1;
  }
}
