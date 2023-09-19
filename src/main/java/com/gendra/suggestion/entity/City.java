package com.gendra.suggestion.entity;

import lombok.Data;

/** Represents a city with basic information including its ID, name, latitude, and longitude. */
@Data
public class City {

  /** The unique ID of the city. */
  private Long id;

  /** The name of the city. */
  private String name;

  /** The latitude coordinates of the city's location. */
  private double latitude;

  /** The longitude coordinates of the city's location. */
  private double longitude;

  /**
   * Constructs a new City object based on a CityDAO object.
   *
   * @param cityDAO The CityDAO object providing city data.
   */
  public City(CityDAO cityDAO) {
    this.id = cityDAO.getId();
    this.name = cityDAO.getName();
    this.latitude = cityDAO.getLatitude();
    this.longitude = cityDAO.getLongitude();
  }

  /** Default constructor for creating an empty City object. */
  public City() {}
}
