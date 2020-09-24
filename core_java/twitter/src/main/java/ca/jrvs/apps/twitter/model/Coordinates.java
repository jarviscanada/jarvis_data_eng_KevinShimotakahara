package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {
  private float[] coordinates = new float[2];

  public float[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(float[] coordinates) {
    this.coordinates = coordinates;
  }
}
