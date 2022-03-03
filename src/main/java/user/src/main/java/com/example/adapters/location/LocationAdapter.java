package com.example.adapters.location;

import com.example.adapters.location.dtos.LocationDTO;
import com.example.adapters.location.ports.LocationPort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class LocationAdapter implements LocationPort {
    @Override
    public Optional<LocationDTO> findLocationById(Long locationId) {
        return Optional.of(new LocationDTO(1L, "Larisa", 10.00, 12.00));
    }
}
