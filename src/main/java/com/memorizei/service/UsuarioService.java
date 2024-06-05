package com.memorizei.service;

import com.memorizei.domain.Usuario;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario buscarUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o id " + id));
    }

}
