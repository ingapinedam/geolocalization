package com.co.coordinadora.geolocalizacion.controller;

import com.co.coordinadora.geolocalizacion.dto.CreateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.dto.GeolocationDTO;
import com.co.coordinadora.geolocalizacion.dto.UpdateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/marcadores")
public class GeolocationController {

    @Autowired
    private GeolocationService geolocationService;

    @GetMapping
    public ResponseEntity<List<GeolocationDTO>> getAllGeolocations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<GeolocationDTO> geolocations = geolocationService.getAllGeolocations(page, size);
        return ResponseEntity.ok(geolocations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeolocationDTO> getGeolocationById(@PathVariable @NotNull Long id) {
        GeolocationDTO geolocation = geolocationService.getGeolocationById(id);
        return (geolocation != null) ? ResponseEntity.ok(geolocation) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GeolocationDTO> createGeolocation(@Valid @RequestBody CreateGeolocationRequestDTO createRequestDTO) {
        GeolocationDTO createdGeolocation = geolocationService.createGeolocation(createRequestDTO);
        return ResponseEntity.ok(createdGeolocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeolocationDTO> updateGeolocation(
            @PathVariable @NotNull Long id,
            @Valid @RequestBody UpdateGeolocationRequestDTO updateRequestDTO
    ) {
        GeolocationDTO existingGeolocation = geolocationService.getGeolocationById(id);
        if (existingGeolocation == null) {
            return ResponseEntity.notFound().build();
        }
        if (!updateRequestDTO.getName().equals(existingGeolocation.getName())
                || !updateRequestDTO.getUser().equals(existingGeolocation.getUser())) {
            return ResponseEntity.badRequest().body(null);
        }
        GeolocationDTO updatedGeolocation = geolocationService.updateGeolocation(id, updateRequestDTO);
        return ResponseEntity.ok(updatedGeolocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeolocation(@PathVariable @NotNull Long id) {
        geolocationService.deleteGeolocation(id);
        return ResponseEntity.noContent().build();
    }
}
