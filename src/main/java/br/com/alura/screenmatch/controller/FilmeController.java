package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private List<Filme> filmes = new ArrayList<>();

    @GetMapping("/formulario")
    public String carregaPaginaFormulario() {
        return "filmes/formulario";
}

/* No método com a anotação "GetMapping" do Controller, a classe Spring "Model" pode ser passada
como parâmetro para que este método envie os dados de uma classe java,
para uma variável adicionada à página HTML. Nesse caso, os dados da variável filmes,
que é uma Array de objetos, que são do tipo Filme, serão enviados para a página HTML "listagem". */
    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", filmes);
        return "filmes/listagem";
    }

/* Através da palavra reservada redirect do Spring, redireciona-se a requisição para um outro método.*/
    @PostMapping
    public  String cadastraFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);
        filmes.add(filme);

        return "redirect:/filmes";
    }

}
