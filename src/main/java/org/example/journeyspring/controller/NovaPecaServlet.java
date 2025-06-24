// NovaPecaServlet.java
package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.PecaDAO;
import org.example.journeyspring.model.Peca;

import java.io.IOException;

public class NovaPecaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String tamanho = request.getParameter("tamanho");
        String cor = request.getParameter("cor");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        PecaDAO dao = new PecaDAO();
        Peca existente = dao.buscarPorAtributos(tipo, tamanho, cor);

        if (existente != null) {
            int novaQuantidade = existente.getQuantidade() + quantidade;
            existente.setQuantidade(novaQuantidade);
            dao.atualizar(existente);
        } else {
            Peca nova = new Peca();
            nova.setTipo(tipo);
            nova.setTamanho(tamanho);
            nova.setCor(cor);
            nova.setQuantidade(quantidade);
            dao.inserir(nova);
        }

        response.sendRedirect("dashboard");
    }
}
