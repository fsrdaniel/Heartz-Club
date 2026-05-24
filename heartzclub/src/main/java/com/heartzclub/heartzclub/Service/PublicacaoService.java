package com.heartzclub.heartzclub.Service;

import com.heartzclub.heartzclub.DTO.PublicacaoDTO;
import com.heartzclub.heartzclub.Exception.PublicacaoNotFoundException;
import com.heartzclub.heartzclub.Model.Publicacao;
import com.heartzclub.heartzclub.Repository.PublicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;

    public PublicacaoService(PublicacaoRepository publicacaoRepository) {
        this.publicacaoRepository = publicacaoRepository;
    }

    public Publicacao findById(Long id) {
        return publicacaoRepository.findById(id).
                orElseThrow(() -> new PublicacaoNotFoundException(id));
    }

    public List<Publicacao> findAll() {
        return publicacaoRepository.findAll();
    }

    public Publicacao criar(PublicacaoDTO publicacaoDTO) {

        var publicacao = new Publicacao(
                publicacaoDTO.usuario(),
                publicacaoDTO.jogo().getNome(),
                publicacaoDTO.titulo(),
                publicacaoDTO.descricao(),
                publicacaoDTO.comentarios(),
                publicacaoDTO.horaPublicacao()
        );

        return publicacaoRepository.save(publicacao);
    }
    public Publicacao alterar(Long id, PublicacaoDTO publicacaoDTO) {

        var publicacao = findById(id);
        publicacao.setTitulo(publicacaoDTO.titulo());
        publicacao.setDescricao(publicacaoDTO.descricao());

        return publicacaoRepository.save(publicacao);
    }

    public void deletar(Long id) {
        findById(id);
        publicacaoRepository.deleteById(id);
    }
}