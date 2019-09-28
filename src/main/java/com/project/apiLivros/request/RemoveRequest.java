package com.project.apiLivros.request;

public class RemoveRequest {
    private Long idLivro;

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public String toString() {
        return "RemoveRequest{" +
                "idLivro=" + idLivro +
                '}';
    }
}
