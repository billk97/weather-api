package com.example.adapters.serviceProviders.ports;

import com.example.adapters.location.dtos.LocationDTO;
import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;

import java.util.Optional;

public interface ForecastProviderPort {
    Optional<ServiceProviderDTO> findForecastProviderById(Long forecastProviderId);

}
