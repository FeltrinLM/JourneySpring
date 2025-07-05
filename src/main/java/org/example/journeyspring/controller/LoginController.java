package org.example.journeyspring.controller;

import jakarta.servlet.http.HttpSession;
import org.example.journeyspring.model.Usuario;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Peca;
import org.example.journeyspring.model.Estampa;
import org.example.journeyspring.service.UsuarioService;
import org.example.journeyspring.service.ColecaoService;
import org.example.journeyspring.service.PecaService;
import org.example.journeyspring.service.EstampaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PecaService pecaService;

    @Autowired
    private ColecaoService colecaoService;

    @Autowired
    private EstampaService estampaService;

    @GetMapping("/")
    public String index() {
        return "index"; // WEB-INF/index.jsp
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "login"; // WEB-INF/login.jsp
    }

    @PostMapping("/login")
    public String realizarLogin(Model model,
                                HttpSession session,
                                @RequestParam String email,
                                @RequestParam String senha) {

        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/visualizacao-geral";
        } else {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
    }

    @GetMapping("/visualizacao-geral")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Peca> pecas = pecaService.listar();
        List<Colecao> colecoes = colecaoService.listar();
        List<Estampa> estampas = estampaService.listar();

        model.addAttribute("pecas", pecas);
        model.addAttribute("colecoes", colecoes);
        model.addAttribute("estampas", estampas);

        return "visualizacao-geral";
    }
}
