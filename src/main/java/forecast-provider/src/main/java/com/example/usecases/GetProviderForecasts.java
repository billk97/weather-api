package com.example.usecases;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import com.example.repositories.ForecastProviderRepository;
import com.example.repositories.ForecastRepository;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetProviderForecasts {

    @Inject
    ForecastProviderRepository forecastProviderRepo;

    @Inject
    ForecastRepository forecastRepository;

    public List<Forecast> query(String providerId) {
        if(!StringUtils.isNumeric(providerId)) {
            throw new IllegalArgumentExceptionWithCode(
                    String.format("Provider with id: %s not found", providerId),
                    ErrorCode.INVALID_INPUT
            );
        }

        ForecastProvider provider = forecastProviderRepo.findById(Long.valueOf(providerId));

        if(provider == null) {
            throw new IllegalArgumentExceptionWithCode(String.format("Provider with id: %s not found", providerId), ErrorCode.PROVIDER_NOT_FOUND);
        }

        List<Forecast> forecasts = forecastRepository.findForecastByProvider(provider);
        return forecasts;
    }

}
