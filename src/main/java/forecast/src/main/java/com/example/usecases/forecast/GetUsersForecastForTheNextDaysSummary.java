package com.example.usecases.forecast;

import com.example.domain.Forecast;
import com.example.dtos.out.ForecastSummaryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetUsersForecastForTheNextDaysSummary {

    @Inject
    private  GetUsersForecastForTheNextDays getUsersForecastForTheNextDays;

    public List<ForecastSummaryDTO> query(String userId, String numberOfDays) {
        List<ForecastSummaryDTO> forecastSummaries = new ArrayList<>();
        List<Forecast> forecasts = getUsersForecastForTheNextDays.query(userId, numberOfDays);
        for(Forecast f : forecasts) {
            forecastSummaries.add(new ForecastSummaryDTO(f.getLocation().getName(), f.getWeatherCategory(), f.getIsoTime()));
        }
        return forecastSummaries;
    }

}
