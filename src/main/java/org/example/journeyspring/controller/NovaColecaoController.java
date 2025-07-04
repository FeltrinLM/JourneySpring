package org.example.journeyspring.controller;

import org.example.journeyspring.model.Colecao;
import org.example.journeyspring.service.ColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nova-colecao")
public class NovaColecaoController {

    @Autowired
    private ColecaoService colecaoService;

    @GetMapping
    public String mostrarFormulario() {
        return "nova-colecao"; // nova-colecao.jsp
    }

    @PostMapping
    public String criarColecao(
            @RequestParam String nome,
            @RequestParam String data_inicio,
            @RequestParam(required = false) String data_fim,
            Model model
    ) {
        if (colecaoService.nomeExiste(nome)) {
            model.addAttribute("erro", "Já existe uma coleção com esse nome.");
            model.addAttribute("nome", nome);
            model.addAttribute("data_inicio", data_inicio);
            model.addAttribute("data_fim", data_fim);
            return "nova-colecao"; // volta pro form com os dados preenchidos
        }

        Colecao colecao = new Colecao();
        colecao.setNome(nome);
        colecao.setData_inicio(data_inicio);
        colecao.setData_fim((data_fim == null || data_fim.isEmpty()) ? null : data_fim);

        colecaoService.inserir(colecao);
        return "redirect:/dashboard";
    }
}
