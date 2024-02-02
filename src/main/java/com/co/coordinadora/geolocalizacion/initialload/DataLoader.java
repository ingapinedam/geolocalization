package com.co.coordinadora.geolocalizacion.initialload;

import com.co.coordinadora.geolocalizacion.dto.CreateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DataLoader implements CommandLineRunner {

    private final GeolocationService geolocationService;

    @Autowired
    public DataLoader(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @Override
    public void run(String... args) {
        try {
            // Ruta del archivo en resources
            String filePath = "sample.csv";

            // Obtén el InputStream del archivo
            InputStream inputStream = new ClassPathResource(filePath).getInputStream();

            // Crea un BufferedReader para leer el archivo
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Parsea cada línea del archivo y crea objetos DTO
                    String[] parts = line.split(",");
                    CreateGeolocationRequestDTO geolocationDTO = new CreateGeolocationRequestDTO();
                    geolocationDTO.setLongitude(Double.parseDouble(parts[0]));
                    geolocationDTO.setLatitude(Double.parseDouble(parts[1]));
                    geolocationDTO.setName(parts[2]);
                    geolocationDTO.setUser(parts[3]);

                    // Llama al servicio para crear la geolocalización
                    geolocationService.createGeolocation(geolocationDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}