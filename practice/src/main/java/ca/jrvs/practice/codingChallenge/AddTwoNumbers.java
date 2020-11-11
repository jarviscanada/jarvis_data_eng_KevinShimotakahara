package ca.jrvs.practice.codingChallenge;

import java.lang.Math;

public class AddTwoNumbers {

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int l1Radix = 0, l2Radix = 0, l1Sum = 0, l2Sum = 0;
    while(l1 != null || l2 != null){
      while(l1 != null || l2 != null){
        if(l1 != null){
          l1Sum += l1.val*Math.pow(10,l1Radix++);
          l1 = l1.next;
        }

        if(l2 != null){
          l2Sum +=  l2.val*Math.pow(10,l2Radix++);
          l2 = l2.next;
        }
      }
    }

    int sum = l1Sum + l2Sum;

    ListNode answer = new ListNode();
    ListNode current = answer;
    ListNode temp = new ListNode();
    do{
      current.val = sum%10;
      sum /= 10;
      if(sum > 0){
        current.next = new ListNode();
        current = current.next;
      }
    }while(sum > 0);

    return answer;
  }
}
