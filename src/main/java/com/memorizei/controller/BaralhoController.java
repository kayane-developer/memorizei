package com.memorizei.controller;

import com.memorizei.dto.BaralhoDTO;
import com.memorizei.model.entity.Baralho;
import com.memorizei.service.BaralhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/baralho")
public class BaralhoController {

    private final BaralhoService service;

    @PostMapping
    public Baralho cadastrarBaralho(@RequestBody @Valid BaralhoDTO baralhoDTO) {
        return service.cadastrar(baralhoDTO);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Baralho>> buscarPorUsuario(@PathVariable Long idUsuario) {
        final var baralhos = service.buscarPorIdUsuario(idUsuario);
        return CollectionUtils.isEmpty(baralhos) ? ResponseEntity.noContent().build() : ResponseEntity.ok(baralhos);
    }

}
