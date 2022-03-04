package com.example.usecases;

import com.example.adapters.forecasts.ForecastPort;
import com.example.domain.Forecast;
import com.example.repositories.ForecastRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class UpdateForecasts {

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    @RestClient
    ForecastPort forecastPort;

    public void command() {
        List<Forecast> forecasts = forecastRepo.findAll().list();

        for(Forecast forecast : forecasts) {
            forecastPort.provideProviderForecasts(forecast);
        }
    }
}
