package com.example.usecases.forecast;


import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetAllForecastByLocation {

    @Inject
    LocationRepository locationRepo;
    @Inject
    ForecastRepository forecastRepo;

    public List<Forecast> query(String locationId) {
        if(!StringUtils.isNumeric(locationId)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Location with id: %s not found", locationId),
                ErrorCode.INVALID_INPUT
            );
        }
        Location location = locationRepo.findById(Long.valueOf(locationId));
        if(location == null) {
            throw new IllegalArgumentExceptionWithCode(String.format("Location with id: %s not found", locationId), ErrorCode.LOCATION_NOT_FOUND);
        }
        List<Forecast> forecasts = forecastRepo.findForecastByLocation(location);
        return forecasts;
    }
}
