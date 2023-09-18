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

/**
 * The {@code SuggestionService} class provides methods for generating location suggestions based on
 * a user query and geographical coordinates.
 *
 * <p>This service uses data from a {@link CitiesReaderRepository} to calculate and return
 * suggestions.
 */
@Service
public class SuggestionService {

  private final CitiesReaderRepository citiesReaderRepository;

  /**
   * Constructs a new {@code SuggestionService} with the specified repository.
   *
   * @param repository The repository to retrieve city data from.
   */
  @Autowired
  public SuggestionService(CitiesReaderRepository repository) {
    this.citiesReaderRepository = repository;
  }

  /**
   * Retrieves a list of location suggestions based on the provided query, latitude, and longitude.
   *
   * @param query The user's search query.
   * @param latitude The latitude of the user's current location (nullable).
   * @param longitude The longitude of the user's current location (nullable).
   * @return A list of {@link Suggestion} objects sorted by relevance.
   */
  public List<Suggestion> getSuggestions(String query, Double latitude, Double longitude) {
    List<CityFile> cities = citiesReaderRepository.getCities();

    return cities.stream()
        .filter(city -> matchesQuery(city, query))
        .map(city -> new Suggestion(city, calculateScore(city, latitude, longitude)))
        .sorted((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()))
        .limit(200)
        .collect(Collectors.toList());
  }

  /**
   * Calculates the relevance score for a city based on its proximity to the specified coordinates.
   *
   * @param city The city for which to calculate the score.
   * @param latitude The latitude of the reference point.
   * @param longitude The longitude of the reference point.
   * @return The relevance score for the city.
   */
  public double calculateScore(City city, Double latitude, Double longitude) {
    double baseScore = 100;
    double distanceScore =
        (latitude != null && longitude != null)
            ? calculateDistanceScore(city, latitude, longitude)
            : 0.0;
    return baseScore * distanceScore;
  }

  /**
   * Calculates the distance-based score for a city relative to the specified coordinates.
   *
   * @param city The city for which to calculate the score.
   * @param latitude The latitude of the reference point.
   * @param longitude The longitude of the reference point.
   * @return The distance-based score for the city.
   */
  public double calculateDistanceScore(City city, Double latitude, Double longitude) {
    double cityLatitude = city.getLatitude();
    double cityLongitude = city.getLongitude();
    double distance =
        DistanceUtils.calculateDistance(cityLatitude, cityLongitude, latitude, longitude);
    return 1.0 / distance;
  }

  /**
   * Finds a set of cities whose names, ASCII names, or alternative names match the given query.
   *
   * @param cities The list of cities to search within.
   * @param query The user's search query.
   * @return A set of {@link City} objects matching the query.
   */
  public Set<City> findByNameContaining(List<CityFile> cities, String query) {
    return cities.stream()
        .filter(cityFile -> matchesQuery(cityFile, query))
        .map(City::new)
        .collect(Collectors.toSet());
  }

  /**
   * Checks if a given city file matches the user's query (case-insensitive).
   *
   * @param cityFile The city file to check.
   * @param query The user's search query.
   * @return {@code true} if the city file matches the query, {@code false} otherwise.
   */
  public boolean matchesQuery(CityFile cityFile, String query) {
    String lowerQuery = query.toLowerCase();
    return cityFile.getName().toLowerCase().contains(lowerQuery)
        || cityFile.getAscii().toLowerCase().contains(lowerQuery)
        || cityFile.getAltName().toLowerCase().contains(lowerQuery);
  }
}
