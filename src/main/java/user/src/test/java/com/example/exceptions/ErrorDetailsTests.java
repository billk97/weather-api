package com.example.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import com.example.enums.ErrorCode;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class ErrorDetailsTests {

    @Test
    void given_a_error_it_should_be_created_successfully() {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), "message", "");
        assertEquals("message", errorDetails.getMessage());

        ErrorDetails errorDetails2 = new ErrorDetails( "message", "");
        assertEquals("message", errorDetails2.getMessage());

        ErrorDetails errorDetails3 = new ErrorDetails( "message", "");
        errorDetails3.setDetails("details");
        errorDetails3.setMessage("message");
        errorDetails3.setCode(ErrorCode.INVALID_INPUT);
        errorDetails3.setTimestamp(Instant.now());
        assertEquals("details", errorDetails3.getDetails());
        assertEquals("message", errorDetails3.getMessage());
        assertEquals(ErrorCode.INVALID_INPUT, errorDetails3.getCode());
        assertNotNull(errorDetails3.getTimestamp());

    }

}