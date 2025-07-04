package org.example.journeyspring.service;

import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.model.Colecao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColecaoService {

    private final ColecaoDAO dao = new ColecaoDAO();

    public boolean nomeExiste(String nome) {
        return dao.nomeExiste(nome);
    }

    public void inserir(Colecao colecao) {
        dao.inserir(colecao);
    }

    public List<Colecao> listar() {
        return dao.listar();
    }

    public void removerColecao(int id) {
        dao.removerColecao(id);
    }
}
