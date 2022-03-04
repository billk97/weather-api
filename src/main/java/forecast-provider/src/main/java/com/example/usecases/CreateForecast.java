package com.example.usecases;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;
import com.example.repositories.ForecastRepository;
import com.example.repositories.LocationRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CreateForecast {

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    LocationRepository locationRepo;

    @Inject
    ForecastProviderRepository forecastProviderRepo;

    public ObjectIdDTO command(CreateForecastDTO dto) {

        Location location = locationRepo.findById(dto.locationId());

        if(location == null) {
            throw new IllegalArgumentException(
                    String.format("Location with id: %d was not found", dto.locationId())
            );
        }

        ForecastProvider forecastProvider = forecastProviderRepo.findById(dto.forecastProviderId());

        if(forecastProvider == null) {
            throw new IllegalArgumentException(
                    String.format("Forecast Provider with id: %d was not found", dto.forecastProviderId())
            );
        }

        Forecast forecast = new Forecast(dto, location, forecastProvider);
        forecastRepo.persist(forecast);
        return new ObjectIdDTO(forecast.getId());
    }
}
