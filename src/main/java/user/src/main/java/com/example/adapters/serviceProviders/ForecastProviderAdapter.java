package com.example.adapters.serviceProviders;

import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;
import com.example.adapters.serviceProviders.ports.ForecastProviderPort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class ForecastProviderAdapter implements ForecastProviderPort {
    @Override
    public List<ServiceProviderDTO> findUserForecastProviders(List<Long> forecastProvidersIds){
        return Arrays.asList(new ServiceProviderDTO(1l, "meteo", "ameteodescription"));
    }
}
