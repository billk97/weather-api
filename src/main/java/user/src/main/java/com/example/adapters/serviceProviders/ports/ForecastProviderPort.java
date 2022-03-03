package com.example.adapters.serviceProviders.ports;

import com.example.adapters.location.dtos.LocationDTO;
import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;

import java.util.List;
import java.util.Optional;

public interface ForecastProviderPort {
    List<ServiceProviderDTO> findUserForecastProviders(List<Long> forecastProvidersIds);

}
