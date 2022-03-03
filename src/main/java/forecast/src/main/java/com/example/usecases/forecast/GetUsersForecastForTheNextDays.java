package com.example.usecases.forecast;

import com.example.adapters.users.dtos.UserDTO;
import com.example.adapters.users.ports.UserPort;
import com.example.domain.Forecast;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RequestScoped
public class GetUsersForecastForTheNextDays {

    @Inject
    ForecastRepository forecastRepo;
    @Inject
    @RestClient
    UserPort userPort;

    public List<Forecast> query(String userId, String numberOfDays) {
        if(!StringUtils.isNumeric(userId) || !StringUtils.isNumeric(numberOfDays)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("Forecast with id: %s not found", userId),
                ErrorCode.INVALID_INPUT
            );
        }

        UserDTO user = userPort.findUserById(userId).orElseThrow(() ->
            new IllegalArgumentExceptionWithCode("User not found", ErrorCode.USER_NOT_FOUND)
        );
        user.throwIfNotValid();
        return  forecastRepo.findNextDaysForecast(user.locationId(), user.forecastProviderIds(), Integer.valueOf(numberOfDays));
    }

}
