package com.memorizei.service;

import com.memorizei.model.entity.AlteracaoUsuario;
import com.memorizei.model.entity.Usuario;
import com.memorizei.model.repository.AlteracaoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteracaoUsuarioService {

    private final AlteracaoUsuarioRepository repository;

    public void salvarAlteracaoDoUsuario(Usuario usuario) {
        final var alteracaoUsuario = new AlteracaoUsuario();
        alteracaoUsuario.setUsuario(usuario);
        alteracaoUsuario.setDataInsercao(LocalDateTime.now());
        alteracaoUsuario.setNome(usuario.getNome());
        alteracaoUsuario.setEmail(usuario.getEmail());
        alteracaoUsuario.setHashSenha(usuario.getHashSenha());

        repository.save(alteracaoUsuario);
    }

}
