package com.co.coordinadora.geolocalizacion.service;

import com.co.coordinadora.geolocalizacion.dto.CreateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.dto.GeolocationDTO;
import com.co.coordinadora.geolocalizacion.dto.UpdateGeolocationRequestDTO;
import com.co.coordinadora.geolocalizacion.model.Geolocation;
import com.co.coordinadora.geolocalizacion.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    @Autowired
    private GeolocationRepository geolocationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GeolocationDTO> getAllGeolocations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Geolocation> geolocationPage = geolocationRepository.findByIsActiveTrue(pageable);
        List<GeolocationDTO> geolocationDTOs = geolocationPage.getContent().stream()
                .map(Geolocation::toDTO)
                .collect(Collectors.toList());
        return geolocationDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public GeolocationDTO getGeolocationById(Long id) {
        Geolocation geolocation = geolocationRepository.findByIdAndIsActiveTrue(id).orElse(null);
        return (geolocation != null) ? geolocation.toDTO() : null;
    }

    @Override
    @Transactional
    public GeolocationDTO createGeolocation(CreateGeolocationRequestDTO createRequestDTO) {
        Geolocation geolocation = Geolocation.fromCreateDTO(createRequestDTO);
        geolocation.setActive(true);
        geolocation = geolocationRepository.save(geolocation);
        return geolocation.toDTO();
    }

    @Override
    @Transactional
    public GeolocationDTO updateGeolocation(Long id, UpdateGeolocationRequestDTO updateRequestDTO) {
        Geolocation existingGeolocation = geolocationRepository.findById(id).orElse(null);

        if (existingGeolocation != null) {
            Geolocation updatedGeolocation = Geolocation.fromUpdateDTO(updateRequestDTO);

            existingGeolocation.setLongitude(updatedGeolocation.getLongitude());
            existingGeolocation.setLatitude(updatedGeolocation.getLatitude());
            existingGeolocation.setClientName(updatedGeolocation.getClientName());
            existingGeolocation.setUserName(updatedGeolocation.getUserName());

            existingGeolocation = geolocationRepository.save(existingGeolocation);
            return existingGeolocation.toDTO();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteGeolocation(Long id) {
        Geolocation geolocation = geolocationRepository.findById(id).orElse(null);
        if (geolocation != null) {
            geolocation.setActive(false);
            // No es necesario llamar a save() ya que la entidad está siendo gestionada por JPA y los cambios se reflejarán automáticamente.
        }
    }
}
