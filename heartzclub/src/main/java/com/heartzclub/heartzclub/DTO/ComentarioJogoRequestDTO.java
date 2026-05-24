package com.heartzclub.heartzclub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioJogoRequestDTO(
        @NotBlank(message = "Texto não pode ser vazio")
        String texto,

        @NotNull
        Long jogoId,

        @NotNull
        Long usuarioId
) {
}