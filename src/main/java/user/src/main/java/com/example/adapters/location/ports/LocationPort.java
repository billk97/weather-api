package com.example.adapters.location.ports;

import com.example.adapters.location.dtos.LocationDTO;

import java.util.Optional;

public interface LocationPort {
    Optional<LocationDTO> findLocationById(Long locationId);
}
