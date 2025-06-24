package org.example.journeyspring.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.DAO.EstampaDAO;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Estampa;

import java.io.IOException;
import java.util.List;

public class EditarEstampaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEstampa = Integer.parseInt(request.getParameter("estampa_id"));

        EstampaDAO dao = new EstampaDAO();
        Estampa estampa = dao.buscarPorId(idEstampa);

        if (estampa != null) {
            List<Colecao> colecoes = new ColecaoDAO().listar();
            request.setAttribute("estampa", estampa);
            request.setAttribute("colecoes", colecoes);
            request.getRequestDispatcher("editar-estampa.jsp").forward(request, response);
        } else {
            response.sendRedirect("dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("estampa_id"));
        String nome = request.getParameter("nome");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        int id_colecao = Integer.parseInt(request.getParameter("id_colecao"));

        EstampaDAO dao = new EstampaDAO();
        Estampa duplicada = dao.buscarPorNomeEColecao(nome, id_colecao);

        if (duplicada != null && duplicada.getId_estampa() != id) {
            // Existe outra estampa com o mesmo nome (ignorando case) e mesma coleção -> soma
            int novaQuantidade = duplicada.getQuantidade() + quantidade;
            dao.atualizarQuantidade(duplicada.getId_estampa(), novaQuantidade);
            dao.excluir(id); // remove a atual (já que será fundida com a duplicata)
        } else {
            // Atualiza normalmente
            Estampa estampa = new Estampa();
            estampa.setId_estampa(id);
            estampa.setNome(nome);
            estampa.setQuantidade(quantidade);
            estampa.setId_colecao(id_colecao);
            dao.atualizar(estampa);
        }

        response.sendRedirect("dashboard");
    }
}
