package com.memorizei.domain;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class BaseEntity {

    @Column(name = "data_insercao")
    private LocalDateTime dataInsercao;

    @Column(name = "data_edicao")
    private LocalDateTime dataEdicao;

    @PrePersist
    private void alterarDataEdicao() {
        this.dataEdicao = LocalDateTime.now();
    }

}
