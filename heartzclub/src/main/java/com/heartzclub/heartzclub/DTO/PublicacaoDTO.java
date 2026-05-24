package com.heartzclub.heartzclub.DTO;

import com.heartzclub.heartzclub.Model.Comentario;
import com.heartzclub.heartzclub.Model.Jogo;
import com.heartzclub.heartzclub.Model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record PublicacaoDTO(

        @NotBlank
        Usuario usuario,

        @NotBlank
        Jogo jogo,

        @NotBlank(message = "Titulo não pode ser vazio")
        String titulo,

        @NotBlank(message = "Descrição não pode ser vazio")
        String descricao,

        @NotBlank(message = "Comentários não pode ser vazio")
        List<Comentario> comentarios,
        @NotNull()
        Date horaPublicacao
) {
}