package com.example.repositories;

import com.example.domain.ForecastProvider;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastProviderRepository implements PanacheRepository<ForecastProvider> {
}
