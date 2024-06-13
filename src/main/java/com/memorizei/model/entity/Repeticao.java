package com.memorizei.model.entity;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "t_repeticao")
public class Repeticao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_insercao", updatable = false)
    private LocalDateTime dataInsercao;

    private boolean ativo;

    private String dificuldade;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_card")
    private Card card;

    @PrePersist
    private void setarDataInsercao() {
        this.setDataInsercao(LocalDateTime.now());
    }

}
