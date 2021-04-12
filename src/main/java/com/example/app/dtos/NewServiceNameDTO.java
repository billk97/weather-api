package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type New service name dto.
 */
@XmlRootElement
public class NewServiceNameDTO {
    private String serviceName;

    private NewServiceNameDTO(){}

    /**
     * Instantiates a new New service name dto.
     *
     * @param serviceName the service name
     */
    public NewServiceNameDTO(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets service name.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
