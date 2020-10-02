package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-a7415debad1f4fd988c5bdf0c6357192
 */
public class Fibonacci {
  public int fibonacciRecursive(int n){
   if(n < 1)
     throw new IllegalArgumentException("Fibonacci Sequence is only defined for natural number inputs.");
   if (n == 1 || n == 2)
     return 1;
   //O(2^n) time complexity because computation roughly doubles every time you increase n
   //O(n) space complexity because recursion tree's max height = n, so uses n slots of
   //stack memory at a time
   return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
  }
  public int numDistinctWaysRecursive(int numSteps){
    return fibonacciRecursive(numSteps+1);
  }
  public int fibonacciDP(int n){
    //before anything, make sure problem isn't trivial
    if (n == 1 || n == 2)
      return 1;

    int[] memo = new int[n-1];
    memo[0] = 1;
    memo[1] = 1;
    //Linear time complexity O(n) because for loop
    for(int i = 2; i < n-1; i++){
      memo[i] = memo[i-1] + memo[i-2];
    }
    return memo[n-2] + memo[n-3];
  }
  public int numDistinctWaysDP(int numSteps){
    return fibonacciDP(numSteps + 1);
  }
}
