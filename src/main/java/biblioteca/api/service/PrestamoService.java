package biblioteca.api.service;

import biblioteca.api.model.Libro;
import biblioteca.api.model.Prestamo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {

    List<Prestamo> obtenerTodosLosPrestamos();

    Optional<Prestamo> obtenerPrestamoPorId(Long id);

    Prestamo guardarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(Long id);
}
