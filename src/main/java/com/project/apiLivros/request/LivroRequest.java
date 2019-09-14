package com.project.apiLivros.request;

public class LivroRequest {
    private String nome;
    private String autor;
    private String ano;

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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "LivroRequest{" +
                "nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}
