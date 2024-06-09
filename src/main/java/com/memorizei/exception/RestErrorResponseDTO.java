package com.memorizei.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class RestErrorResponseDTO {

    private int statusCode;
    private String message;
    private LocalDateTime date;

}

