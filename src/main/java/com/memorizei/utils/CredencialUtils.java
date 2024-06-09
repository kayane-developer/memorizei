package com.memorizei.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CredencialUtils {

    public static String encriptografarSenha(String senha) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        return bcrypt.encode(senha);
    }

    public static boolean isSenhaCorreta(String senha, String senhaEncriptada) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        return bcrypt.matches(senha, senhaEncriptada);
    }

    private CredencialUtils( ){}

}
