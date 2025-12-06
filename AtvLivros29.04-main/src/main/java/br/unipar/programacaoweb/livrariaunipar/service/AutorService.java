package br.unipar.programacaoweb.livrariaunipar.service;

import br.unipar.programacaoweb.livrariaunipar.model.Autor;
import br.unipar.programacaoweb.livrariaunipar.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {

        return autorRepository.save(autor);
    }

    public void excluir(long id) {
        autorRepository.deleteById(id);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public List<Autor> buscarPorNome(String nome) {

        return autorRepository.findAll().stream().filter(autor -> autor.getNome().equalsIgnoreCase(nome)).collect(Collectors.toList());
    }

    public List<Autor> listarTodos(){
        return autorRepository.findAll();
    }

}
