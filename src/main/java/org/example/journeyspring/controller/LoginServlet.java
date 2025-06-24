package org.example.journeyspring.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.journeyspring.DAO.UsuarioDAO;
import org.example.journeyspring.model.Usuario;

import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario;
        try {
            usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao acessar o banco de dados: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("erro", "Email ou senha incorretos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}