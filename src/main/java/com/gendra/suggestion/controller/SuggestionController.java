package com.gendra.suggestion.controller;

import com.gendra.suggestion.entity.Suggestion;
import com.gendra.suggestion.entity.SuggestionResponse;
import com.gendra.suggestion.service.SuggestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionController {
  private final SuggestionService suggestionService;

  @Autowired
  public SuggestionController(SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }

  @GetMapping("/suggestions")
  public SuggestionResponse getSuggestions(
      @RequestParam("q") String query,
      @RequestParam(value = "latitude", required = false) Double latitude,
      @RequestParam(value = "longitude", required = false) Double longitude) {
    List<Suggestion> suggestions = suggestionService.getSuggestions(query, latitude, longitude);
    return new SuggestionResponse(suggestions);
  }
}
