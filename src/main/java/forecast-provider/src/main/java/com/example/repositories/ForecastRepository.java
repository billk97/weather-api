package com.example.repositories;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ForecastRepository implements PanacheRepository<Forecast> {

    public List<Forecast> findForecastByProvider(ForecastProvider provider) {
        return list("forecastProvider", provider);
    }
}
