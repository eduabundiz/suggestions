package com.gendra.suggestion.service;

import com.gendra.suggestion.entity.City;
import com.gendra.suggestion.entity.CityFile;
import com.gendra.suggestion.entity.Suggestion;
import com.gendra.suggestion.repository.CitiesReaderRepository;
import com.gendra.suggestion.util.DistanceUtils;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    List<CityFile> cities = citiesReaderRepository.getCities();

    return cities.stream()
        .filter(city -> city.getName().toLowerCase().contains(query.toLowerCase()))
        .map(
            city -> {
              double score = calculateScore(city, latitude, longitude);
              return new Suggestion(city, score);
            })
        .sorted((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()))
        .limit(200)
        .collect(Collectors.toList());
  }

  public double calculateScore(City city, Double latitude, Double longitude) {
    double baseScore = 100;
    double distanceScore =
        (latitude != null && longitude != null)
            ? calculateDistanceScore(city, latitude, longitude)
            : 0.0;
    return baseScore * distanceScore;
  }

  public double calculateDistanceScore(City city, Double latitude, Double longitude) {
    double cityLatitude = city.getLatitude();
    double cityLongitude = city.getLongitude();
    double distance =
        DistanceUtils.calculateDistance(cityLatitude, cityLongitude, latitude, longitude);
    return 1.0 / distance;
  }

  public Set<City> findByNameContainingIgnoreCase(List<CityFile> cities, String query) {
    return cities.stream()
        .filter(cityFile -> matchesQuery(cityFile, query))
        .map(City::new)
        .collect(Collectors.toSet());
  }

  public boolean matchesQuery(CityFile cityFile, String query) {
    String lowerQuery = query.toLowerCase();
    return cityFile.getName().toLowerCase().contains(lowerQuery)
        || cityFile.getAscii().toLowerCase().contains(lowerQuery)
        || cityFile.getAltName().toLowerCase().contains(lowerQuery);
  }
}
