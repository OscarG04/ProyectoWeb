
package com.proyecto.service;

import com.proyecto.domain.Producto;
import java.util.List;


public interface ProductoService {
    public List<Producto> getProductos(boolean activos);
    
    public Producto  getProducto(Producto producto);
    
    public Producto getProductoById(Long idProducto);
    
    public void save(Producto producto);
    
    public void delete(Producto producto);
    
      public List<Producto> consultaJPA(double precioInf, double precioSup);
    
    //Se define una consulta tipo JPQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    //Se define una consulta tipo SQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    public List<Producto> consultaSQL(double precioInf, double precioSup);
}
