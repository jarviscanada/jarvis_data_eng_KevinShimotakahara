package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})

public class Tweet {
  @JsonProperty("created_at")
  private Date created_at;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String id_str;
  @JsonProperty("entities")
  private List<Entities> entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweet_count = 0;
  @JsonProperty("favourite_count")
  private int favourite_count = 0;
  @JsonProperty("favourited")
  private boolean favourited = false;
  @JsonProperty("retweeted")
  private boolean retweeted = false;

  @JsonProperty("created_at")
  public Date getCreated_at() {
    return created_at;
  }
  @JsonProperty("created_at")
  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }
  @JsonProperty("id")
  public long getId() {
    return id;
  }
  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }
  @JsonProperty("id_str")
  public String getId_str() {
    return id_str;
  }
  @JsonProperty("id_str")
  public void setId_str(String id_str) {
    this.id_str = id_str;
  }
  @JsonProperty("entities")
  public List<Entities> getEntities() {
    return entities;
  }
  @JsonProperty("entities")
  public void setEntities(List<Entities> entities) {
    this.entities = entities;
  }
  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }
  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }
  @JsonProperty("retweet_count")
  public int getRetweet_count() {
    return retweet_count;
  }
  @JsonProperty("retweet_count")
  public void setRetweet_count(int retweet_count) {
    this.retweet_count = retweet_count;
  }
  @JsonProperty("favourite_count")
  public int getFavourite_count() {
    return favourite_count;
  }
  @JsonProperty("favourite_count")
  public void setFavourite_count(int favourite_count) {
    this.favourite_count = favourite_count;
  }
  @JsonProperty("favourited")
  public boolean isFavourited() {
    return favourited;
  }
  @JsonProperty("favourited")
  public void setFavourited(boolean favourited) {
    this.favourited = favourited;
  }
  @JsonProperty("retweeted")
  public boolean isRetweeted() {
    return retweeted;
  }
  @JsonProperty("retweeted")
  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }
}
