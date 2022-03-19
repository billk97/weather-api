package com.example.adapters.location.ports;

import com.example.adapters.location.dtos.LocationDTO;
import io.quarkus.arc.Priority;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Alternative
@Priority(1)
@ApplicationScoped
@RestClient
public class LocationStub implements LocationPort{

    @Override
    public Optional<LocationDTO> findLocationById(Long locationId) {
        return Optional.of(new LocationDTO(1L, "Larisa", 10.00, 12.00));
    }
}