package com.heartzclub.heartzclub.DTO;

import jakarta.validation.constraints.NotBlank;

public record JogoRequestDTO(
        @NotBlank(message = "Nome não deve ser vazio")
        String nome,

        @NotBlank(message = "Genero não deve ser vazio")
        String genero,

        @NotBlank(message = "Descrição não deve ser vazio")
        String descricao,

        @NotBlank(message = "Adicione uma nota")
        Double nota,

        String imagemUrl
) {
}