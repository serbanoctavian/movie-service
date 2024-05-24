package com.typesoft.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieException extends RuntimeException {

    private final int errorCode;
    private final String message;
}
