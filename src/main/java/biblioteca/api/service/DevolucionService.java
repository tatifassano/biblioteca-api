package biblioteca.api.service;

import biblioteca.api.model.Devolucion;

import java.util.List;
import java.util.Optional;

public interface DevolucionService {

    List<Devolucion> obtenerTodasLasDevoluciones();

    Optional<Devolucion> obtenerDevolucionPorId(Long id);

    Devolucion guardarDevolucion(Devolucion devolucion);

    void eliminarDevolucion(Long id);
}
