package com.example.usecases;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CreateForecastProvider {

    @Inject
    ForecastProviderRepository forecastProviderRepo;


    public ObjectIdDTO command(CreateForecastProviderDTO dto) {
        if (dto.name() == null) {
            throw new IllegalArgumentException("Forecast provider name cannot be empty");
        }

        ForecastProvider forecastProvider = new ForecastProvider(dto.name(), dto.description());

        forecastProviderRepo.persist(forecastProvider);
        return new ObjectIdDTO(forecastProvider.getId());
    }
}
