package com.heartzclub.heartzclub.Service;

import com.heartzclub.heartzclub.DTO.JogoRequestDTO;
import com.heartzclub.heartzclub.Exception.JogoNotFoundException;
import com.heartzclub.heartzclub.Model.Jogo;
import com.heartzclub.heartzclub.Repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public Jogo findById(Long id) {
        return jogoRepository.findById(id).
                orElseThrow(() -> new JogoNotFoundException(id));
    }

    public Jogo criar(JogoRequestDTO dto) {
        var jogo = new Jogo(dto.nome(),
                dto.genero(),
                dto.descricao(),
                dto.nota(),
                dto.imagemUrl());
        return jogoRepository.save(jogo);
    }

    public List<Jogo> findAll() {
        return jogoRepository.findAll();
    }

    public Jogo alterar(Long id, JogoRequestDTO dto) {
        var jogo = findById(id);
        jogo.setNome(dto.nome());
        jogo.setGenero(dto.genero());
        jogo.setDescricao(jogo.getDescricao());
        return jogoRepository.save(jogo);
    }

    public void deletar(Long id) {
        findById(id);
        jogoRepository.deleteById(id);
    }
}