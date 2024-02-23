
package com.tienda_vi.service;

import com.tienda_vi.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    public List<Categoria> getCategoria(boolean activos);
}
