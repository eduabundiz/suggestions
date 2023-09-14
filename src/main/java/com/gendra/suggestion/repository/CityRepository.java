package com.gendra.suggestion.repository;

import com.gendra.suggestion.Entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CityRepository {
    List<City> cities;
    public List<City> findByNameContainingIgnoreCase(String query){

        return  cities;
    }
}
