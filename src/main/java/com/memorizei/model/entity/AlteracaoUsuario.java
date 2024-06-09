package com.memorizei.model.entity;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "t_alteracao_usuario")
public class AlteracaoUsuario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean ativo;

    private String nome;

    private String email;

    @Column(name = "hash_senha")
    private String hashSenha;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

}
