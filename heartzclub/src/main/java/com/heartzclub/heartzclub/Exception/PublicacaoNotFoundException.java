package com.heartzclub.heartzclub.Exception;

public class PublicacaoNotFoundException extends RuntimeException {

    public PublicacaoNotFoundException(Long message) {
        super("Não encontrado");
    }
}
