package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.DAO.EstampaDAO;
import org.example.journeyspring.DAO.PecaDAO;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Estampa;
import org.example.journeyspring.model.Peca;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class VisualizacaoGeralServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            List<Peca> pecas = new PecaDAO().listar();
            List<Colecao> colecoes = new ColecaoDAO().listar();
            List<Estampa> estampas = new EstampaDAO().listar();

            request.setAttribute("pecas", pecas);
            request.setAttribute("colecoes", colecoes);
            request.setAttribute("estampas", estampas);

            // Agora faz forward para a JSP normalmente
            request.getRequestDispatcher("visualizacao-geral.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao carregar dados para a visualização.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        try {
            if ("excluir".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                new PecaDAO().excluir(id);

            } else if ("excluir-colecao".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                new ColecaoDAO().removerColecao(id);

            } else if ("excluir-estampa".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                new EstampaDAO().excluir(id);
            }

            // Redireciona de volta pro servlet (refresca os dados)
            response.sendRedirect("dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar ação no dashboard.", e);
        }
    }
}
