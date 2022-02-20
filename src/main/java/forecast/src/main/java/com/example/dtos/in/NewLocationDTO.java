package com.example.dtos.in;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public record NewLocationDTO (String locationName, double latitude, double longitude) {
}
