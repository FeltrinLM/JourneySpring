package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.PecaDAO;
import org.example.journeyspring.model.Peca;

import java.io.IOException;

public class EditarPecaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        PecaDAO dao = new PecaDAO();
        Peca peca = dao.buscarPorId(id);

        if (peca != null) {
            request.setAttribute("peca", peca);
            request.getRequestDispatcher("editar-peca.jsp").forward(request, response);
        } else {
            response.sendRedirect("dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("peca_id"));
        String tipo = request.getParameter("tipo");
        String tamanho = request.getParameter("tamanho");
        String cor = request.getParameter("cor");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        PecaDAO dao = new PecaDAO();

        // Verifica se existe outra peça com os mesmos atributos (sem considerar a peça atual)
        Peca duplicada = dao.buscarPorAtributos(tipo, tamanho, cor);

        if (duplicada != null && duplicada.getPeca_id() != id) {
            // Existe uma peça diferente com os mesmos atributos → somar
            int novaQuantidade = duplicada.getQuantidade() + quantidade;
            dao.atualizarQuantidade(duplicada.getPeca_id(), novaQuantidade);

            // Excluir a peça atual (a que o usuário estava editando)
            dao.excluir(id);

        } else {
            // Não existe duplicata, então só atualiza normalmente
            Peca atualizada = new Peca();
            atualizada.setPeca_id(id);
            atualizada.setTipo(tipo);
            atualizada.setTamanho(tamanho);
            atualizada.setCor(cor);
            atualizada.setQuantidade(quantidade);

            dao.atualizar(atualizada);
        }

        response.sendRedirect("dashboard");
    }
}
