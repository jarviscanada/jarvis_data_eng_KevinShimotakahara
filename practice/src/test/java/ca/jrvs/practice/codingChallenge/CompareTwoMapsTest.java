package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareTwoMapsTest {

  @Test
  public void compareMapsTest() {
    CompareTwoMaps c2m = new CompareTwoMaps();
    Map<String,String> m1 = new HashMap<>();
    m1.put("someKey","someValue");
    m1.put("someOtherKey","someOtherValue");
    Map<String,String> m2 = new HashMap<>();
    m2.put("someKey","someValue");
    m2.put("someOtherKey","someOtherValue");
    assertEquals(c2m.compareMapsHashJMap(m1,m2),true);
    assertEquals(c2m.compareMapsJavaAPI(m1,m2),true);
    m2.put("someOtherOtherKey","someOtherValue");
    assertEquals(c2m.compareMapsHashJMap(m1,m2),false);
    m1.put("someOtherOtherKey","someOtherValue");
    assertEquals(c2m.compareMapsHashJMap(m1,m2),true);
    m1.put("someOtherOtherOtherKey","someOtherValue");
    assertEquals(c2m.compareMapsHashJMap(m1,m2),false);

  }
}