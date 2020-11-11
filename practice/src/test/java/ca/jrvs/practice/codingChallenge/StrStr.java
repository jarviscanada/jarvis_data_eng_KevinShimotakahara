package ca.jrvs.practice.codingChallenge;

public class StrStr {
  public int strStr(String haystack, String needle) {
    if(needle.length() == 0)
      return 0;
    for(int i = 0; i<= haystack.length() - needle.length(); i++){
      if(haystack.substring(i , i + needle.length()).equals(needle))
        return 2;
    }
    return -1;
  }
}
