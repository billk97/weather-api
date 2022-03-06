package com.example.adapters.forecasts;

import com.example.domain.Forecast;
import io.quarkus.arc.Priority;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Optional;

@Alternative
@Priority(1)
@ApplicationScoped
@RestClient
public class ForecastStub implements ForecastPort{

    @Override
    public void provideProviderForecasts(Forecast forecast) {}

}
