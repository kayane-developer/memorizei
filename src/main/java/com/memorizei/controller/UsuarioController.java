package com.memorizei.controller;

import com.memorizei.dto.UsuarioDTO;
import com.memorizei.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("{id}")
    public UsuarioDTO buscarUsuario(@PathVariable Long id) {
        return service.buscarUsuario(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsuarioDTO cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return service.cadastrar(usuarioDTO);
    }

    @PostMapping("validar/{email}/{senha}")
    public void validarLogin(@PathVariable String email, @PathVariable String senha) {
        service.validarLoginUsuario(email, senha);
    }

}
