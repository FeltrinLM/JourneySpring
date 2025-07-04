package org.example.journeyspring.controller;

import org.example.journeyspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @RequestParam String nome,
                        @RequestParam String email,
                        @RequestParam String senha) {

        if (usuarioService.autenticar(email, senha)) {
            return "redirect:/visualizacao-geral";
        } else {
            model.addAttribute("msg", "Login ou senha incorretos");
            return "login";
        }
    }

    @GetMapping("/visualizacao-geral")
    public String dashboard() {
        return "visualizacao-geral";
    }
}
