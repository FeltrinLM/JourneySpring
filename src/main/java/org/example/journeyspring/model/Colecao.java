package org.example.journeyspring.model;

public class Colecao {
    private int id_colecao;
    private String nome;
    private String data_inicio;
    private String data_fim;

    public int getId_colecao() {
        return id_colecao;
    }

    public void setId_colecao(int id_colecao) {
        this.id_colecao = id_colecao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
}
