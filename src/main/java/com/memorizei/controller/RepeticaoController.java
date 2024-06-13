package com.memorizei.controller;

import com.memorizei.dto.RepeticaoDTO;
import com.memorizei.service.RepeticaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("repeticao")
@RequiredArgsConstructor
public class RepeticaoController {

    private final RepeticaoService service;

    @PostMapping
    public void registrarRepeticao(@RequestBody @Valid RepeticaoDTO repeticaoDTO) {
        service.registrarRepeticaoCard(repeticaoDTO);
    }

}
