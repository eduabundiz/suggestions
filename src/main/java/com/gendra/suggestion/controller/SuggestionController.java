package com.gendra.suggestion.controller;

import com.gendra.suggestion.entity.Suggestion;
import com.gendra.suggestion.entity.SuggestionResponse;
import com.gendra.suggestion.service.SuggestionService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controller class responsible for handling suggestions-related HTTP requests. */
@RestController
public class SuggestionController {
  private final SuggestionService suggestionService;

  /**
   * Constructs a new SuggestionController with the provided SuggestionService.
   *
   * @param suggestionService The SuggestionService used to retrieve suggestions.
   */
  @Autowired
  public SuggestionController(SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }

  /**
   * Handles GET requests to "/suggestions" and retrieves a list of suggestions based on the
   * provided query, latitude, and longitude.
   *
   * @param query The query string used to search for suggestions (e.g., "london").
   * @param latitude The latitude coordinate (optional) for location-based suggestions (e.g.,
   *     43.70011).
   * @param longitude The longitude coordinate (optional) for location-based suggestions (e.g.,
   *     -79.4163).
   * @return A SuggestionResponse containing a list of suggested cities.
   */
  @GetMapping("/suggestions")
  public SuggestionResponse getSuggestions(
      @Valid @Parameter(description = "query", example = "london") @RequestParam("q") String query,
      @Parameter(description = "Latitude", example = "43.70011")
          @RequestParam(value = "latitude", required = false)
          Double latitude,
      @Parameter(description = "Longitude", example = "-79.4163")
          @RequestParam(value = "longitude", required = false)
          Double longitude) {
    List<Suggestion> suggestions = suggestionService.getSuggestions(query, latitude, longitude);
    return new SuggestionResponse(suggestions);
  }
}
