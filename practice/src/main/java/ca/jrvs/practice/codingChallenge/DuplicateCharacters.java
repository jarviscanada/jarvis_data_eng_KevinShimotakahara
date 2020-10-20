package ca.jrvs.practice.codingChallenge;
import java.util.HashSet;

/**
 * https://www.notion.so/Duplicate-Characters-3bffb9ed169a44d5a3b743b56ab2f6d1
 */
public class DuplicateCharacters {
  private HashSet<String> trackingSet = new HashSet<>();
  private HashSet<String> answerSet = new HashSet<>();
  public String[] duplicateCharacters(String string) {
    //O(n)
    for (int i = 0; i < string.length(); i++) {
      if(Character.isDigit(string.charAt(i)) || Character.isAlphabetic(string.charAt(i))){
        String c = String.valueOf(string.charAt(i));
        if (trackingSet.contains(c) && !answerSet.contains(c)) {
          answerSet.add(c);
        } else {
          trackingSet.add(c);
        }
      }
    }
    return answerSet.toArray(new String[0]);
  }
}
