package com.heartzclub.heartzclub.DTO;

import jakarta.validation.constraints.NotBlank;


public record LoginDto(
        @NotBlank(message = "Digite o email")
        String email,
        @NotBlank(message = "Digite a senha")
        String senha
) {}