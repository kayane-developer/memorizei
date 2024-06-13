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

    public List<PendenciaDTO> buscarCardsPendentes(Long idUsuario) {
        final var cardsAtivos = repository.buscarRepeticoesCardAtivosDoUsuario(idUsuario);
        final var pendenciasPorBaralho = filtrarCardsPendentes(cardsAtivos)
                .stream()
                .collect(Collectors.groupingBy(PendenciaResponseDTO::getIdBaralho));
        return pendenciasPorBaralho.entrySet().stream()
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

    private List<PendenciaResponseDTO> filtrarCardsPendentes(List<PendenciaResponseDTO> cards) {
        final var hoje = LocalDate.now();
        return cards.stream()
                .filter(card -> {
                    final var dataDeInsercao = card.getDataInsercao().toLocalDate();
                    final var dificuldade = card.getDificuldade();
                    final var temUmDia = dataDeInsercao.plusDays(1).isAfter(hoje) ||
                            dataDeInsercao.plusDays(1).isEqual(hoje);
                    final var temTresDias = dataDeInsercao.plusDays(3).isAfter(hoje) ||
                            dataDeInsercao.plusDays(3).isEqual(hoje);
                    final var temCincoDias = dataDeInsercao.plusDays(5).isAfter(hoje) ||
                            dataDeInsercao.plusDays(5).isEqual(hoje);
                    return (DificuldadeEnum.FACIL.name().equals(dificuldade) && temUmDia) ||
                            (DificuldadeEnum.MEDIO.name().equals(dificuldade) && temTresDias) ||
                            (DificuldadeEnum.DIFICIL.name().equals(dificuldade) && temCincoDias);
                })
                .collect(Collectors.toList());
    }

}
