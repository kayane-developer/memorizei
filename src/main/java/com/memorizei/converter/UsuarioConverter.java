package com.memorizei.converter;

import com.memorizei.dto.UsuarioDTO;
import com.memorizei.model.entity.Usuario;
import com.memorizei.utils.CredencialUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class UsuarioConverter {

    public Usuario dtoToEntity(UsuarioDTO usuarioDTO) {
        final var usuario = new Usuario();
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setAtivo(true);
        usuario.setHashSenha(CredencialUtils.encriptografarSenha(usuarioDTO.getSenha()));
        usuario.setDataInsercao(LocalDateTime.now());
        if (Objects.nonNull(usuarioDTO.getId())) {
            usuario.setId(usuario.getId());
            usuario.setDataInsercao(usuarioDTO.getDataInsercao());
        }
        return usuario;
    }

    public UsuarioDTO entityToDto(Usuario usuario) {
        final var usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setDataInsercao(usuario.getDataInsercao());
        return usuarioDTO;
    }

}
