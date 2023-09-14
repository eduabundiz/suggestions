package com.gendra.suggestion.Entity;

import lombok.Data;

import java.util.List;
@Data
public class SuggestionResponse {
    List<Suggestion> suggestions;

    public SuggestionResponse(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
