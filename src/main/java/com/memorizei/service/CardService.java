package com.memorizei.service;

import com.memorizei.converter.CardConverter;
import com.memorizei.dto.CardDTO;
import com.memorizei.dto.PendenciaDTO;
import com.memorizei.dto.PendenciaResponseDTO;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.model.entity.Card;
import com.memorizei.model.enums.DificuldadeEnum;
import com.memorizei.model.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardConverter converter;
    private final CardRepository repository;

    public Card cadastrar(CardDTO cardDTO) {
        final var card = converter.dtoToEntity(cardDTO);
        return repository.save(card);
    }

    public Card buscarOuFalharPorId(Long idCard) {
        return repository.findById(idCard)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Card não encontrado com o id informado"));
    }

    public List<PendenciaDTO> buscarCardsDoUsuario(Long idUsuario) {
        final var cards = repository.buscarCardsAtivosDoUsuario(idUsuario);
        return separarPorBaralho(cards);
    }

    public List<PendenciaDTO> buscarCardsPendentes(Long idUsuario) {
        final var cardsAtivos = repository.buscarRepeticoesCardAtivosDoUsuario(idUsuario);
        final var cardsPendentes = filtrarCardsPendentes(cardsAtivos);
        return separarPorBaralho(cardsPendentes);
    }

    private List<PendenciaResponseDTO> filtrarCardsPendentes(List<PendenciaResponseDTO> cards) {
        final var hoje = LocalDate.now();
        return cards.stream()
                .filter(card -> {
                    final var dataDeInsercao = card.getDataInsercao().toLocalDate();
                    final var diferenca = dataDeInsercao.until(hoje, ChronoUnit.DAYS);
                    final var dificuldade = card.getDificuldade();
                    return (DificuldadeEnum.FACIL.name().equals(dificuldade) && (diferenca >= 5)) ||
                            (DificuldadeEnum.MEDIO.name().equals(dificuldade) && (diferenca >= 3)) ||
                            (DificuldadeEnum.DIFICIL.name().equals(dificuldade) && (diferenca >= 1));
                })
                .collect(Collectors.toList());
    }

    private List<PendenciaDTO> separarPorBaralho(List<PendenciaResponseDTO> cards) {
        final var cardsPorBaralho = cards.stream()
                .collect(Collectors.groupingBy(PendenciaResponseDTO::getIdBaralho));
        return cardsPorBaralho.entrySet().stream()
                .map(entry -> {
                    final var idBaralho = entry.getKey();
                    final var nomeBaralho = entry.getValue().get(0).getNomeBaralho();
                    final var cardsPendentes = entry.getValue().stream()
                            .map(response -> PendenciaDTO.CardPendente.builder()
                                    .idCard(response.getIdCard())
                                    .perguntaCard(response.getPerguntaCard())
                                    .respostaCard(response.getRespostaCard())
                                    .dificuldade(response.getDificuldade())
                                    .build())
                            .collect(Collectors.toList());
                    return new PendenciaDTO(idBaralho, nomeBaralho, cardsPendentes);
                }).collect(Collectors.toList());
    }

}
