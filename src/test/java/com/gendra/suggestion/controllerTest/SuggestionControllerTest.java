package com.gendra.suggestion.controllerTest;

import com.gendra.suggestion.Entity.Suggestion;
import com.gendra.suggestion.Entity.SuggestionResponse;
import com.gendra.suggestion.controller.SuggestionController;
import com.gendra.suggestion.service.SuggestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SuggestionControllerTest {
    @InjectMocks
    private SuggestionController suggestionController;

    @Mock
    private SuggestionService suggestionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSuggestions() {
        String query = "Londo";
        Double latitude = 43.70011;
        Double longitude = -79.4163;

        List<Suggestion> mockSuggestions = new ArrayList<>();
        Suggestion suggestion1 = new Suggestion(6058560L, "London",  42.98339,-81.23304, 1.0 );
        Suggestion suggestion2 = new Suggestion(4517009L, "London",  39.88645,-83.44825, 0.0018525093109628508 );
        mockSuggestions.add(suggestion1);
        mockSuggestions.add(suggestion2);

        when(suggestionService.getSuggestions(query, latitude, longitude)).thenReturn(mockSuggestions);
        SuggestionResponse response = suggestionController.getSuggestions(query, latitude, longitude);

        verify(suggestionService, times(1)).getSuggestions(query, latitude, longitude);

        assertEquals(mockSuggestions, response.getSuggestions());
    }
    @Test
    public void testGetSuggestionsBadRequest() {
        String query = null;
        Double latitude = 43.70011;
        Double longitude = -79.4163;

        when(suggestionService.getSuggestions(query, latitude, longitude))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> suggestionController.getSuggestions(query, latitude, longitude));
    }
}
