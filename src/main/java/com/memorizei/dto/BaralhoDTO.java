package com.memorizei.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class BaralhoDTO {

    private Long id;
    @NotEmpty
    private String nome;
    @NotNull
    private Long idUsuario;
    @NotNull
    private boolean privado;
    private LocalDateTime dataInsercao;

}
