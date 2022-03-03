package com.example.adapters.serviceProviders;

import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;
import com.example.adapters.serviceProviders.ports.ForecastProviderPort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ForecastProviderAdapter implements ForecastProviderPort {
    @Override
    public Optional<ServiceProviderDTO> findForecastProviderById(Long forecastProviderId){
        return Optional.of(new ServiceProviderDTO(1l, "meteo", "ameteodescription"));
    }
}
