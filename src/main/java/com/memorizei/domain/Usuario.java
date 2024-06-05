package com.memorizei.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_usuario")
public class Usuario {

    @Id
    private Long id;

    private String nome;

    private String email;

    @Column(name = "email_confirmado")
    private Boolean emailConfirmado;

}
