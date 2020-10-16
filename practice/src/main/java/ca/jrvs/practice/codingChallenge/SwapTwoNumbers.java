package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/Swap-two-numbers-d26a6d51dd0a40cc81892ed00f60ff25
 */
public class SwapTwoNumbers {
  public int[] swapTwoNumbers(int[] arr){
    if(arr.length != 2)
      throw new IllegalArgumentException();
    //O(1) complexity
    arr[0] = (int)Math.pow(10,1+Math.floor(Math.log10(arr[1])))*arr[0] + arr[1];
    arr[1] = arr[0]/((int)Math.pow(10,1+Math.floor(Math.log10(arr[1]))));
    arr[0] = arr[0]%((int)Math.pow(10,1+Math.floor(Math.log10(arr[1]))));
    return arr;
  }

  public int[] swapTwoNumbersBitManip(int[] arr){
    //You probably had in mind for this solution to just shift one value 16 bits
    //to the left to make room for the other number, but this way shifts the value
    //by the exact number that is absolutely necessary.
    arr[0] = (arr[0] << (1 + (int) Math.floor(Math.log(arr[1])/Math.log(2)))) + arr[1];
    arr[1] = arr[0] >> (1 + (int) Math.floor(Math.log(arr[1])/Math.log(2)));
    arr[0] = arr[0] & (int)Math.pow(2,((1 + (int) Math.floor(Math.log(arr[0])/Math.log(2)))
        - (1 + (int) Math.floor(Math.log(arr[1])/Math.log(2)))) - 1);
    return arr;
  }
}
