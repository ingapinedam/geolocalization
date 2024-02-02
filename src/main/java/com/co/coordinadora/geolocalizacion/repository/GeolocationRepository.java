package com.co.coordinadora.geolocalizacion.repository;

import com.co.coordinadora.geolocalizacion.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {

    Page<Geolocation> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT g FROM Geolocation g WHERE g.id = :id AND g.isActive = true")
    Optional<Geolocation> findByIdAndIsActiveTrue(Long id);


}