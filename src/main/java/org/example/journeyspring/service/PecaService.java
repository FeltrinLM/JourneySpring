package org.example.journeyspring.service;

import org.example.journeyspring.DAO.PecaDAO;
import org.example.journeyspring.model.Peca;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {

    private final PecaDAO pecaDAO = new PecaDAO();

    public Peca buscarPorId(int id) {
        return pecaDAO.buscarPorId(id);
    }

    public void atualizarOuFundirPeca(Peca novaPeca) {
        Peca duplicada = pecaDAO.buscarPorAtributos(novaPeca.getTipo(), novaPeca.getTamanho(), novaPeca.getCor());

        if (duplicada != null && duplicada.getPeca_id() != novaPeca.getPeca_id()) {
            int novaQuantidade = duplicada.getQuantidade() + novaPeca.getQuantidade();
            pecaDAO.atualizarQuantidade(duplicada.getPeca_id(), novaQuantidade);
            pecaDAO.excluir(novaPeca.getPeca_id());
        } else {
            pecaDAO.atualizar(novaPeca);
        }
    }

    public void inserirOuSomarPeca(Peca novaPeca) {
        Peca existente = pecaDAO.buscarPorAtributos(novaPeca.getTipo(), novaPeca.getTamanho(), novaPeca.getCor());

        if (existente != null) {
            int novaQuantidade = existente.getQuantidade() + novaPeca.getQuantidade();
            existente.setQuantidade(novaQuantidade);
            pecaDAO.atualizar(existente);
        } else {
            pecaDAO.inserir(novaPeca);
        }
    }

    public List<Peca> listar() {
        return pecaDAO.listar();
    }

    public void excluir(int id) {
        pecaDAO.excluir(id);
    }
}
