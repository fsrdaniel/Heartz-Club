package com.heartzclub.heartzclub.Controller;

import com.heartzclub.heartzclub.DTO.ComentarioJogoRequestDTO;
import com.heartzclub.heartzclub.Model.ComentarioJogo;
import com.heartzclub.heartzclub.Service.ComentarioJogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/comentarios-jogo")
public class ComentarioJogoController {

    private final ComentarioJogoService service;

    public ComentarioJogoController(ComentarioJogoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComentarioJogo> criar(@RequestBody @Valid ComentarioJogoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping("/jogo/{jogoId}")
    public ResponseEntity<List<ComentarioJogo>> listarPorJogo(@PathVariable Long jogoId) {
        return ResponseEntity.ok(service.listarPorJogo(jogoId));
    }
}