package com.gendra.suggestion.repositoryTest;

import com.gendra.suggestion.Entity.CityFile;
import com.gendra.suggestion.repository.CitiesReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CitiesReaderRepositoryTest {

    @InjectMocks
    private CitiesReaderRepository citiesReaderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCities() {
        ClassPathResource resource = new ClassPathResource("cities_test.tsv");
        try {
            InputStream inputStream = resource.getInputStream();

            when(resource.getInputStream()).thenReturn(inputStream);

            List<CityFile> cities = citiesReaderRepository.getCities();
            assertEquals(2, cities.size());

            CityFile city1 = cities.get(0);
            assertEquals(1L, city1.getId());
            assertEquals("City1", city1.getName());
            assertEquals("Ascii1", city1.getAscii());

            CityFile city2 = cities.get(1);
            assertEquals(2L, city2.getId());
            assertEquals("City2", city2.getName());
            assertEquals("Ascii2", city2.getAscii());

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
