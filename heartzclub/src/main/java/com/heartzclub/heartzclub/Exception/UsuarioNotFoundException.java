package com.heartzclub.heartzclub.Exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long message) {
        super("Usuario não encontrado.");
    }

    public UsuarioNotFoundException(String message) {
        super("Email ou senha incorretas. Tente novamente");
    }
}