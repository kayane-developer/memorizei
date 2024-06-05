package com.memorizei.controller;

import com.memorizei.domain.Usuario;
import com.memorizei.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return service.buscarUsuario(id);
    }

}
