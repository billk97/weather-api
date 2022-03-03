package com.example.exceptions;

import com.example.enums.ErrorCode;
import java.time.Instant;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDetails {
    private Instant timestamp;
    private String message;
    private String details;
    private ErrorCode code;

    public ErrorDetails(Instant timestamp, String message, String details, ErrorCode code) {
        this(timestamp, message, details);
        this.code = code;
    }

    public ErrorDetails(Instant timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorDetails( String message, String details) {
        super();
        this.timestamp = Instant.now();
        this.message = message;
        this.details = details;
    }



    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}