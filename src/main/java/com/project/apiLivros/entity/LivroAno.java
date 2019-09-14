package com.project.apiLivros.entity;

import javax.persistence.*;

@Entity
@Table(name="livroano")
public class LivroAno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private Long id;

    @Column(name="codigoLivro")
    private Long idLivro;
    @Column(name="codigoAno")
    private Long idAno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public Long getIdAno() {
        return idAno;
    }

    public void setIdAno(Long idAno) {
        this.idAno = idAno;
    }

    @Override
    public String toString() {
        return "LivroAno{" +
                "id=" + id +
                ", idLivro=" + idLivro +
                ", idAno=" + idAno +
                '}';
    }
}
