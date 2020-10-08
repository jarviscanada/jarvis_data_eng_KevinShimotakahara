package ca.jrvs.apps.trading.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil {
  /**
   * Parse JSON string to an object
   * @param json JSON str
   * @param clazz object class
   * @param <T> Type
   * @return Object
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    //m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    return (T) m.readValue(json,clazz);
  }

  public static String toPrettyJson(Object object)
      throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();
    m.setSerializationInclusion(Include.NON_NULL);
    m.enable(SerializationFeature.INDENT_OUTPUT);
    return m.writeValueAsString(object);
  }
}

