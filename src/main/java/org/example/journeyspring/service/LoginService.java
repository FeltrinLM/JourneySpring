package org.example.journeyspring.service;

import org.example.journeyspring.DAO.UsuarioDAO;
import org.example.journeyspring.model.Usuario;

import java.sql.SQLException;

public class LoginService {

    public boolean autenticar(String email, String senha) {

        // Usa o DAO para buscar um Gerente com email + senhaInt
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = null;
        try {
            usuario = dao.buscarPorEmailESenha(email, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return usuario != null;
    }

}
