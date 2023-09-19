package com.gendra.suggestion.entity;

import java.util.List;
import lombok.Data;

/** A data class representing a response containing a list of suggestions. */
@Data
public class SuggestionResponse {

  /** The list of suggestions included in the response. */
  private List<Suggestion> suggestions;

  /**
   * Constructs a new SuggestionResponse with the provided list of suggestions.
   *
   * @param suggestions The list of suggestions to include in the response.
   */
  public SuggestionResponse(List<Suggestion> suggestions) {
    this.suggestions = suggestions;
  }
}
