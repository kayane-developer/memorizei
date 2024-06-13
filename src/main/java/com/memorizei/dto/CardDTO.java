package com.memorizei.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CardDTO {

    private Long id;
    @NotEmpty
    private String pergunta;
    @NotEmpty
    private String resposta;
    @NotNull
    private Long idBaralho;

}
