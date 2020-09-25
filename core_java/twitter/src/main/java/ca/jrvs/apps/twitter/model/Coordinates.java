package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "coordinates",
})
public class Coordinates {
  @JsonProperty("type")
  private String type;
  @JsonProperty("coordinates")
  private double[] coordinates = new double[2];

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("coordinates")
  public double[] getCoordinates() {
    return coordinates;
  }
  @JsonProperty("coordinates")
  public void setCoordinates(double[] coordinates) {
    this.coordinates = coordinates;
  }
}
