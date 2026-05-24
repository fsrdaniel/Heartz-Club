package com.heartzclub.heartzclub.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publicacoes")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String jogo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    // Construtor padrão (Vazio) - Exigido pelo Hibernate
    public Publicacao() {}

    // CONSTRUTOR COM 6 ARGUMENTOS (Resolve o erro da linha 31)
    public Publicacao(Usuario usuario, String jogo, String titulo, String description, List<Comentario> comentarios, Date horaPublicacao) {
        this.usuario = usuario;
        this.jogo = jogo;
        this.titulo = titulo;
        this.descricao = description;
        this.comentarios = comentarios != null ? comentarios : new ArrayList<>();
        this.horaPublicacao = horaPublicacao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getJogo() { return jogo; }
    public void setJogo(String jogo) { this.jogo = jogo; }

    public Date getHoraPublicacao() { return horaPublicacao; }
    public void setHoraPublicacao(Date horaPublicacao) { this.horaPublicacao = horaPublicacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { this.comentarios = comentarios; }
}