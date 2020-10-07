package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;

public class ArrayPractice {

  public static void main(String[] args) {
    // create an array of integers
    int[] intArray = new int[10];
    intArray[0] = 100;
    intArray[1] = 200;
    intArray[2] = 300;

    //shortcut syntax to create and initialize an array
    int[] inlineArray = {
        100, 200, 300
    };

    //copy array
    char[] copyFrom = {'d','e','c','a','f','f','e','i'
        ,'n','a','t','e','d'};
    char[] copyTo = new char[7];

    System.arraycopy(copyFrom, 2, copyTo, 0, 7);
    System.out.println(new String(copyTo));
  }
}
