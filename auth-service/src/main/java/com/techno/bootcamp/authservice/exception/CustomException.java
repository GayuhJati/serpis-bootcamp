package com.techno.bootcamp.authservice.exception;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CustomException extends RuntimeException {
    private int errorHttpStatus;
    private String errorStatus;

    public CustomException(String message, int errorHttpStatus, String errorStatus) {
        super(message);
        this.errorHttpStatus = errorHttpStatus;
        this.errorStatus = errorStatus;
    }

}
