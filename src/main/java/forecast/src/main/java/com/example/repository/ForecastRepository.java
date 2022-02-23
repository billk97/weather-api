package com.example.repository;

import com.example.domain.Forecast;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastRepository implements PanacheRepository<Forecast> {

//    public List<Forecast>
}
