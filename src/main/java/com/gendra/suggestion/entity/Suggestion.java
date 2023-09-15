package com.gendra.suggestion.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Suggestion extends City {

  private double score;

  public Suggestion(City city, double score) {
    super.setId(city.getId());
    super.setName(city.getName());
    super.setLatitude(city.getLatitude());
    super.setLongitude(city.getLongitude());
    this.score = score;
  }
}
