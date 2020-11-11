package ca.jrvs.practice.codingChallenge;
import java.lang.Math;
public class LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0)
      return "";
    StringBuilder answer = new StringBuilder();
    int minSize = 201;
    for(int i = 0; i < strs.length; i++){
      if(minSize > strs[i].length())
        minSize = strs[i].length();
    }
    boolean mismatch = false;
    for(int i = 0; i < minSize; i++){
      for(String str : strs){
        if(str.charAt(i) != strs[0].charAt(i)){
          mismatch = true;
        }
      }
      if(mismatch)
        break;
      answer.append(strs[0].charAt(i));
    }
    return answer.toString();
  }
}
