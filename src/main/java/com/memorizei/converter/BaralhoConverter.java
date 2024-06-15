package com.memorizei.converter;

import com.memorizei.dto.BaralhoDTO;
import com.memorizei.model.entity.Baralho;
import com.memorizei.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BaralhoConverter {

    private final UsuarioService usuarioService;

    public Baralho dtoToEntity(BaralhoDTO baralhoDTO) {
        final var baralho = new Baralho();
        baralho.setNome(baralhoDTO.getNome());
        baralho.setUsuario(usuarioService.buscarOuFalharPorId(baralhoDTO.getIdUsuario()));
        baralho.setPrivado(baralhoDTO.isPrivado());
        baralho.setAtivo(true);
        if(Objects.nonNull(baralhoDTO.getId())) {
            baralho.setId(baralhoDTO.getId());
        }

        return baralho;
    }

    public BaralhoDTO entityToDto(Baralho baralho) {
        final var baralhoDTO = new BaralhoDTO();
        baralhoDTO.setId(baralho.getId());
        baralhoDTO.setNome(baralho.getNome());
        baralhoDTO.setIdUsuario(baralho.getUsuario().getId());
        baralhoDTO.setPrivado(baralho.isPrivado());
        baralhoDTO.setDataInsercao(baralho.getDataInsercao());

        return baralhoDTO;
    }

}
