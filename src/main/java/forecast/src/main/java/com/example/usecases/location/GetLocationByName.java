package com.example.usecases.location;

import com.example.domain.Location;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.LocationRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetLocationByName {

    @Inject
    LocationRepository locationRepo;

    public Location query(String locationId) {
        if(!StringUtils.isNumeric(locationId)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Forecast with id: %s not found", locationId),
                ErrorCode.INVALID_INPUT
            );
        }
        Location location = locationRepo.findById(Long.valueOf(locationId));
        if(location == null) {
            throw new IllegalArgumentException(String.format("Location with id: %s doesnt exist",
                locationId));
        }
        return location;
    }
}
