package org.example.journeyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.journeyspring.model.Peca;
import org.example.journeyspring.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/nova-peca")
public class NovaPecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public String exibirFormulario() {
        return "nova-peca"; // carrega /WEB-INF/nova-peca.jsp
    }

    @PostMapping
    public String criarPeca(
            @RequestParam String tipo,
            @RequestParam String tamanho,
            @RequestParam String cor,
            @RequestParam int quantidade
    ) {
        Peca nova = new Peca();
        nova.setTipo(tipo);
        nova.setTamanho(tamanho);
        nova.setCor(cor);
        nova.setQuantidade(quantidade);

        pecaService.inserirOuSomarPeca(nova);
        return "redirect:/dashboard";
    }
}
