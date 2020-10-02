package ca.jrvs.practice.codingChallenge;

public class RemoveNthLastNodeFromLinkedList {
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode() {}
   *     ListNode(int val) { this.val = val; }
   *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */
  public static class ListNode{
    int val;
    ListNode next;
    ListNode() {};
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode removeNthFromEnd(ListNode head, int n){
    //Must count the number of elements in the list O(n)
    int size = 0;
    ListNode current = head;
    while(current != null){
      size++;
      current = current.next;
    }
    //Now calculate absolute index of n, assuming n = 1 is referring to last element in list
    int nAbs = size - n + 1;
    current = head;
    //General way of removing Linked list element doesn't work if you need to remove head
    if(nAbs == 1){
      head = head.next;
      return head;
    }
    //Otherwise you have a general case. Find element and remove it (O(n))
    int i = 1;
    current = head;
    while(i < nAbs-1){
      current = current.next;
      i++;
    }
    current.next = current.next.next;
    return head;
  }
}
