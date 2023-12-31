package com.gendra.suggestion.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.gendra.suggestion.entity.City;
import com.gendra.suggestion.entity.CityDAO;
import com.gendra.suggestion.entity.Suggestion;
import com.gendra.suggestion.repository.CitiesReaderRepository;
import com.gendra.suggestion.service.SuggestionService;
import com.gendra.suggestion.util.DistanceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SuggestionServiceTest {

  @InjectMocks private SuggestionService suggestionService;

  @Mock private CitiesReaderRepository citiesReaderRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetSuggestions() {
    String query = "A";
    Double latitude = 43.70011;
    Double longitude = -79.4163;

    CityDAO city1 = new CityDAO();
    CityDAO city2 = new CityDAO();
    city1.setId(5881791L);
    city1.setName("Abbotsford");
    city1.setAscii("Abbotsford");
    city1.setAltName("Abbotsford,YXX,Абботсфорд");
    city1.setLatitude(49.05798);
    city1.setLongitude(-122.25257);

    city2.setId(5882142L);
    city2.setName("Acton Vale");
    city2.setAscii("Acton Vale");
    city2.setLatitude(45.65007);
    city2.setLongitude(-72.56582);

    when(citiesReaderRepository.getCities()).thenReturn(List.of(city1, city2));
    List<Suggestion> suggestions = suggestionService.getSuggestions(query, latitude, longitude);

    assertEquals(2, suggestions.size());
    assertEquals("Acton Vale", suggestions.get(0).getName());
    assertEquals("Abbotsford", suggestions.get(1).getName());
  }

  @Test
  public void testCalculateScore() {
    City city = new City();
    city.setLatitude(42.98339);
    city.setLongitude(-81.23304);
    Double latitude = 43.70011;
    Double longitude = -79.4163;

    DistanceUtils.calculateDistance(city.getLatitude(), city.getLongitude(), latitude, longitude);
    double score = suggestionService.calculateScore(city, latitude, longitude);
    assertEquals(0.005983154207457454, score, 100.0);
  }

  @Test
  public void testCalculateScoreWithoutCoordinates() {
    City city = new City();
    city.setLatitude(49.05798);
    city.setLongitude(-122.25257);

    double score = suggestionService.calculateScore(city, null, null);

    assertEquals(0.0, score, 0.001);
  }

  @Test
  public void testFindByNameContainingIgnoreCase() {
    CityDAO city1 = new CityDAO();
    city1.setId(1L);
    city1.setName("New York");
    city1.setAscii("New York");
    city1.setAltName("NYC");

    CityDAO city2 = new CityDAO();
    city2.setId(2L);
    city2.setName("Los Angeles");
    city2.setAscii("Los Angeles");
    city2.setAltName("LA");

    List<CityDAO> cityFiles = new ArrayList<>();
    cityFiles.add(city1);
    cityFiles.add(city2);

    when(citiesReaderRepository.getFileCities()).thenReturn(cityFiles);
    Set<City> result = suggestionService.findByNameContaining(cityFiles, "new");
    assertEquals(1, result.size());
    assertEquals("New York", result.stream().findFirst().get().getName());
  }

  @Test
  public void testMatchesQuery() {
    CityDAO city1 = new CityDAO();
    city1.setName("New York");
    city1.setAscii("New York");
    city1.setAltName("NYC");
    assertTrue(suggestionService.matchesQuery(city1, "new"));
    assertTrue(suggestionService.matchesQuery(city1, "york"));
    assertTrue(suggestionService.matchesQuery(city1, "nyc"));
    assertFalse(suggestionService.matchesQuery(city1, "los angeles"));
    assertFalse(suggestionService.matchesQuery(city1, "london"));
  }
}
