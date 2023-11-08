package biblioteca.api.controller;

import biblioteca.api.model.Devolucion;
import biblioteca.api.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<List<Devolucion>> obtenerTodasLasDevoluciones() {
        List<Devolucion> devoluciones = devolucionService.obtenerTodasLasDevoluciones();
        return new ResponseEntity<>(devoluciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> obtenerDevolucionPorId(@PathVariable Long id) {
        return devolucionService.obtenerDevolucionPorId(id)
                .map(devolucion -> new ResponseEntity<>(devolucion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Devolucion> crearDevolucion(@RequestBody Devolucion devolucion) {
        Devolucion nuevaDevolucion = devolucionService.guardarDevolucion(devolucion);
        return new ResponseEntity<>(nuevaDevolucion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizarDevolucion(@PathVariable Long id, @RequestBody Devolucion devolucion) {
        if (!devolucionService.obtenerDevolucionPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        devolucion.setId(id);
        Devolucion devolucionActualizada = devolucionService.guardarDevolucion(devolucion);
        return new ResponseEntity<>(devolucionActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDevolucion(@PathVariable Long id) {
        if (!devolucionService.obtenerDevolucionPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        devolucionService.eliminarDevolucion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
