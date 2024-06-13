package com.memorizei.dto;

import com.memorizei.model.enums.DificuldadeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PendenciaDTO {

    private Long idBaralho;
    private String nomeBaralho;
    private List<CardPendente> cardsPendentes;

    private Long cardsDificeis() {
        return cardsPendentes.stream()
                .filter(card -> DificuldadeEnum.DIFICIL.name().equals(card.getDificuldade()))
                .count();
    }

    private Long cardsFaceis() {
        return cardsPendentes.stream()
                .filter(card -> DificuldadeEnum.FACIL.name().equals(card.getDificuldade()))
                .count();
    }

    private Long cardsMedios() {
        return cardsPendentes.stream()
                .filter(card -> DificuldadeEnum.MEDIO.name().equals(card.getDificuldade()))
                .count();
    }

    @Getter
    @Setter
    @Builder
    public static class CardPendente {
        private Long idCard;
        private String perguntaCard;
        private String respostaCard;
        private String dificuldade;
    }

}
