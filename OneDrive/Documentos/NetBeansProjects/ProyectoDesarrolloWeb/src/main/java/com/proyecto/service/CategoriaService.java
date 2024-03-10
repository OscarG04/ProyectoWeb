
package com.proyecto.service;

import com.proyecto.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    public List<Categoria> getCategorias(boolean activos);
    
    public Categoria  getCategoria(Categoria categoria);
    
    public Categoria getCategoriaById(Long idCategoria);
    
    public void save(Categoria categoria);
    
    public void delete(Categoria categoria);
}
