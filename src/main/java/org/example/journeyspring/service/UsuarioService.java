package org.example.journeyspring.service;

import org.example.journeyspring.DAO.UsuarioDAO;
import org.example.journeyspring.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean autenticar(String email, String senha) {
        try {
            Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
            return usuario != null;
        } catch (Exception e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarPorEmail(String email) {
        try {
            return usuarioDAO.buscarPorEmail(email);
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    public boolean cadastrar(Usuario usuario) {
        try {
            return usuarioDAO.inserir(usuario);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
}
