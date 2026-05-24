package com.heartzclub.heartzclub.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double nota;

    @Column(nullable = false)
    private Double mediaNotas;

    @Column(nullable = false)
    private String imagemUrl;

    public Jogo() {}

    public Jogo(String nome, String genero, String descricao, Double nota, String imagemUrl) {
        this.nome = nome;
        this.genero = genero;
        this.descricao = descricao;
        this.nota = nota;
        this.imagemUrl = imagemUrl;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public Double getMediaNotas() { return mediaNotas; }
    public void setMediaNotas(Double mediaNotas) { this.mediaNotas = mediaNotas; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}