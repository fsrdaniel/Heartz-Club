package com.heartzclub.heartzclub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @NotBlank String email,
        @NotNull LocalDate dataNascimento,
        @NotBlank String cpf,
        @NotBlank String endereco,
        @NotBlank String senha
) {}