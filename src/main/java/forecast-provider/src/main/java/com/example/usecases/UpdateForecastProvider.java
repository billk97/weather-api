package com.example.usecases;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;
import jdk.jfr.Registered;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UpdateForecastProvider {

    @Inject
    ForecastProviderRepository forecastProviderRepo;

    public ObjectIdDTO command(String forecastProviderId, CreateForecastProviderDTO dto) {
        if (dto.name() == null) {
            throw new IllegalArgumentException("Forecast provider name cannot be empty");
        }

        ForecastProvider forecastProvider = forecastProviderRepo.findById(Long.valueOf(forecastProviderId));

        if (forecastProvider == null) {
            throw new IllegalArgumentException(
                    String.format("Forecast provider with id: %d was not found", forecastProviderId)
            );
        }

        forecastProvider.setName(dto.name());
        forecastProvider.setDescription(dto.description());

        forecastProviderRepo.persist(forecastProvider);
        return new ObjectIdDTO(forecastProvider.getId());
    }
}
