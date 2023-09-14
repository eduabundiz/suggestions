package com.gendra.suggestion.repository;

import com.gendra.suggestion.Entity.City;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {
  List<City> cities;

  public List<City> findByNameContainingIgnoreCase(String query) {

    return cities;
  }
}
