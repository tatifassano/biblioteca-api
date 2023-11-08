package biblioteca.api.repository;

import biblioteca.api.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByAutor(String autor);

    List<Libro> findByTitulo(String titulo);

    /**
     * Resuelve una consulta usando una query.
     */
    @Query("SELECT l FROM Libro l JOIN l.categorias c WHERE c.nombre = :categoria")
    List<Libro> findByCategoria(@Param("categoria") String categoria);

}
