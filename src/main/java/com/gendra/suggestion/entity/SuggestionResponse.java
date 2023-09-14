package com.gendra.suggestion.entity;

import java.util.List;
import lombok.Data;

@Data
public class SuggestionResponse {
  List<Suggestion> suggestions;

  public SuggestionResponse(List<Suggestion> suggestions) {
    this.suggestions = suggestions;
  }
}
