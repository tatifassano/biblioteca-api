package biblioteca.api.service.impl;

import biblioteca.api.model.Devolucion;
import biblioteca.api.repository.DevolucionRepository;
import biblioteca.api.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    public List<Devolucion> obtenerTodasLasDevoluciones() {
        return devolucionRepository.findAll();
    }

    public Optional<Devolucion> obtenerDevolucionPorId(Long id) {
        return devolucionRepository.findById(id);
    }

    public Devolucion guardarDevolucion(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    public void eliminarDevolucion(Long id) {
        devolucionRepository.deleteById(id);
    }
}
