package com.gendra.suggestion.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a suggestion based on a city, including a score. Extends the City class to inherit
 * basic city information.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Suggestion extends City {

  /** The score associated with the suggestion. */
  private double score;

  /**
   * Constructs a new Suggestion object based on a City object and a score.
   *
   * @param city The City object providing basic city information.
   * @param score The score associated with the suggestion.
   */
  public Suggestion(City city, double score) {
    super.setId(city.getId());
    super.setName(city.getName());
    super.setLatitude(city.getLatitude());
    super.setLongitude(city.getLongitude());
    this.score = score;
  }
}
