package ca.jrvs.practice.codingChallenge;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ticket Link:
 * https://www.notion.so/How-to-compare-two-maps-921fb62e8abf4d839d3dfb3baad51a23
 */
public class CompareTwoMaps {
  public <K,V> boolean compareMapsJavaAPI(Map<K,V> m1, Map<K,V> m2){
    //O(1) complexity (at least assuming it is implemented with the gitHub code referred
    // to in the ticket)
    // This method simply checks for reference equivalence, which is a single operation.
    return m1.equals(m2);
  }
  public <K,V> boolean compareMapsHashJMap(Map<K,V> m1, Map<K,V> m2){
    //O(1) complexity (at least assuming it is implemented with the gitHub code referred
    // to in the ticket)
    // This method simply checks for reference equivalence, which is a single operation.

    //I guess this is supposed to test value equivalence
    //Always check if the two objects are literally the same
    if(m1 == m2)
      return true;

    //Check if all the entry Node<K,V>s are the same O(n) complexity
      //Two passes through collections
      //Each iteration needs to do a contains check (but this is O(1) complexity for a HashSet)
      // 2*n*O(1) = O(n)
    Set<Map.Entry<K,V>> m1ADm2 = m1.entrySet().stream().filter(node -> !m2.entrySet().contains(node)).collect(Collectors.toSet());
    Set<Map.Entry<K,V>> m2ADm1 = m2.entrySet().stream().filter(node -> !m1.entrySet().contains(node)).collect(Collectors.toSet());

    return m1ADm2.isEmpty() && m2ADm1.isEmpty();
  }
}