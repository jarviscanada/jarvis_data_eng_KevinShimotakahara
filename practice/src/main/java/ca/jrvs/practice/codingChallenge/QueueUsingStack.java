package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Ticket URL: https://www.notion.so/Implement-Queue-using-Stacks-adb6bec11569450a8ff4d8706666eaa7
 */
public class QueueUsingStack {
  private LinkedList<Integer> topUp = new LinkedList<>();
  private LinkedList<Integer> bottomUp = new LinkedList<>();

  //O(n)
  public void enqueueA1(int x){
    while(!bottomUp.isEmpty())
      topUp.push(bottomUp.pop());
    topUp.push(x);
    while(!topUp.isEmpty())
      bottomUp.push(topUp.pop());
  }

  //O(1)
  public int dequeueA1(){
    return bottomUp.pop();
  }

  //Overall, the time complexity of enqueuing/dequeuing is amortized O(1)
  //because you do not always have to do a linear time operation
  public void enqueueA2(int x){
    if(bottomUp.isEmpty()){
      //hard life (O(n) operation)
      while(!topUp.isEmpty())
        bottomUp.push(topUp.pop());
    }
    bottomUp.push(x);
  }

  public int dequeueA2(){
    if(topUp.isEmpty()){
      //hard life (O(n) operation)
      while(!bottomUp.isEmpty())
        topUp.push(bottomUp.pop());
    }
    return topUp.pop();
  }
}
