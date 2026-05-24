package com.heartzclub.heartzclub.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Não precisa de import, estão na mesma pasta

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataComentario;

    public Comentario() {}

    public Comentario(String texto, Publicacao publicacao, Usuario usuario, Date dataComentario) {
        this.texto = texto;
        this.publicacao = publicacao;
        this.usuario = usuario;
        this.dataComentario = dataComentario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Publicacao getPublicacao() { return publicacao; }
    public void setPublicacao(Publicacao publicacao) { this.publicacao = publicacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Date getDataComentario() { return dataComentario; }
    public void setDataComentario(Date dataComentario) { this.dataComentario = dataComentario; }
}