package com.memorizei.service;

import com.memorizei.converter.UsuarioConverter;
import com.memorizei.dto.UsuarioDTO;
import com.memorizei.exception.EntidadeNaoEncontradaException;
import com.memorizei.exception.LoginException;
import com.memorizei.model.repository.UsuarioRepository;
import com.memorizei.utils.CredencialUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioConverter converter;
    private final UsuarioRepository repository;
    private final AlteracaoUsuarioService alteracaoUsuarioService;

    public UsuarioDTO buscarUsuario(Long id) {
        return repository.findById(id)
                .map(converter::entityToDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o id " + id));
    }

    public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO) {
        repository.findByEmailIgnoreCase(usuarioDTO.getEmail())
                .ifPresent(usuarioExistente -> {
                    throw new LoginException("Já existe um usuário cadastrado com o email informado");
                });
        final var usuario = converter.dtoToEntity(usuarioDTO);
        alteracaoUsuarioService.salvarAlteracaoDoUsuario(usuario);
        return converter.entityToDto(repository.save(usuario));
    }

    public void validarLoginUsuario(String email, String senha) {
        final var usuario = repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não cadastrado"));
        if (CredencialUtils.isSenhaCorreta(senha, usuario.getHashSenha())) {
            throw new LoginException("Senha incorreta");
        }
    }

}
