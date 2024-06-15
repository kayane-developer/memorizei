package com.memorizei.model.repository;

import com.memorizei.model.entity.Baralho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaralhoRepository extends JpaRepository<Baralho, Long> {

    List<Baralho> findByUsuarioIdAndAtivoTrue(Long idUsuario);

}
