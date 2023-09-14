package com.gendra.suggestion.entity;

import lombok.Data;

@Data
public class City {

  private Long id;
  private String name;
  private double latitude;
  private double longitude;

  public City(CityFile cityFile) {
    this.id = cityFile.getId();
    this.name = cityFile.getName();
    this.latitude = cityFile.getLatitude();
    this.longitude = cityFile.getLongitude();
  }

  public City() {}
}
