package com.example.usecases;

import com.example.adapters.ForecastPort;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

class UpdateForecastsTest {

    @InjectMock
    @RestClient
    ForecastPort forecastPort;


}