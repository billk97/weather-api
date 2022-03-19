package com.example.usecases;

import com.example.domain.ForecastProvider;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetSingleProvider {
    @Inject
    ForecastProviderRepository forecastProviderRepo;

    public ForecastProvider command(String providerId) {
        ForecastProvider provider = forecastProviderRepo.findById(Long.valueOf(providerId));

        if (provider == null) {
            throw new IllegalArgumentException(
                    String.format("Forecast provider with id: %d was not found", providerId)
            );
        }

        return provider;
    }
}
