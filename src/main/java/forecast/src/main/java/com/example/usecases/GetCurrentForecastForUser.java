package com.example.usecases;


import com.example.domain.Forecast;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetCurrentForecastForUser {

    @Inject
    private ForecastRepository forecastRepository;

    public Forecast query(String userId) {

        if(!StringUtils.isNumeric(userId)) {
            throw new IllegalArgumentExceptionWithCode(String.format("User with id: %s not found", userId), ErrorCode.INVALID_INPUT);
        }

        // find user
        // get users preferred location ids

        // findCurrentForecasts for all locations

        return null;
    }
}
