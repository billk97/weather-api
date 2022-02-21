package com.example.usecases;

import com.example.domain.Location;
import com.example.dtos.in.CreateLocationDTO;
import com.example.repository.LocationRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class CreateLocation {

    @Inject
    private LocationRepository locationRepo;

    @Transactional
    public long command(CreateLocationDTO dto) {
        if(dto.locationName() == null) {
            throw new IllegalArgumentException("Location name cannot be null");
        }
        Location location = new Location(dto);
        locationRepo.persist(location);
        return location.getLocationId();
    }
}
