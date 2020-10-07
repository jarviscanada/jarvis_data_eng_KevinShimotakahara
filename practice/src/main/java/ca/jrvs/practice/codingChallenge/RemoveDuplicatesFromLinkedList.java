package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * Ticket URL: https://www.notion.so/Duplicate-LinkedList-Node-e6859d1d58c64d799dfb0b62af023a7c
 */
public class RemoveDuplicatesFromLinkedList {
  public void removeDuplicatesFromLinkedList(LinkedList<Integer> list){
    //create a hash map to keep track of values as you traverse the list
    Set<Integer> uniqueValuesInList = new HashSet<>();
    int i = 0;
    int reading;
    //O(n) complexity
    while(i < list.size()){
      reading = list.get(i);
      if(uniqueValuesInList.contains(reading)){
        list.remove(i);
      } else{
        //add to set, but don't delete from list
        uniqueValuesInList.add(reading);
        i++;
      }
    }
  }
}
