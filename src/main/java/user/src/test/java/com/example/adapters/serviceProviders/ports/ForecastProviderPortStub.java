package com.example.adapters.serviceProviders.ports;

import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;
import io.quarkus.arc.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Alternative
@Priority(1)
@ApplicationScoped
@RestClient
public class ForecastProviderPortStub implements  ForecastProviderPort {
    @Override
    public ServiceProviderDTO findForecastProviderById(Long forecastProvidersId) {
        return new ServiceProviderDTO(1l, "meteo", "ameteodescription");
    }
}