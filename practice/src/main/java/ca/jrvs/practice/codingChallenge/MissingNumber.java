package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

/**
 * https://www.notion.so/Missing-Number-5f9873acfc194ecfa5d2b40b4932ccaa
 */

public class MissingNumber{

  public Integer findMissingNumberSum(Integer[] arr){
    Integer integral = 0;
    for(int i = 1; i <= arr.length; i++){
      integral += i;
    }
    Integer arrIntegral = 0;
    for(Integer element : arr){
      arrIntegral += element;
    }
    return integral - arrIntegral;
  }

  public Integer findMissingNumberGauss(Integer[] arr){
    Integer integral = (arr.length+1)*arr.length/2;
    Integer arrIntegral = 0;
    for(Integer element : arr){
      arrIntegral += element;
    }
    return integral - arrIntegral;
  }

  public Integer findMissingNumber(Integer[] arr){
    HashSet<Integer> set = new HashSet<>();
    //O(n) time and space
    for(Integer i = 0; i <= arr.length + 1; i++){
      set.add(i);
    }
    //O(n)
    for(Integer element : arr){
      set.remove(element);
    }
    //O(1) space
    Object[] answer = set.toArray();
    return (Integer) answer[0];
  }
}
