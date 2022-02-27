package com.example.usecases.location;

import com.example.domain.Forecast;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class DeleteForecastById {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private ForecastRatingRepository forecastRatingRepo;


    public void command(String id) {
        if(!StringUtils.isNumeric(id)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Forecast with id: %s not found", id),
                ErrorCode.INVALID_INPUT
            );
        }
        long forecastId = Long.valueOf(id);
        Forecast forecast = forecastRepo.findById(forecastId);
        if(forecast == null) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Forecast with id: %d was not found", forecastId),
                ErrorCode.FORECAST_NOT_FOUND
            );
        }
        forecastRatingRepo.deleteAllByForecast(forecast);
        forecastRepo.deleteById(forecastId);
    }
}
