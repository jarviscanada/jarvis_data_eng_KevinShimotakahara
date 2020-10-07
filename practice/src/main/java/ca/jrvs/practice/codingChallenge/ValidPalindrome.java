package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Valid-Palindrome-29d9d6955f0f4dc4951e04c8fc10e0dd
 */
public class ValidPalindrome {
  public Boolean validPalindromeA1(String text){
    //This is O(n) complexity
    for(int i = 0;i < text.length()/2; i++){
      int iReflection = text.length() - 1 - i;
      if (text.charAt(i) != text.charAt(iReflection))
        return false;
    }
    //Comes to this if string is empty
    return true;
  }

  public Boolean validPalindromeA2(String text){
    //recursive version; still requires n/2 function calls, but eats up more stack memory
    //O(n) time complexity, O(n) space complexity
    //Is a fun solution, but not a good one when you can just loop through the string
    int len = text.length();
    switch (len){
      case 0:
        return true;
      case 3:
        if(text.charAt(0) == text.charAt(2))
          return true;
        return false;
      case 2:
        if(text.charAt(0) == text.charAt(1))
          return true;
        return false;
      default:
        if(text.charAt(0) == text.charAt(len-1))
          return validPalindromeA2(text.substring(1,len-1));
        return false;
    }
  }
}
