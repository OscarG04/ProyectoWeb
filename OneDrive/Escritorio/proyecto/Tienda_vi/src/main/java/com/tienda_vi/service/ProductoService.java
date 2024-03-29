
package com.tienda_vi.service;

import com.tienda_vi.domain.Producto;
import java.util.List;


public interface ProductoService {
    public List<Producto> getProductos(boolean activos);
    
    public Producto  getProducto(Producto producto);
    
    public Producto getProductoById(Long idProducto);
    
    public void save(Producto producto);
    
    public void delete(Producto producto);
}
