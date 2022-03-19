package com.example.adapters.serviceProviders.dtos;

import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;

public record ServiceProviderDTO(Long forecastProviderId, String name, String description) {
    public void throwIfNotValid() {
        if (forecastProviderId == null) {
            throw new IllegalArgumentExceptionWithCode("forecast provider id can not be empty", ErrorCode.NULL_VALUE);
        }
    }
}
