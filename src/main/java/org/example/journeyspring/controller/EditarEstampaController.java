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
@RequestMapping("/editar-estampa")
public class EditarEstampaController {

    @Autowired
    private EstampaService estampaService;

    private final ColecaoDAO colecaoDAO = new ColecaoDAO();

    @GetMapping
    public String mostrarFormulario(@RequestParam("estampa_id") int idEstampa, Model model) {
        Estampa estampa = estampaService.buscarPorId(idEstampa);

        if (estampa != null) {
            List<Colecao> colecoes = colecaoDAO.listar();
            model.addAttribute("estampa", estampa);
            model.addAttribute("colecoes", colecoes);
            return "editar-estampa";
        } else {
            return "redirect:/dashboard";
        }
    }

    @PostMapping
    public String editarEstampa(
            @RequestParam("estampa_id") int id,
            @RequestParam("nome") String nome,
            @RequestParam("quantidade") int quantidade,
            @RequestParam("id_colecao") int id_colecao
    ) {
        Estampa estampa = new Estampa();
        estampa.setId_estampa(id);
        estampa.setNome(nome);
        estampa.setQuantidade(quantidade);
        estampa.setId_colecao(id_colecao);

        estampaService.atualizarEstampaComVerificacao(estampa);
        return "redirect:/dashboard";
    }
}
