package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;
    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if (id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
}

    /* No método com a anotação "GetMapping" do Controller, a classe Spring "Model" pode ser passada
    como parâmetro para que este método envie os dados de uma classe java,
    para uma variável adicionada à página HTML. Nesse caso, os dados da variável filmes,
    que é uma Array de objetos, que são do tipo Filme, serão enviados para a página HTML "listagem". */
    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    /* Através da palavra reservada redirect do Spring, redireciona-se a requisição para um outro método.*/
    @PostMapping
    public  String cadastraFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);

        repository.save(filme);

        return "redirect:/filmes";
    }
    @PutMapping
    @Transactional
    public  String alteraFilme(DadosAlteracaoFilme dados) {
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public  String removeFilme(Long id) {
        repository.deleteById(id);

        return "redirect:/filmes";
    }

}
