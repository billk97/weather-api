package com.example.usecases.location;

import com.example.domain.Location;
import com.example.repository.LocationRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetAllLocations {

    @Inject
    LocationRepository locationRepo;

    public List<Location> query() {
        return locationRepo.findAll().stream().toList();
    }
}
