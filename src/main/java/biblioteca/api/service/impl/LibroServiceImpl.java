package biblioteca.api.service.impl;

import biblioteca.api.model.Libro;
import biblioteca.api.model.Prestamo;
import biblioteca.api.repository.LibroRepository;
import biblioteca.api.repository.PrestamoRepository;
import biblioteca.api.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }


    /**
     * Metodo que resuelve con Stream Api una busqueda de libros por titulos y filtrando
     * fechas anteriores a una fecha dada.
     */
    public List<Libro> buscarPorTitulo(String titulo) {

        LocalDate fechaAComparar = LocalDate.of(1998, 12, 12);

        List<Libro> libroList = libroRepository.findByTitulo(titulo);

        return  libroList.stream()
                .filter(l -> l.getFechaPublicacion().isBefore(fechaAComparar))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    public List<Libro> buscarPorCategoria(String categoria) {
        return libroRepository.findByCategoria(categoria);
    }

    /**
     *
     * @param fechaInicio
     * @param fechaFin
     * Metodo para obtener libros mas prestados en un periodo determinado
     */
    public List<Libro> obtenerLibrosMasPrestadosEnPeriodo(Date fechaInicio, Date fechaFin) {

        List<Prestamo> listaPrestamos = prestamoRepository.findAll();

        Map<Libro, Long> conteoLibrosPrestados = listaPrestamos.stream()
                .filter(prestamo -> prestamo.getFechaPrestamo().after(fechaInicio) && prestamo.getFechaPrestamo().before(fechaFin))
                .collect(Collectors.groupingBy(Prestamo::getLibro, Collectors.counting()));

        return conteoLibrosPrestados.entrySet().stream()
                .sorted(Map.Entry.<Libro, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
