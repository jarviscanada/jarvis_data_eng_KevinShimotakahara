package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LambdaStreamImp {
  /**
   * Create a String stream from array
   *
   * note: arbitrary number of value will be stored in an array
   *
   * @param strings
   * @return
   */
  public Stream<String> createStrStream(String ... strings) {
    return Arrays.stream(strings);
  }

  /**
   * Convert all strings to uppercase
   * please use createStrStream
   *
   * @param strings
   * @return
   */
  public Stream<String> toUpperCase(String ... strings){
    return this.createStrStream(strings).map(string -> string.toUpperCase());
  }

  /**
   * filter strings that contains the pattern
   * e.g.
   * filter(stringStream, "a") will return another stream which no element contains a
   *
   *
   * @param stringStream
   * @param pattern
   * @return
   */
  Stream<String> filter(Stream<String> stringStream, String pattern){
    Pattern patternObj = Pattern.compile(pattern);
    return stringStream.filter(string -> patternObj.matcher(string).find());
  }

  /**
   * Create a intStream from a arr[]
   * @param arr
   * @return
   */
  IntStream createIntStream(int[] arr){
    //stream method automatically returns an IntStream if you pass a primitive int array to it
    return Arrays.stream(arr);
  }

  /**
   * Convert a stream to list
   *
   * @param stream
   * @param <E>
   * @return
   */
  <E> List<E> toList(Stream<E> stream){
    return stream.collect(Collectors.toList());
  }

  /**
   * Convert a intStream to list
   * @param intStream
   * @return
   */
  List<Integer> toList(IntStream intStream){
    //boxed method wraps primitives in wrapper classes so they can be "collected"
    return intStream.boxed().collect(Collectors.toList());
  }

  /**
   * Create a IntStream range from start to end inclusive
   * @param start
   * @param end
   * @return
   */
  IntStream createIntStream(int start, int end){

    return IntStream.rangeClosed(start, end);
  }

  /**
   * Convert a intStream to a doubleStream
   * and compute square root of each element
   * @param intStream
   * @return
   */
  DoubleStream squareRootIntStream(IntStream intStream){
    return intStream.mapToDouble(integer -> (double) integer).map(dub -> Math.pow(dub,0.5));
  }

  /**
   * filter all even number and return odd numbers from a intStream
   * @param intStream
   * @return
   */
  IntStream getOdd(IntStream intStream){
    return intStream.filter(integer -> integer%2 == 1);
  }

  /**
   * Return a lambda function that print a message with a prefix and suffix
   * This lambda can be useful to format logs
   *
   * You will learn:
   *   - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
   *   - lambda syntax
   *
   * e.g.
   * LambdaStreamExc lse = new LambdaStreamImp();
   * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
   * printer.accept("Message body");
   *
   * sout:
   * start>Message body<end
   *
   * @param prefix prefix str
   * @param suffix suffix str
   * @return
   */
  Consumer<String> getLambdaPrinter(String prefix, String suffix){
    Consumer<String> thing = (String message) -> {System.out.println(prefix + message + suffix);};
    return thing;
  }

  /**
   * Print each message with a given printer
   * Please use `getLambdaPrinter` method
   *
   * e.g.
   * String[] messages = {"a","b", "c"};
   * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
   *
   * sout:
   * msg:a!
   * msg:b!
   * msg:c!
   *
   * @param messages
   * @param printer
   */
  void printMessages(String[] messages, Consumer<String> printer){
    this.createStrStream(messages).forEach(printer);
  }

  /**
   * Print all odd number from a intStream.
   * Please use `createIntStream` and `getLambdaPrinter` methods
   *
   * e.g.
   * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
   *
   * sout:
   * odd number:1!
   * odd number:3!
   * odd number:5!
   *
   * @param intStream
   * @param printer
   */
  void printOdd(IntStream intStream, Consumer<String> printer){
    this.getOdd(intStream).mapToObj(String::valueOf).forEach(printer);
  }

  /**
   * Square each number from the input.
   * Please write two solutions and compare difference
   *   - using flatMap
   *
   * @param ints
   * @return
   */
  Stream<Integer> flatNestedInt(Stream<List<Integer>> ints){
    return ints.flatMap(Collection::stream).map(integer -> integer*integer);
  }

  public static void main(String[] args) {
    LambdaStreamImp harpalarp = new LambdaStreamImp();
    IntStream intstream = harpalarp.createIntStream(4,9);
    harpalarp.squareRootIntStream(intstream).forEach(System.out::println);
    Stream<String> strStream = harpalarp.toUpperCase("am", "I", "doing", "this", "right?");
    strStream = harpalarp.filter(strStream,"I");
    strStream.forEach(System.out::println); //.filter(string -> string.contains("I"))
    Consumer<String> someLambdaThing = harpalarp.getLambdaPrinter("super","explialodocious");
    someLambdaThing.accept("kallafragalistic");
    harpalarp.printMessages(new String[] {"bleh","dolan"},someLambdaThing);

    harpalarp.printOdd(harpalarp.createIntStream(1,6),someLambdaThing);
    List<Integer> a = Arrays.asList(0, 1);
    List<Integer> b = Arrays.asList(2, 3);
    List<Integer> c = Arrays.asList(4, 5);
    List<List<Integer>> listOfLists = new ArrayList<>();
    listOfLists.add(a);
    listOfLists.add(b);
    listOfLists.add(c);

    harpalarp.flatNestedInt(listOfLists.stream()).forEach(System.out::println);
    harpalarp.createIntStream(1,10).filter(integer -> integer%2 == 0).forEach(System.out::println);
  }

}
