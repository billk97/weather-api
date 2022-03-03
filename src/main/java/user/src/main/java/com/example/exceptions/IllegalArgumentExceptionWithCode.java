package com.example.exceptions;


import com.example.enums.ErrorCode;

public class IllegalArgumentExceptionWithCode extends IllegalArgumentException implements ExceptionWithCode {
    private ErrorCode errorCode;

    public IllegalArgumentExceptionWithCode(String exceptionMessage , ErrorCode errorCode) {
        super(exceptionMessage);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
