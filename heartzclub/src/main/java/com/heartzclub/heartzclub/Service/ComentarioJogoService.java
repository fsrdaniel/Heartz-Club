package com.heartzclub.heartzclub.Service;

import com.heartzclub.heartzclub.DTO.ComentarioJogoRequestDTO;
import com.heartzclub.heartzclub.Model.ComentarioJogo;
import com.heartzclub.heartzclub.Model.Jogo;
import com.heartzclub.heartzclub.Model.Usuario;
import com.heartzclub.heartzclub.Repository.ComentarioJogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioJogoService {


    private final ComentarioJogoRepository repository;
    private final JogoService jogoService;
    private final UsuarioService usuarioService;

    public ComentarioJogoService(ComentarioJogoRepository repository,
                                 JogoService jogoService,
                                 UsuarioService usuarioService) {
        this.repository = repository;
        this.jogoService = jogoService;
        this.usuarioService = usuarioService;
    }

    public ComentarioJogo criar(ComentarioJogoRequestDTO dto) {
        Jogo jogo = jogoService.findById(dto.jogoId());
        Usuario usuario = usuarioService.findById(dto.usuarioId());
        return repository.save(new ComentarioJogo(dto.texto(), jogo, usuario));
    }

    public List<ComentarioJogo> listarPorJogo(Long jogoId) {
        return repository.findByJogoIdOrderByDataComentarioDesc(jogoId);
    }
}
