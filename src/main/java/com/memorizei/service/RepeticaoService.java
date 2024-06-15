package com.memorizei.service;

import com.memorizei.converter.RepeticaoConverter;
import com.memorizei.dto.RepeticaoDTO;
import com.memorizei.model.repository.RepeticaoRepository;
import com.memorizei.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RepeticaoService {

    private final RepeticaoRepository repository;
    private final RepeticaoConverter converter;

    public void registrarRepeticaoCard(RepeticaoDTO repeticaoDTO) {
        log.info("Registrando repetição: {}", JsonUtils.toJson(repeticaoDTO));
        final var repeticao = converter.dtoToEntity(repeticaoDTO);
        repository.desativarRepeticoesDoCardDoUsuario(repeticaoDTO.getIdCard(), repeticaoDTO.getIdUsuario());
        repository.save(repeticao);
    }

}
