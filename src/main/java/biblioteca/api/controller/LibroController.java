package biblioteca.api.controller;

import biblioteca.api.model.Libro;
import biblioteca.api.model.Prestamo;
import biblioteca.api.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private final LibroService libroService;


    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        if (!libroService.obtenerLibroPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libro.setId(id);
        Libro libroActualizado = libroService.guardarLibro(libro);
        return new ResponseEntity<>(libroActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (!libroService.obtenerLibroPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libroService.eliminarLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@RequestParam String titulo) {
        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<Libro>> buscarPorAutor(@RequestParam String autor) {
        List<Libro> libros = libroService.buscarPorAutor(autor);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Libro>> buscarPorCategoria(@RequestParam String categoria) {
        List<Libro> libros = libroService.buscarPorCategoria(categoria);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/mas-prestados")
    public List<Libro> obtenerLibrosMasPrestadosEnPeriodo(
            @RequestParam("fechaInicio") Date fechaInicio,
            @RequestParam("fechaFin") Date fechaFin ) {

        return libroService.obtenerLibrosMasPrestadosEnPeriodo(fechaInicio, fechaFin);
    }
}
