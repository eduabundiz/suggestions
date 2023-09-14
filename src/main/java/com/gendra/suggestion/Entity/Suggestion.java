package com.gendra.suggestion.Entity;

import lombok.Data;

@Data
public class Suggestion extends City{

    private double score;

    public Suggestion(Long id,String name, double latitude, double longitude, double score) {
        super.setId(id);
        super.setName(name);
        super.setLatitude(latitude);
        super.setLongitude(longitude);
        this.score = score;
    }
}
