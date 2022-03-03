package com.example.usecases.forecast;

import com.example.domain.Forecast;
import com.example.dtos.out.ForecastSummaryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetUsersForecastForTheNextHoursSummary {

    @Inject
    GetUsersForecastForTheNextHours getUsersForecastForTheNextHours;

    public List<ForecastSummaryDTO> query(String userId, String numberOfHours) {
        List<ForecastSummaryDTO> forecastSummaries = new ArrayList<>();
        List<Forecast> forecasts = getUsersForecastForTheNextHours.query(userId, numberOfHours);
        for(Forecast f : forecasts) {
            forecastSummaries.add(new ForecastSummaryDTO(f.getLocation().getName(), f.getWeatherCategory(), f.getIsoTime()));
        }
        return forecastSummaries;
    }

}
