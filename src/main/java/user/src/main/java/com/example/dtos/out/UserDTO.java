package com.example.dtos.out;

import java.util.Set;

public record UserDTO(
    long id,
    String name,
    long locationId,
    Set<Long> forecastProviderIds) {

}