package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Object id dto.
 */
@XmlRootElement
public class ObjectIdDTO {
    private int objectId;

    /**
     * Instantiates a new Object id dto.
     */
    public ObjectIdDTO(){}

    /**
     * Instantiates a new Object id dto.
     *
     * @param objectId the object id
     */
    public ObjectIdDTO(int objectId) {
        this.objectId = objectId;
    }

    /**
     * Gets object id.
     *
     * @return the object id
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * Sets object id.
     *
     * @param objectId the object id
     */
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
