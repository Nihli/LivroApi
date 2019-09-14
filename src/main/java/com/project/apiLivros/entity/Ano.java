package com.project.apiLivros.entity;

import javax.persistence.*;

@Entity
@Table(name="ano")
public class Ano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private Long id;

    private String ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ano{" +
                "id=" + id +
                ", ano='" + ano + '\'' +
                '}';
    }
}
