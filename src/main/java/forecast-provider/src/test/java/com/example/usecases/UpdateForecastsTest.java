package com.example.usecases;

import com.example.adapters.forecasts.ForecastPort;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import static org.junit.jupiter.api.Assertions.*;

class UpdateForecastsTest {

    @InjectMock
    @RestClient
    ForecastPort forecastPort;


}