package com.co.coordinadora.geolocalizacion.model;

import com.co.coordinadora.geolocalizacion.dto.CreateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.dto.GeolocationDTO;
import com.co.coordinadora.geolocalizacion.dto.UpdateGeolocationRequestDTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(indexes = {@Index(columnList = "id"), @Index(columnList = "isActive")})
@Data
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double longitude;
    private Double latitude;
    private String clientName;
    private String userName;
    @Column(name = "is_active")
    private boolean isActive = true;

    public GeolocationDTO toDTO() {
        GeolocationDTO dto = new GeolocationDTO();
        dto.setLongitude(this.longitude);
        dto.setLatitude(this.latitude);
        dto.setName(this.clientName);
        dto.setUser(this.userName);
        return dto;
    }

    public static Geolocation fromDTO(GeolocationDTO dto) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLongitude(dto.getLongitude());
        geolocation.setLatitude(dto.getLatitude());
        geolocation.setClientName(dto.getName());
        geolocation.setUserName(dto.getUser());
        return geolocation;
    }

    public static Geolocation fromCreateDTO(CreateGeolocationRequestDTO createDTO) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLongitude(createDTO.getLongitude());
        geolocation.setLatitude(createDTO.getLatitude());
        geolocation.setClientName(createDTO.getName());
        geolocation.setUserName(createDTO.getUser());
        return geolocation;
    }

    public static Geolocation fromUpdateDTO(UpdateGeolocationRequestDTO updateDTO) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLongitude(updateDTO.getLongitude());
        geolocation.setLatitude(updateDTO.getLatitude());
        geolocation.setClientName(updateDTO.getName());
        geolocation.setUserName(updateDTO.getUser());
        return geolocation;
    }
}
