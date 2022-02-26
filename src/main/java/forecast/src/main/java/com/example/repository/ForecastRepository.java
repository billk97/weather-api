package com.example.repository;

import com.example.domain.Forecast;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastRepository implements PanacheRepository<Forecast> {

    // SELECT * FROM weather_api.forecast where location_id = 1 and forecast_provider_id in (1, 2)  and  iso_time > DATE_SUB(CURDATE(), INTERVAL 1 YEAR);
//    public List<Forecast> findCurrentForecastForUser(long locationId, Set<Long> servicesIds){
//        Map<String, Object> params = new HashMap<>();
//        params.put("locationId", locationId);
//        params.put("serviceIds", servicesIds);
//    return list("SELECT f FROM Forecast f " +
//        "where location_id = :locationId " +
//        "and " +
//        "forecast_provider_id in (:serviceIds)", params);
//
//    }

    public List<Forecast> findCurrentForecastForUser(long locationId, Set<Long> servicesIds){
        Map<String, Object> params = new HashMap<>();
        params.put("locationId", locationId);
        params.put("serviceIds", servicesIds);
        return list("locationId = :locationId " +
            "and " +
            "forecast_provider_id in (:serviceIds)", params);

    }
}
