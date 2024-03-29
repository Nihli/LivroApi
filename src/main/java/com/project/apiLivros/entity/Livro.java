package com.project.apiLivros.entity;

import javax.persistence.*;

@Entity
@Table(name="livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private Long id;

    private String nome;
    private String autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
