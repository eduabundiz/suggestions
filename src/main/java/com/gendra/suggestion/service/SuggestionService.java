package com.gendra.suggestion.service;

import com.gendra.suggestion.entity.City;
import com.gendra.suggestion.entity.CityFile;
import com.gendra.suggestion.entity.Suggestion;
import com.gendra.suggestion.repository.CitiesReaderRepository;
import com.gendra.suggestion.util.DistanceUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

  CitiesReaderRepository citiesReaderRepository;

  @Autowired
  public SuggestionService(CitiesReaderRepository repository) {
    this.citiesReaderRepository = repository;
  }

  public List<Suggestion> getSuggestions(String query, Double latitude, Double longitude) {
    List<Suggestion> suggestions = new ArrayList<>();
    List<CityFile> cities = citiesReaderRepository.getCities();
    Set<City> matchingCities = findByNameContainingIgnoreCase(cities, query);
    for (City city : matchingCities) {
      double score = calculateScore(city, latitude, longitude);
      suggestions.add(
          new Suggestion(
              city.getId(), city.getName(), city.getLatitude(), city.getLongitude(), score));
    }
    suggestions.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
    if (suggestions.size() > 200) {
      suggestions = suggestions.subList(0, 200);
    }
    return suggestions;
  }

  public double calculateScore(City city, Double latitude, Double longitude) {
    double baseScore = 1.0;
    double distanceScore = calculateDistanceScore(city, latitude, longitude);
    return baseScore * distanceScore;
  }

  public double calculateDistanceScore(City city, Double latitude, Double longitude) {
    if (latitude != null && longitude != null) {
      double cityLatitude = city.getLatitude();
      double cityLongitude = city.getLongitude();
      double distance =
          DistanceUtils.calculateDistance(cityLatitude, cityLongitude, latitude, longitude);
      return 1.0 / distance;
    }
    return 1.0;
  }

  public Set<City> findByNameContainingIgnoreCase(List<CityFile> cities, String query) {
    Set<City> matchCities = new HashSet<>();
    for (CityFile cityFile : cities) {
      if (matchesQuery(cityFile, query)) {
        City city = new City(cityFile);
        matchCities.add(city);
      }
    }
    return matchCities;
  }

  public boolean matchesQuery(CityFile cityFile, String query) {
    String lowerQuery = query.toLowerCase();
    return cityFile.getName().toLowerCase().contains(lowerQuery)
        || cityFile.getAscii().toLowerCase().contains(lowerQuery)
        || cityFile.getAltName().toLowerCase().contains(lowerQuery);
  }
}
