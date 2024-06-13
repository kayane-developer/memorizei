package com.memorizei.controller;

import com.memorizei.dto.CardDTO;
import com.memorizei.dto.PendenciaDTO;
import com.memorizei.dto.PendenciaResponseDTO;
import com.memorizei.model.entity.Card;
import com.memorizei.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("card")
public class CardController {

    private final CardService service;

    @PostMapping
    public Card cadastrarCard(@RequestBody @Valid CardDTO cardDTO) {
        return service.cadastrar(cardDTO);
    }

    @GetMapping("/pendencias/{idUsuario}")
    public List<PendenciaDTO> buscarCardsPendentesDoUsuario(@PathVariable Long idUsuario) {
        return service.buscarCardsPendentes(idUsuario);
    }

}
