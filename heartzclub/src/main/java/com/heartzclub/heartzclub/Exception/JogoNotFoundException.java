package com.heartzclub.heartzclub.Exception;

public class JogoNotFoundException extends RuntimeException {
    public JogoNotFoundException(Long message) {
        super("Jogo não encontrado");
    }
}