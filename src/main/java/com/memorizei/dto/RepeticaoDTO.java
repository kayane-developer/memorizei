package com.memorizei.dto;

import com.memorizei.model.enums.DificuldadeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RepeticaoDTO {

    @NotNull
    private DificuldadeEnum dificuldade;
    @NotNull
    private Long idCard;
    @NotNull
    private Long idUsuario;

}
