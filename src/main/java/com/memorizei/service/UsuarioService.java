package com.memorizei.service;

import com.memorizei.converter.UsuarioConverter;
import com.memorizei.dto.UsuarioDTO;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.exception.LoginException;
import com.memorizei.model.entity.Usuario;
import com.memorizei.model.repository.UsuarioRepository;
import com.memorizei.utils.CredencialUtils;
import com.memorizei.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioConverter converter;
    private final UsuarioRepository repository;
    private final AlteracaoUsuarioService alteracaoUsuarioService;

    public UsuarioDTO buscarUsuarioDTO(Long id) {
        return repository.findById(id)
                .map(converter::entityToDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o id " + id));
    }

    public Usuario buscarOuFalharPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o id " + id));
    }

    public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO) {
        repository.findByUsuario(usuarioDTO.getUsuario())
                .ifPresent(usuarioExistente -> {
                    throw new LoginException("Já existe um usuário cadastrado com o nome informado");
                });
        final var usuarioSalvo = repository.save(converter.dtoToEntity(usuarioDTO));
        log.info("Usuário cadastrado: {}", JsonUtils.toJson(usuarioSalvo));
        alteracaoUsuarioService.salvarAlteracaoDoUsuario(usuarioSalvo);
        return converter.entityToDto(usuarioSalvo);
    }

    public void validarLoginUsuario(String nomeUsuario, String senha) {
        final var usuario = repository.findByUsuario(nomeUsuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não cadastrado"));
        if (!CredencialUtils.isSenhaCorreta(senha, usuario.getHashSenha())) {
            throw new LoginException("Senha incorreta");
        }
    }

}
