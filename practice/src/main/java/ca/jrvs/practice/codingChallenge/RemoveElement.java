package ca.jrvs.practice.codingChallenge;

public class RemoveElement {
  public int removeElement(int[] arr, int val){
    if(arr.length == 0)
      return 0;
    int marginPointer = -1;
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == val) {
        if(marginPointer == -1){
          marginPointer = i;
        }
      } else if(marginPointer != -1){
        //move current value to marginPointer and increment marginPointer
        arr[marginPointer] = arr[i];
        marginPointer++;
      }
    }
    if(marginPointer < 0)
      return arr.length;
    return marginPointer;
  }
}