package br.unipar.programacaoweb.livrariaunipar.repository;

import br.unipar.programacaoweb.livrariaunipar.model.Livro;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findLivroByTituloContainingIgnoreCase(String titulo);

    List<Livro> findLivroByGeneroContainingIgnoreCase(String genero);

    @Query("SELECT t FROM Livro t WHERE t.titulo = :genero AND t.numeroPaginas >= :numeroPaginas")
    List<Livro> findLivroBygeneroeNumerPagina(@Param("genero") String genero,
                                              @Param("numeroPaginas") int pagina);



}
