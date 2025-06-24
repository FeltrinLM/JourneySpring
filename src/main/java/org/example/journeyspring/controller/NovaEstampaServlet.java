package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.DAO.EstampaDAO;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Estampa;

import java.io.IOException;
import java.util.List;

@WebServlet("/nova-estampa")
public class NovaEstampaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Colecao> colecoes = new ColecaoDAO().listar();
        request.setAttribute("colecoes", colecoes);
        request.getRequestDispatcher("nova-estampa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        int id_colecao = Integer.parseInt(request.getParameter("id_colecao"));

        EstampaDAO dao = new EstampaDAO();
        Estampa existente = dao.buscarPorNomeEColecao(nome, id_colecao);

        if (existente != null) {
            // Se já existe, somar a quantidade
            int novaQuantidade = existente.getQuantidade() + quantidade;
            dao.atualizarQuantidade(existente.getId_estampa(), novaQuantidade);
        } else {
            // Se não existe, inserir normalmente
            Estampa nova = new Estampa();
            nova.setNome(nome);
            nova.setQuantidade(quantidade);
            nova.setId_colecao(id_colecao);
            dao.inserir(nova);
        }

        response.sendRedirect("dashboard");
    }
}
