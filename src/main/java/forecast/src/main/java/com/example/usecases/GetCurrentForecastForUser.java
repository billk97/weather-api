package com.example.usecases;


import com.example.adapters.users.dtos.UserDTO;
import com.example.adapters.users.ports.UsersPort;

import com.example.domain.Forecast;
import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@RequestScoped
public class GetCurrentForecastForUser {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private UsersPort usersPort;

    public List<Forecast> query(String userId) {
        if(!StringUtils.isNumeric(userId)) {
            throw new IllegalArgumentExceptionWithCode(
                String.format("User with id: %s not found", userId),
                ErrorCode.INVALID_INPUT
            );
        }
        UserDTO userDTO = usersPort.findUserById(userId)
            .orElseThrow(() ->
                new IllegalArgumentExceptionWithCode("", ErrorCode.USER_NOT_FOUND)
            );
        userDTO.throwIfNotValid();
        List<Forecast>
            forecasts = forecastRepo.findCurrentForecastForUser(userDTO.locationId(), userDTO.forecastProviderIds());
        // we need to return one forecast from each forecast provider the user selected
        return forecasts;
    }


}
