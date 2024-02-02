package com.co.coordinadora.geolocalizacion.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateGeolocationRequestDTO {

    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "User cannot be blank")
    private String user;
}
