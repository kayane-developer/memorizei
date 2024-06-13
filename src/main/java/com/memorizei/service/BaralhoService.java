package com.memorizei.service;

import com.memorizei.converter.BaralhoConverter;
import com.memorizei.dto.BaralhoDTO;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.model.entity.Baralho;
import com.memorizei.model.repository.BaralhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaralhoService {

    private final BaralhoRepository repository;
    private final BaralhoConverter converter;
    private final UsuarioService usuarioService;

    public Baralho cadastrar(BaralhoDTO baralhoDTO) {
        final var baralho = converter.dtoToEntity(baralhoDTO);
        return repository.save(baralho);
    }

    public Baralho buscarOuFalharPorId(Long idBaralho) {
        return repository.findById(idBaralho)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum baralho foi encontrado com o id informado"));
    }

    public List<Baralho> buscarPorIdUsuario(Long idUsuario) {
        usuarioService.buscarOuFalharPorId(idUsuario);
        return repository.buscarAtivosPorUsuario(idUsuario);
    }

}
