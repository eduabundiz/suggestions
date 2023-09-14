package com.gendra.suggestion.controller;

import com.gendra.suggestion.Entity.Suggestion;
import com.gendra.suggestion.Entity.SuggestionResponse;
import com.gendra.suggestion.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }
    @GetMapping("")
    public SuggestionResponse getSuggestions(
            @RequestParam("q") String query,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude) {
        List<Suggestion> suggestions = suggestionService.getSuggestions(query, latitude, longitude);
        return new SuggestionResponse(suggestions);
    }
}
