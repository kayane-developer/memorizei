package com.memorizei.model.repository;

import com.memorizei.model.entity.Repeticao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RepeticaoRepository extends JpaRepository<Repeticao, Long> {

    @Transactional
    @Modifying
    @Query(value = "update t_repeticao " +
            "set ativo = false " +
            "where fk_card = :idCard " +
            "and fk_usuario = :idUsuario", nativeQuery = true)
    int desativarRepeticoesDoCardDoUsuario(Long idCard, Long idUsuario);

}
