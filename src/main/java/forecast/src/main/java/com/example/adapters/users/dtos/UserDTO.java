package com.example.adapters.users.dtos;

import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import java.util.Set;

public record UserDTO(
    long userId,
    String username,
    long locationId,
    Set<Long> forecastProviderIds) {


    public void throwIfNotValid() {
        for(Long id : forecastProviderIds) {
            if(id == null) {
                throw new IllegalArgumentExceptionWithCode(
                    "Forecast Provider Id cannot be null",
                    ErrorCode.NULL_VALUE
                );
            }
        }
    }
}



