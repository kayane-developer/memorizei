package com.memorizei.converter;

import com.memorizei.dto.UsuarioDTO;
import com.memorizei.model.entity.Usuario;
import com.memorizei.utils.CredencialUtils;

import java.util.Objects;

public class UsuarioConverter {

    public Usuario dtoToEntity(UsuarioDTO usuarioDTO) {
        final var usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setHashSenha(CredencialUtils.encriptografarSenha(usuario.getHashSenha()));
        if (Objects.nonNull(usuarioDTO.getId())) {
            usuario.setId(usuario.getId());
            usuario.setDataInsercao(usuarioDTO.getDataInsercao());
        }
        return usuario;
    }

    public UsuarioDTO entityToDto(Usuario usuario) {
        final var usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setDataInsercao(usuario.getDataInsercao());
        return usuarioDTO;
    }

}
