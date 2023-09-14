package com.gendra.suggestion.repository;

import com.gendra.suggestion.Entity.CityFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CitiesReaderRepository {
  String TSV_FILE_NAME = "cities_canada-usa.tsv";
  List<CityFile> cities = new ArrayList<>();

  public List<CityFile> getCities() {
    try {
      ClassPathResource resource = new ClassPathResource(TSV_FILE_NAME);
      InputStreamReader reader = new InputStreamReader(resource.getInputStream());
      BufferedReader bufferedReader = new BufferedReader(reader);

      String line;
      line = bufferedReader.readLine();
      // Lee y muestra cada línea del archivo
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split("\t");
        if (parts.length == 19) { // Verifica que haya 19 columnas en cada línea
          CityFile city = new CityFile();
          city.setId(Long.parseLong(parts[0]));
          city.setName(parts[1]);
          city.setAscii(parts[2]);
          city.setAltName(parts[3]);
          city.setLatitude(Double.parseDouble(parts[4]));
          city.setLongitude(Double.parseDouble(parts[5]));
          city.setFeatClass(parts[6]);
          city.setFeatCode(parts[7]);
          city.setCountry(parts[8]);
          city.setCc2(parts[9]);
          city.setAdmin1(parts[10]);
          city.setAdmin2(parts[11]);
          city.setAdmin3(parts[12]);
          city.setAdmin4(parts[13]);
          if (!parts[14].isEmpty()) {
            city.setPopulation(Integer.parseInt(parts[14]));
          }
          if (!parts[15].isEmpty()) {
            city.setElevation(Integer.parseInt(parts[15]));
          }
          if (!parts[16].isEmpty()) {
            city.setDem(Integer.parseInt(parts[16]));
          }
          city.setTz(parts[17]);
          city.setModifiedAt(parts[18]);

          cities.add(city);
        }
      }
      bufferedReader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return cities;
  }
}
