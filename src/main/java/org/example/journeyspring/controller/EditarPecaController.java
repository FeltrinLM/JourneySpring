package org.example.journeyspring.controller;

import org.example.journeyspring.model.Peca;
import org.example.journeyspring.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editar-peca")
public class EditarPecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public String mostrarFormulario(@RequestParam("id") int id, Model model) {
        Peca peca = pecaService.buscarPorId(id);

        if (peca != null) {
            model.addAttribute("peca", peca);
            return "editar-peca"; // editar-peca.jsp
        } else {
            return "redirect:/dashboard";
        }
    }

    @PostMapping
    public String editarPeca(
            @RequestParam("peca_id") int id,
            @RequestParam("tipo") String tipo,
            @RequestParam("tamanho") String tamanho,
            @RequestParam("cor") String cor,
            @RequestParam("quantidade") int quantidade
    ) {
        Peca peca = new Peca();
        peca.setPeca_id(id);
        peca.setTipo(tipo);
        peca.setTamanho(tamanho);
        peca.setCor(cor);
        peca.setQuantidade(quantidade);

        pecaService.atualizarOuFundirPeca(peca);

        return "redirect:/dashboard";
    }
}
