package com.memorizei.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private LocalDateTime dataInsercao;
    @NotEmpty
    private String nome;
    @NotEmpty
    @Email
    private String email;
    private String senha;
    private boolean emailConfirmado;

}
