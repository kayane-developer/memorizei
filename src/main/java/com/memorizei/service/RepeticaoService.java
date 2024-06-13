package com.memorizei.service;

import com.memorizei.converter.RepeticaoConverter;
import com.memorizei.dto.RepeticaoDTO;
import com.memorizei.model.repository.RepeticaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepeticaoService {

    private final RepeticaoRepository repository;
    private final RepeticaoConverter converter;

    public void registrarRepeticaoCard(RepeticaoDTO repeticaoDTO) {
        final var repeticao = converter.dtoToEntity(repeticaoDTO);
        repository.desativarRepeticoesDoCardDoUsuario(repeticaoDTO.getIdCard(), repeticaoDTO.getIdUsuario());
        repository.save(repeticao);
    }

}
