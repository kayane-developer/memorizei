package com.memorizei.converter;

import com.memorizei.dto.CardDTO;
import com.memorizei.model.entity.Card;
import com.memorizei.service.BaralhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CardConverter {

    private final BaralhoService baralhoService;

    public Card dtoToEntity(CardDTO cardDTO) {
        final var card = new Card();
        card.setPergunta(cardDTO.getPergunta());
        card.setResposta(cardDTO.getResposta());
        card.setBaralho(baralhoService.buscarOuFalharPorId(cardDTO.getIdBaralho()));
        if (Objects.nonNull(cardDTO.getId())) {
            card.setId(cardDTO.getId());
        }

        return card;
    }

}
