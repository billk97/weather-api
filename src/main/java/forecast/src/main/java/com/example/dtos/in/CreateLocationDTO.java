package com.example.dtos.in;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public record CreateLocationDTO(String locationName, double latitude, double longitude) {
}
