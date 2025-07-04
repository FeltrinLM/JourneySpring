package org.example.journeyspring.service;

import org.example.journeyspring.DAO.EstampaDAO;
import org.example.journeyspring.model.Estampa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstampaService {

    private final EstampaDAO estampaDAO = new EstampaDAO();

    public Estampa buscarPorId(int id) {
        return estampaDAO.buscarPorId(id);
    }

    public void atualizarEstampaComVerificacao(Estampa estampa) {
        Estampa duplicada = estampaDAO.buscarPorNomeEColecao(estampa.getNome(), estampa.getId_colecao());

        if (duplicada != null && duplicada.getId_estampa() != estampa.getId_estampa()) {
            int novaQuantidade = duplicada.getQuantidade() + estampa.getQuantidade();
            estampaDAO.atualizarQuantidade(duplicada.getId_estampa(), novaQuantidade);
            estampaDAO.excluir(estampa.getId_estampa());
        } else {
            estampaDAO.atualizar(estampa);
        }
    }

    public void inserirOuSomarEstampa(Estampa estampa) {
        Estampa existente = estampaDAO.buscarPorNomeEColecao(estampa.getNome(), estampa.getId_colecao());

        if (existente != null) {
            int novaQuantidade = existente.getQuantidade() + estampa.getQuantidade();
            estampaDAO.atualizarQuantidade(existente.getId_estampa(), novaQuantidade);
        } else {
            estampaDAO.inserir(estampa);
        }
    }

    public List<Estampa> listar() {
        return estampaDAO.listar();
    }

    public void excluir(int id) {
        estampaDAO.excluir(id);
    }
}
