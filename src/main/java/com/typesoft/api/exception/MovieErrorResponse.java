package com.typesoft.api.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MovieErrorResponse {

    private final int errorCode;
    private final String message;
    private final String timestamp;

    public MovieErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
