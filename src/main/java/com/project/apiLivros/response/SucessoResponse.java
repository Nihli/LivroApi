package com.project.apiLivros.response;

public class SucessoResponse<M> {
    private int codigo;
    private String mensagem;
    private M objeto;

    public SucessoResponse(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public SucessoResponse(int codigo, String mensagem, M objeto) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.objeto = objeto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public M getObjeto() {
        return objeto;
    }

    public void setObjeto(M objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "SucessoResponse{" +
                "codigo=" + codigo +
                ", mensagem='" + mensagem + '\'' +
                ", objeto=" + objeto +
                '}';
    }
}
