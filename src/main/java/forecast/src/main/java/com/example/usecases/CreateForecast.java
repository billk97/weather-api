package com.example.usecases;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class CreateForecast {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private LocationRepository locationRepo;


    @Transactional
    public ObjectIdDTO command(CreateForecastDTO dto) {
        if(dto.forecastTime() == null) {
            throw new IllegalArgumentException("Forecast time can't be null");
        }
        Location location = locationRepo.findById(dto.locationId());
        if(location == null) {
            throw new IllegalArgumentException(
                String.format("Location with id: %d was not found", dto.locationId())
            );
        }
        Forecast forecast = new Forecast(dto, location);
        forecastRepo.persist(forecast);
        return new ObjectIdDTO(forecast.getForecastId());
    }

}
