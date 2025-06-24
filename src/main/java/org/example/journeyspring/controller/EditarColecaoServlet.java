package org.example.journeyspring.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.model.Colecao;

import java.io.IOException;

public class EditarColecaoServlet extends HttpServlet {

    // Mostra a tela de edição
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">> Entrou no doGet de EditarColecaoServlet");

        int id = Integer.parseInt(request.getParameter("id"));
        Colecao colecao = new ColecaoDAO().buscarPorId(id);

        if (colecao != null) {
            request.setAttribute("colecao", colecao);
            request.getRequestDispatcher("editar-colecao.jsp").forward(request, response);
        } else {
            System.out.println("Coleção não encontrada. ID: " + id);
            response.sendRedirect("dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String data_inicio = request.getParameter("data_inicio");
        String data_fim = request.getParameter("data_fim");

        ColecaoDAO dao = new ColecaoDAO();

        // Buscar coleção atual no banco pra comparar o nome antigo
        Colecao atual = dao.buscarPorId(id);

        // Se o nome foi alterado, a gente precisa verificar se já existe outro igual
        if (!nome.equalsIgnoreCase(atual.getNome()) && dao.nomeExiste(nome)) {
            request.setAttribute("colecao", atual);
            request.setAttribute("erro", "Já existe uma coleção com esse nome.");
            request.getRequestDispatcher("editar-colecao.jsp").forward(request, response);
            return;
        }

        // Continua normalmente com a atualização
        Colecao nova = new Colecao();
        nova.setId_colecao(id);
        nova.setNome(nome);
        nova.setData_inicio(data_inicio);
        nova.setData_fim(data_fim);

        dao.atualizar(nova);

        response.sendRedirect("dashboard");
    }


}
