package com.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return covertExceptionToResponse(e);
    }


    private Response covertExceptionToResponse(Exception exception) {

        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), exception.toString());
        exception.printStackTrace();

        if(exception instanceof  IllegalArgumentException ) {
            return Response
                .status(400)
                .entity(errorDetails)
                .build();
        }

        return Response.serverError().entity(errorDetails).build();
    }


}
