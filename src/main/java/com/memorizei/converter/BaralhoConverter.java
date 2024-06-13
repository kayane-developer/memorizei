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

}
