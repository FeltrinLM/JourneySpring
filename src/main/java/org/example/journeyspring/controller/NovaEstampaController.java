package org.example.journeyspring.controller;

import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Estampa;
import org.example.journeyspring.service.EstampaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nova-estampa")
public class NovaEstampaController {

    @Autowired
    private EstampaService estampaService;

    private final ColecaoDAO colecaoDAO = new ColecaoDAO();

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<Colecao> colecoes = colecaoDAO.listar();
        model.addAttribute("colecoes", colecoes);
        return "nova-estampa"; // nova-estampa.jsp
    }

    @PostMapping
    public String criarEstampa(
            @RequestParam String nome,
            @RequestParam int quantidade,
            @RequestParam("id_colecao") int idColecao
    ) {
        Estampa nova = new Estampa();
        nova.setNome(nome);
        nova.setQuantidade(quantidade);
        nova.setId_colecao(idColecao);

        estampaService.inserirOuSomarEstampa(nova);
        return "redirect:/dashboard";
    }
}
