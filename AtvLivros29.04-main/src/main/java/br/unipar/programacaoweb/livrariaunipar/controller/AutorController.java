package br.unipar.programacaoweb.livrariaunipar.controller;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {

        this.autorService = autorService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarAutor() {

        List<Autor> autor = autorService.listarTodos();

        if (autor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autor);
        //return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);

    }



    @GetMapping("/buscar/titulo/{nome}")
    public ResponseEntity<List<Autor>> buscarAutorPorTitulo(@PathVariable String nome) {
        List<Autor> autor = autorService.buscarPorNome(nome);
        if (autor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorService.salvar(autor);

        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);

        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Autor> atualizarAutor(@RequestBody Autor autor, @PathVariable Long id) {
        Autor autorAtual = autorService.buscarPorId(id);
        if (autorAtual == null) {
            return ResponseEntity.notFound().build();
        }
        autorAtual.setNome(autor.getNome());
        autorAtual.setNacionalidade(autor.getNacionalidade());
        autorAtual.setDataNascimento(autor.getDataNascimento());
        autorAtual.setEmail(autor.getEmail());
        return ResponseEntity.ok(autorService.salvar(autorAtual));

    }


}
