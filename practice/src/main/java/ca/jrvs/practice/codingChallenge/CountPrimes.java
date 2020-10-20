package ca.jrvs.practice.codingChallenge;
import java.util.HashSet;

/**
 * https://www.notion.so/Count-Primes-906d24f1a3c84669842721548b8d4b99
 */
public class CountPrimes {
  public HashSet<Integer> set = new HashSet<>();
  public int countPrimes(int n){
    int answer = 0;
    for(int i = 2; i < n; i++){
      if(isPrime(i)){
        if(i > 3){
          set.add(i);
        }
        answer++;
      }
    }
    return answer;
  }
  public boolean isPrime(Integer integer){
    if(set.isEmpty()){
      set.add(2);
      set.add(3);
    }
    if(integer == 2 || integer == 3)
      return true;
    for(Integer prime : set){
      if(integer%prime == 0)
        return false;
    }
    return true;
  }
}
