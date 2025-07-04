package org.example.journeyspring.controller;

import jakarta.servlet.http.HttpSession;
import org.example.journeyspring.model.Usuario;
import org.example.journeyspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public String cadastrarUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            HttpServletRequest request,
            Model model
    ) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        boolean sucesso = usuarioService.cadastrar(usuario);

        if (sucesso) {
            Usuario usuarioCadastrado = usuarioService.buscarPorEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioCadastrado);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("mensagem", "Erro ao cadastrar usuário. Tente novamente.");
            return "index"; // Renderiza /WEB-INF/index.jsp
        }
    }
}
