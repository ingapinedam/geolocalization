package com.co.coordinadora.geolocalizacion.service;

import com.co.coordinadora.geolocalizacion.dto.CreateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.dto.GeolocationDTO;
import com.co.coordinadora.geolocalizacion.dto.UpdateGeolocationRequestDTO;

import java.util.List;

public interface GeolocationService {

    List<GeolocationDTO> getAllGeolocations(int page, int size);

    GeolocationDTO getGeolocationById(Long id);

    GeolocationDTO createGeolocation(CreateGeolocationRequestDTO createRequestDTO);

    GeolocationDTO updateGeolocation(Long id, UpdateGeolocationRequestDTO updateRequestDTO);

    void deleteGeolocation(Long id);
}
