package com.co.coordinadora.geolocalizacion.dto;

import lombok.Data;

@Data
public class GeolocationDTO {

    private Double longitude;
    private Double latitude;
    private String name;
    private String user;

}