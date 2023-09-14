package com.gendra.suggestion.Entity;

import lombok.Data;

@Data
public class CityFile extends City{
    private Long id;
    private String name;
    private String ascii;
    private String altName;
    private String featClass;
    private String featCode;
    private String country;
    private String cc2;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
    private Integer population;
    private Integer elevation;
    private Integer dem;
    private String tz;
    private String modifiedAt;

    @Override
    public String toString() {
        return "CityFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ascii='" + ascii + '\'' +
                ", altName='" + altName + '\'' +
                ", latitude=" + super.getLatitude() +
                ", longitude=" + super.getLongitude() +
                ", featClass='" + featClass + '\'' +
                ", featCode='" + featCode + '\'' +
                ", country='" + country + '\'' +
                ", cc2='" + cc2 + '\'' +
                ", admin1='" + admin1 + '\'' +
                ", admin2='" + admin2 + '\'' +
                ", admin3='" + admin3 + '\'' +
                ", admin4='" + admin4 + '\'' +
                ", population=" + population +
                ", elevation=" + elevation +
                ", dem=" + dem +
                ", tz='" + tz + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                '}';
    }
}
