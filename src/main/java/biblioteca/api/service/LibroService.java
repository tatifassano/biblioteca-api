package biblioteca.api.service;

import biblioteca.api.model.Libro;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> obtenerTodosLosLibros();

    Optional<Libro> obtenerLibroPorId(Long id);

    Libro guardarLibro(Libro libro);
    void eliminarLibro(Long id);

    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> buscarPorAutor(String autor);

    List<Libro> buscarPorCategoria(String categoria);

    List<Libro> obtenerLibrosMasPrestadosEnPeriodo(Date fechaInicio, Date fechaFin);
}
