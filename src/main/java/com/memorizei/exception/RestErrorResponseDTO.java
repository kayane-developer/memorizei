package com.memorizei.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class RestErrorResponseDTO {

    private int statusCode;
    private String message;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

}

