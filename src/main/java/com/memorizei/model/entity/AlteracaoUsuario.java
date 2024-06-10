package com.memorizei.model.entity;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "t_alteracao_usuario")
public class AlteracaoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_insercao")
    private LocalDateTime dataInsercao;

    @Column(name = "usuario")
    private String nomeUsuario;

    @Column(name = "hash_senha")
    private String hashSenha;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

}
