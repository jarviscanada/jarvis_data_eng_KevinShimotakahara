package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {
  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    if(arr.length != 0) {
      int middle = arr.length / 2;
      switch (target.compareTo(arr[middle])) {
        case 0:
          return Optional.of(middle);
        case -1:
          //target is less than midpoint
          return binarySearchRecursion(Arrays.copyOfRange(arr, 0, middle), target);
        case 1:
          return binarySearchRecursion(Arrays.copyOfRange(arr, middle + 1, arr.length), target);
      }
    }
    return Optional.empty();
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    if(arr.length > 0) {
      int middle = arr.length / 2;
      int low = 0;
      int high = arr.length - 1;
      while (high != low) {
        switch (target.compareTo(arr[middle])) {
          case 0:
            return Optional.of(middle);
          case -1:
            //target is less than midpoint
            high = middle - 1;
            break;
          case 1:
            low = middle + 1;
            break;
        }
        middle = (high + low)/2;
      }
    }
    return Optional.empty();
  }
}
