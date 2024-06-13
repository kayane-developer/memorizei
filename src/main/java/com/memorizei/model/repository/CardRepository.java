package com.memorizei.model.repository;

import com.memorizei.dto.PendenciaResponseDTO;
import com.memorizei.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "select b.id as idBaralho, b.nome as nomeBaralho, c.id as idCard, c.pergunta as perguntaCard, " +
            "c.resposta as respostaCard, r.dificuldade as dificuldade, r.data_insercao as dataInsercao " +
            "from t_card c " +
            "join t_baralho b on b.id = c.fk_baralho " +
            "join t_repeticao r on c.id = r.fk_card " +
            "where r.fk_usuario = :idUsuario " +
            "and r.ativo = true", nativeQuery = true)
    List<PendenciaResponseDTO> buscarRepeticoesCardAtivosDoUsuario(Long idUsuario);

}
