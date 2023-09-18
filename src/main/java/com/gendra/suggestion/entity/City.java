package com.gendra.suggestion.entity;

import lombok.Data;

@Data
public class City {

  private Long id;
  private String name;
  private double latitude;
  private double longitude;

  public City(CityDAO cityDAO) {
    this.id = cityDAO.getId();
    this.name = cityDAO.getName();
    this.latitude = cityDAO.getLatitude();
    this.longitude = cityDAO.getLongitude();
  }

  public City() {}
}
