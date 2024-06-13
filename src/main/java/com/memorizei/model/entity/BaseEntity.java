package com.memorizei.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "data_insercao", updatable = false)
    private LocalDateTime dataInsercao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataEdicao;

    private boolean ativo;

    @PreUpdate
    private void alterarDataEdicao() {
        this.dataEdicao = LocalDateTime.now();
    }

    @PrePersist
    private void setarDataInsercao() {
        this.dataInsercao = LocalDateTime.now();
        this.dataEdicao = LocalDateTime.now();
    }

}
