package br.unipar.programacaoweb.livrariaunipar.service;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import br.unipar.programacaoweb.livrariaunipar.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {

        return livroRepository.save(livro);
    }

    public void excluir(long id) {
        livroRepository.deleteById(id);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findAll().stream().filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo)).collect(Collectors.toList());
    }

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

}
