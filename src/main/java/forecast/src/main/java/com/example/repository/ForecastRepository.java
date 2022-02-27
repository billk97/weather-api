package com.example.repository;

import com.example.domain.Forecast;
import com.example.domain.Location;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastRepository implements PanacheRepository<Forecast> {

    public List<Forecast> findCurrentForecastForUser(long locationId, Set<Long> servicesIds){
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

    public List<Forecast> findForecastByLocation(Location location) {
        return list("location", location);
    }

    public List<Forecast> findNextDaysForecast(long locationId, Set<Long> forecastProviderIds, int numberOfDays) {
        Map<String, Object> params = new HashMap<>();
        params.put("locationId", locationId);
        params.put("forecastProviderIds", forecastProviderIds);
        params.put("yesterday", Instant.now().truncatedTo(ChronoUnit.DAYS).minus(1, ChronoUnit.DAYS));
        params.put("until", Instant.now().truncatedTo(ChronoUnit.DAYS).plus(numberOfDays, ChronoUnit.DAYS));
        return list("location_id = :locationId " +
            "and " +
            "forecast_provider_id in (:serviceIds) " +
            "and " +
            "(iso_time > :yesterday " +
            "and " +
            "iso_time < :until) ", params);
    }

    public List<Forecast> findNextHoursForecast(long locationId, Set<Long> forecastProviderIds, int numberOfHours) {
        Map<String, Object> params = new HashMap<>();
        params.put("locationId", locationId);
        params.put("forecastProviderIds", forecastProviderIds);
        params.put("now", Instant.now());
        params.put("until", Instant.now().truncatedTo(ChronoUnit.DAYS).plus(numberOfHours, ChronoUnit.HOURS));
        return list("location_id = :locationId " +
            "and " +
            "forecast_provider_id in (:serviceIds) " +
            "and " +
            "(iso_time >= :now " +
            "and " +
            "iso_time <= :until) ", params);
    }
}
