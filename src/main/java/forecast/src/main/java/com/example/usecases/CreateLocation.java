package com.example.usecases;

import com.example.domain.Location;
import com.example.dtos.in.NewLocationDTO;
import com.example.repository.LocationRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class CreateLocation {

    @Inject
    private LocationRepository locationRepo;

    @Transactional
    public long command(NewLocationDTO dto) {
        Location location = new Location(dto);
        locationRepo.persist(location);
        return location.getLocationId();
    }
}
