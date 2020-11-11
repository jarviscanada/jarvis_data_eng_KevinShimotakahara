package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/Merge-Sorted-Array-c96dd01cdd9a42c0981648b8c04c53f8
 */
public class MergeSortedArray {
  public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
    int i = nums1.length;
    //O(n+m) complexity
    while(m > 0 || n > 0){
      switch (m*n){
        case 0:
          if(m>0)
            nums1[--i] = nums1[--m];
          if(n>0)
            nums1[--i] = nums2[--n];
          break;
        default:
          if(nums1[m-1] > nums2[n-1] || n == 0)
            nums1[--i] = nums1[--m];
          nums1[--i] = nums2[--n];
      }
    }
  }
}
