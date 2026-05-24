package com.heartzclub.heartzclub.Controller;

import com.heartzclub.heartzclub.Model.Comentario;
import com.heartzclub.heartzclub.Service.ComentarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin("*")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(
            ComentarioService comentarioService
    ) {

        this.comentarioService =
                comentarioService;
    }

    @PostMapping("/{usuarioId}/{publicacaoId}")
    public ResponseEntity<Comentario> comentar(
            @PathVariable Long usuarioId,
            @PathVariable Long publicacaoId,
            @RequestBody String texto
    ) {

        return ResponseEntity.ok(
                comentarioService.comentar(
                        usuarioId,
                        publicacaoId,
                        texto
                )
        );
    }
}