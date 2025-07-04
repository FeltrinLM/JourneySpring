package org.example.journeyspring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.model.Estampa;
import org.example.journeyspring.model.Peca;
import org.example.journeyspring.service.ColecaoService;
import org.example.journeyspring.service.EstampaService;
import org.example.journeyspring.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private PecaService pecaService;

    @Autowired
    private ColecaoService colecaoService;

    @Autowired
    private EstampaService estampaService;

    @GetMapping
    public String mostrarDashboard(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            return "redirect:/"; // index.jsp
        }

        List<Peca> pecas = pecaService.listar();
        List<Colecao> colecoes = colecaoService.listar();
        List<Estampa> estampas = estampaService.listar();

        model.addAttribute("pecas", pecas);
        model.addAttribute("colecoes", colecoes);
        model.addAttribute("estampas", estampas);

        return "visualizacao-geral"; // visualizacao-geral.jsp
    }

    @PostMapping
    public String executarAcao(@RequestParam("acao") String acao, @RequestParam("id") int id) {
        switch (acao) {
            case "excluir":
                pecaService.excluir(id);
                break;
            case "excluir-colecao":
                colecaoService.removerColecao(id);
                break;
            case "excluir-estampa":
                estampaService.excluir(id);
                break;
        }
        return "redirect:/dashboard";
    }
}
