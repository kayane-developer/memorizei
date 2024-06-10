package com.memorizei.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "data_insercao")
    private LocalDateTime dataInsercao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataEdicao;

    private boolean ativo;

    @PrePersist
    private void alterarDataEdicao() {
        this.dataEdicao = LocalDateTime.now();
    }

}
