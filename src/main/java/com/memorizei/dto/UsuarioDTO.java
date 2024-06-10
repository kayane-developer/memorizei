package com.memorizei.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private LocalDateTime dataInsercao;
    @NotEmpty
    private String usuario;
    @NotEmpty
    private String senha;

}
