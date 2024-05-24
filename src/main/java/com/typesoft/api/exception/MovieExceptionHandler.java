package com.typesoft.api.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieExceptionHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MovieErrorResponse> handleFeignException(FeignException e) {
        MovieErrorResponse movieErrorResponse = new MovieErrorResponse(e.status(), e.getMessage());
        return new ResponseEntity<>(movieErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MovieException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MovieErrorResponse> handleMovieException(MovieException e) {
        MovieErrorResponse movieErrorResponse = new MovieErrorResponse(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(movieErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
