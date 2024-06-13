package com.memorizei.model.repository;

import com.memorizei.model.entity.Baralho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaralhoRepository extends JpaRepository<Baralho, Long> {

    @Query(value = "SELECT * FROM t_baralho " +
            "WHERE fk_usuario = :idUsuario " +
            "AND ativo = true", nativeQuery = true)
    List<Baralho> buscarAtivosPorUsuario(Long idUsuario);

}
