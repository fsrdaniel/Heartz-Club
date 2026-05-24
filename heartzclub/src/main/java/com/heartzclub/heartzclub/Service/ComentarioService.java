package com.heartzclub.heartzclub.Service;

import com.heartzclub.heartzclub.Model.Comentario;
import com.heartzclub.heartzclub.Model.Publicacao;
import com.heartzclub.heartzclub.Model.Usuario;
import com.heartzclub.heartzclub.Repository.ComentarioRepository;
import com.heartzclub.heartzclub.Repository.PublicacaoRepository;
import com.heartzclub.heartzclub.Repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    private final UsuarioRepository usuarioRepository;

    private final PublicacaoRepository publicacaoRepository;

    public ComentarioService(
            ComentarioRepository comentarioRepository,
            UsuarioRepository usuarioRepository,
            PublicacaoRepository publicacaoRepository
    ) {

        this.comentarioRepository =
                comentarioRepository;

        this.usuarioRepository =
                usuarioRepository;

        this.publicacaoRepository =
                publicacaoRepository;
    }

    public Comentario comentar(
            Long usuarioId,
            Long publicacaoId,
            String texto
    ) {

        Usuario usuario =
                usuarioRepository.findById(usuarioId)
                        .orElseThrow();

        Publicacao publicacao =
                publicacaoRepository.findById(publicacaoId)
                        .orElseThrow();

        Comentario comentario =
                new Comentario();

        comentario.setTexto(texto);

        comentario.setUsuario(usuario);

        comentario.setPublicacao(publicacao);

        comentario.setDataComentario(new Date());

        return comentarioRepository.save(comentario);
    }
}