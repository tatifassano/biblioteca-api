package biblioteca.api.service;

import biblioteca.api.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria guardarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    List<Categoria> obtenerTodasLasCategorias();

}
