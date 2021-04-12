package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WarningMessageDTO {
    private String warningMessage;

    public WarningMessageDTO(){}

    public WarningMessageDTO(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }
}
