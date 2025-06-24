package org.example.journeyspring.controller;


import org.example.journeyspring.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, String nome, String email, String senha) {

        if(new LoginService(). autenticar(email, senha)){
            return "redirect:/visualizacao-geral";
        }else{
            model.addAttribute("msg", "Login ou senha incorretos");
            return "login";
        }
    }

    @GetMapping("/visualizacao-geral")
    public String dashboard() {
        return "visualizacao-geral";
    }


}
