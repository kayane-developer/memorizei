package com.memorizei.converter;

import com.memorizei.dto.RepeticaoDTO;
import com.memorizei.model.entity.Repeticao;
import com.memorizei.service.CardService;
import com.memorizei.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepeticaoConverter {

    private final CardService cardService;
    private final UsuarioService usuarioService;

    public Repeticao dtoToEntity(RepeticaoDTO repeticaoDTO) {
        final var repeticao = new Repeticao();
        repeticao.setDificuldade(repeticaoDTO.getDificuldade().name());
        repeticao.setCard(cardService.buscarOuFalharPorId(repeticaoDTO.getIdCard()));
        repeticao.setUsuario(usuarioService.buscarOuFalharPorId(repeticaoDTO.getIdUsuario()));
        repeticao.setAtivo(true);
        return repeticao;
    }

}
