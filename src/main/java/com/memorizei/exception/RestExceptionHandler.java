package com.memorizei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestErrorResponseDTO handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception) {
        return RestErrorResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .date(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorResponseDTO handleEntidadeNaoEncontrada(LoginException exception) {
        return RestErrorResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .date(LocalDateTime.now())
                .build();
    }

}
