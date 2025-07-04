package org.example.journeyspring.controller;

import org.example.journeyspring.model.Peca;
import org.example.journeyspring.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nova-peca")
public class NovaPecaController {

    @Autowired
    private PecaService pecaService;

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
