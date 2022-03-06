package com.example.dtos.out;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Boolean response dto.
 */
public class BooleanResponseDTO {
    private boolean response;

    /**
     * Instantiates a new Boolean response dto.
     */
    public BooleanResponseDTO(){}

    /**
     * Instantiates a new Boolean response dto.
     *
     * @param response the response
     */
    public BooleanResponseDTO(boolean response) {
        this.response = response;
    }

    /**
     * Is response boolean.
     *
     * @return the boolean
     */
    public boolean isResponse() {
        return response;
    }

    /**
     * Sets response.
     *
     * @param response the response
     */
    public void setResponse(boolean response) {
        this.response = response;
    }
}
