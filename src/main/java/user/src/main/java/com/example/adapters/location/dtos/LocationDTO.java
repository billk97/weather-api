package com.example.adapters.location.dtos;

import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;


public record LocationDTO(Long locationId, String name, double latitude, double longitude ) {
    public void throwIfNotValid() {
        if(locationId == null) {
            throw new IllegalArgumentExceptionWithCode("Location Id can not be empty", ErrorCode.NULL_VALUE);
        }
    }
}
