package com.example.repositories;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ForecastProviderRepository implements PanacheRepository<ForecastProvider> {
}
