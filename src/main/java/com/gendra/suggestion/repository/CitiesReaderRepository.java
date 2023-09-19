package com.gendra.suggestion.repository;

import com.gendra.suggestion.entity.CityDAO;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

/**
 * This class is responsible for reading city data from a TSV file and providing access to it. City
 * data is stored in a list of CityDAO objects.
 */
@Repository
public class CitiesReaderRepository {
  String TSV_FILE_NAME = "cities_canada-usa.tsv";
  List<CityDAO> cities = new ArrayList<>();

  /**
   * Retrieves the list of cities from the TSV file if it has not been loaded yet.
   *
   * @return The list of cities stored in CityDAO objects.
   */
  public List<CityDAO> getCities() {
    if (cities == null) {
      cities = getFileCities();
    }
    return cities;
  }

  /**
   * Reads city data from the TSV file and stores it in a list of CityDAO objects.
   * The @PostConstruct annotation is used to ensure that this method is called after bean
   * initialization.
   *
   * @return The list of cities stored in CityDAO objects.
   */
  @PostConstruct
  public List<CityDAO> getFileCities() {
    try (InputStreamReader reader =
            new InputStreamReader(new ClassPathResource(TSV_FILE_NAME).getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader)) {
      cities =
          bufferedReader
              .lines()
              .skip(1)
              .map(line -> line.split("\t"))
              .filter(parts -> parts.length == 19)
              .map(CityDAO::new)
              .collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return cities;
  }
}
