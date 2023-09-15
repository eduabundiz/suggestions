package com.gendra.suggestion.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CityFile extends City {
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

  public CityFile() {}

  public CityFile(String[] parts) {
    this.setId(Long.parseLong(parts[0]));
    this.setName(parts[1]);
    this.setAscii(parts[2]);
    this.setAltName(parts[3]);
    this.setLatitude(Double.parseDouble(parts[4]));
    this.setLongitude(Double.parseDouble(parts[5]));
    this.setFeatClass(parts[6]);
    this.setFeatCode(parts[7]);
    this.setCountry(parts[8]);
    this.setCc2(parts[9]);
    this.setAdmin1(parts[10]);
    this.setAdmin2(parts[11]);
    this.setAdmin3(parts[12]);
    this.setAdmin4(parts[13]);

    if (!parts[14].isEmpty()) {
      this.setPopulation(Integer.parseInt(parts[14]));
    }

    if (!parts[15].isEmpty()) {
      this.setElevation(Integer.parseInt(parts[15]));
    }

    if (!parts[16].isEmpty()) {
      this.setDem(Integer.parseInt(parts[16]));
    }

    this.setTz(parts[17]);
    this.setModifiedAt(parts[18]);
  }

  @Override
  public String toString() {
    return "CityFile{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", ascii='"
        + ascii
        + '\''
        + ", altName='"
        + altName
        + '\''
        + ", latitude="
        + super.getLatitude()
        + ", longitude="
        + super.getLongitude()
        + ", featClass='"
        + featClass
        + '\''
        + ", featCode='"
        + featCode
        + '\''
        + ", country='"
        + country
        + '\''
        + ", cc2='"
        + cc2
        + '\''
        + ", admin1='"
        + admin1
        + '\''
        + ", admin2='"
        + admin2
        + '\''
        + ", admin3='"
        + admin3
        + '\''
        + ", admin4='"
        + admin4
        + '\''
        + ", population="
        + population
        + ", elevation="
        + elevation
        + ", dem="
        + dem
        + ", tz='"
        + tz
        + '\''
        + ", modifiedAt='"
        + modifiedAt
        + '\''
        + '}';
  }
}
