package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.model.Colecao;

import java.io.IOException;

@WebServlet("/nova-colecao")
public class NovaColecaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String data_inicio = request.getParameter("data_inicio");
        String data_fim = request.getParameter("data_fim");

        ColecaoDAO dao = new ColecaoDAO();

        if (dao.nomeExiste(nome)) {
            request.setAttribute("erro", "Já existe uma coleção com esse nome.");
            request.setAttribute("nome", nome);
            request.setAttribute("data_inicio", data_inicio);
            request.setAttribute("data_fim", data_fim);
            request.getRequestDispatcher("nova-colecao.jsp").forward(request, response);
            return;
        }

        Colecao colecao = new Colecao();
        colecao.setNome(nome);
        colecao.setData_inicio(data_inicio);
        colecao.setData_fim(data_fim == null || data_fim.isEmpty() ? null : data_fim);

        dao.inserir(colecao);
        response.sendRedirect("dashboard");
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("nova-colecao.jsp").forward(request, response);
    }
}
