package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ticket URL: https://www.notion.so/Implement-Stack-using-Queue-73329c1528564bc390b1f00cad40b680
 */
public class StackUsingQueue {
  public Queue<Integer> q1 = new LinkedList<>();
  public Queue<Integer> q2 = new LinkedList<>();
  public int activeQueue = 1;

  //for single queue implementation
  public Queue<Integer> q3 = new LinkedList<>();

  /** Initialize your data structure here. */
  public StackUsingQueue() {
  }

  /** Push element x onto stack. */
  public void pushA1(int x) {
    //O(1) complexity; simply inserting new element at the end of a queue
    switch(activeQueue){
      case 1:
        q1.add(x);
        break;
      default:
        q2.add(x);
        break;
    }
  }

  /** Removes the element on top of the stack and returns that element. */
  public int popA1() {
    //O(n) complexity; must do n removes, n-1 adds
    switch(activeQueue){
      case 1:
        while(q1.size() > 1){
          q2.add(q1.remove());
        }
        activeQueue = 2;
        return q1.remove();
      default:
        while(q2.size() > 1){
          q1.add(q2.remove());
        }
        activeQueue = 1;
        return q2.remove();
    }
  }

  public void pushA2(int x){
    q3.add(x);
  }

  public int popA2(){
    //O(n) complexity; need to cycle through the queue until the last is at the front
    for(int i = 0; i < q3.size() - 1; i++){
      q3.add(q3.remove());
    }
    return q3.remove();
  }
}
