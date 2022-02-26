package com.example.repository;

import com.example.domain.Forecast;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastRepository implements PanacheRepository<Forecast> {


    public List<Forecast> findCurrentForecastForUser(long locationId, Set<Long> servicesIds){
        System.out.println("yesterday: " + Instant.now().truncatedTo(ChronoUnit.DAYS).minus(1, ChronoUnit.DAYS));
        System.out.println("tomorrow: " + Instant.now().truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS));
        Map<String, Object> params = new HashMap<>();
        params.put("locationId", locationId);
        params.put("serviceIds", servicesIds);
        params.put("yesterday", Instant.now().truncatedTo(ChronoUnit.DAYS).minus(1, ChronoUnit.DAYS));
        params.put("tomorrow", Instant.now().truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS));
        return list("location_id = :locationId " +
            "and " +
            "forecast_provider_id in (:serviceIds) " +
            "and " +
            "(iso_time > :yesterday " +
            "and " +
            "iso_time < :tomorrow) ", params);
    }
}
