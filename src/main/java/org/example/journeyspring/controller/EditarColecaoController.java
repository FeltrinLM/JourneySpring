package org.example.journeyspring.controller;

import org.example.journeyspring.DAO.ColecaoDAO;
import org.example.journeyspring.model.Colecao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editar-colecao")
public class EditarColecaoController {

    private final ColecaoDAO dao = new ColecaoDAO(); // Idealmente isso seria injetado como Bean

    // Mostra o formulário de edição
    @GetMapping
    public String mostrarFormulario(@RequestParam("id") int id, Model model) {
        System.out.println(">> Entrou no GET /editar-colecao");

        Colecao colecao = dao.buscarPorId(id);

        if (colecao != null) {
            model.addAttribute("colecao", colecao);
            return "editar-colecao"; // renderiza editar-colecao.jsp ou editar-colecao.html
        } else {
            System.out.println("Coleção não encontrada. ID: " + id);
            return "redirect:/dashboard";
        }
    }

    // Processa o envio do formulário de edição
    @PostMapping
    public String editarColecao(
            @RequestParam("id") int id,
            @RequestParam("nome") String nome,
            @RequestParam("data_inicio") String data_inicio,
            @RequestParam("data_fim") String data_fim,
            Model model
    ) {
        Colecao atual = dao.buscarPorId(id);

        if (!nome.equalsIgnoreCase(atual.getNome()) && dao.nomeExiste(nome)) {
            model.addAttribute("colecao", atual);
            model.addAttribute("erro", "Já existe uma coleção com esse nome.");
            return "editar-colecao";
        }

        Colecao nova = new Colecao();
        nova.setId_colecao(id);
        nova.setNome(nome);
        nova.setData_inicio(data_inicio);
        nova.setData_fim(data_fim);

        dao.atualizar(nova);

        return "redirect:/dashboard";
    }
}
