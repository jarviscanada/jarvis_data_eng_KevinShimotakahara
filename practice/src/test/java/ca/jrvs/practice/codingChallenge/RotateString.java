package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Rotate-String-3e0daf3ea9a54672b11b0f63e262f649
 */
public class RotateString {
  public boolean rotateString(String A, String B){
    if(A.length() != B.length())
      return false;
    //This method calls a double for loop method, thus O(N^2) time complexity
    if((A+A).contains(B))
      return true;
    return false;
  }
}