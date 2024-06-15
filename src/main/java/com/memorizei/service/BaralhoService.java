package com.memorizei.service;

import com.memorizei.converter.BaralhoConverter;
import com.memorizei.dto.BaralhoDTO;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.model.entity.Baralho;
import com.memorizei.model.repository.BaralhoRepository;
import com.memorizei.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BaralhoService {

    private final BaralhoRepository repository;
    private final BaralhoConverter converter;
    private final UsuarioService usuarioService;

    public Baralho cadastrar(BaralhoDTO baralhoDTO) {
        log.info("Cadastrando baralho: {}", JsonUtils.toJson(baralhoDTO));
        final var baralho = converter.dtoToEntity(baralhoDTO);
        return repository.save(baralho);
    }

    public Baralho buscarOuFalharPorId(Long idBaralho) {
        return repository.findById(idBaralho)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum baralho foi encontrado com o id informado"));
    }

    public List<BaralhoDTO> buscarPorIdUsuario(Long idUsuario) {
        usuarioService.buscarOuFalharPorId(idUsuario);
        return repository.findByUsuarioIdAndAtivoTrue(idUsuario).stream()
                .map(converter::entityToDto)
                .collect(Collectors.toList());
    }

}
