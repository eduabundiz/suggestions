package com.gendra.suggestion.repository;

import com.gendra.suggestion.entity.CityFile;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class CitiesReaderRepository {
  String TSV_FILE_NAME = "cities_canada-usa.tsv";
  List<CityFile> cities = new ArrayList<>();

  public List<CityFile> getCities() {
    if (cities == null) {
      cities = getFileCities();
    }
    return cities;
  }

  @PostConstruct
  public List<CityFile> getFileCities() {

    try (InputStreamReader reader =
            new InputStreamReader(new ClassPathResource(TSV_FILE_NAME).getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader)) {
      cities =
          bufferedReader
              .lines()
              .skip(1)
              .map(line -> line.split("\t"))
              .filter(parts -> parts.length == 19)
              .map(CityFile::new)
              .collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return cities;
  }
}
