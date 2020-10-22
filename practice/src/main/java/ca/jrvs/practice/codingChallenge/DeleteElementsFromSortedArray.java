package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/Remove-Duplicates-from-Sorted-Array-2fa8186320dc418f9efeee97caccfd1e
 */
public class DeleteElementsFromSortedArray {
  public int deleteElementsFromSortedArray(int[] arr){
    int marginPointer = -1;
    //O(n) complexity, O(1) space complexity
    for(int i = 1; i < arr.length; i++){
      if(arr[i] == arr[i-1]) {
        if(marginPointer == -1)
          marginPointer = i;
      } else if(marginPointer != -1){
        arr[marginPointer] = arr[i];
        marginPointer++;
      }
    }
    if(marginPointer != -1)
      return marginPointer;
    return arr.length;
  }
}
