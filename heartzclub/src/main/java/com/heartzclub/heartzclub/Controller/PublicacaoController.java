package com.heartzclub.heartzclub.Controller;

import com.heartzclub.heartzclub.DTO.PublicacaoDTO;
import com.heartzclub.heartzclub.Model.Publicacao;
import com.heartzclub.heartzclub.Service.PublicacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacoes")
@CrossOrigin("*")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    public PublicacaoController(
            PublicacaoService publicacaoService
    ) {

        this.publicacaoService =
                publicacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Publicacao>> listar() {

        return ResponseEntity.ok(
                publicacaoService.findAll()
        );
    }

    @PostMapping
    public ResponseEntity<Publicacao> criar(
            @RequestBody @Valid PublicacaoDTO dto
    ) {

        return ResponseEntity.ok(
                publicacaoService.criar(dto)
        );
    }
}