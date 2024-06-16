package com.memorizei.controller;

import com.memorizei.dto.UsuarioDTO;
import com.memorizei.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("{id}")
    public UsuarioDTO buscarUsuario(@PathVariable Long id) {
        return service.buscarUsuarioDTO(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsuarioDTO cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return service.cadastrar(usuarioDTO);
    }

    @PostMapping("validar/{nomeUsuario}/{senha}")
    public UsuarioDTO validarLogin(@PathVariable String nomeUsuario, @PathVariable String senha) {
        return service.validarLoginUsuario(nomeUsuario, senha);
    }

}
