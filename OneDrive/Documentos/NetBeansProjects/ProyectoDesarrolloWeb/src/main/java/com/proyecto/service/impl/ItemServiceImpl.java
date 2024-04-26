package com.proyecto.service.impl;

import com.proyecto.dao.FacturaDao;
import com.proyecto.dao.VentaDao;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Usuario;
import com.proyecto.domain.Factura;
import com.proyecto.domain.Item;
import com.proyecto.domain.Venta;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.proyecto.dao.ProductoDao;
import com.proyecto.dao.UsuarioDao;
import com.proyecto.service.ItemService;
import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {

    // Define la lista listaItems
    private List<Item> listaItems = new ArrayList<>();

    @Override
    public List<Item> gets() {
        return listaItems;
    }


    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el producto en el carrito se agrega cantidad =1.            
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    //Se usa para eliminar un producto del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Se obtiene la información de un producto del carrito... para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la página para actualizar la cantidad de productos
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private ProductoDao productoDao;

    @Override
    public void facturar() {
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails userDetails) {
        username = userDetails.getUsername();
    } else {
        username = principal.toString();
    }

    if (username.isBlank()) {
        System.out.println("Nombre de usuario en blanco");
        return;
    }

    Usuario usuario = usuarioDao.findByUsername(username);
    if (usuario == null) {
        System.out.println("Usuario no encontrado: " + username);
        return;
    }

    Factura factura = new Factura(usuario.getIdUsuario());
    factura = facturaDao.save(factura);

    double total = 0;
    for (Item i : listaItems) {
        System.out.println("Procesando producto: " + i.getDescripcion());
        Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
        ventaDao.save(venta);
        Producto producto = productoDao.getById(i.getIdProducto());
        producto.setExistencias(producto.getExistencias() - i.getCantidad());
        productoDao.save(producto);
        total += i.getCantidad() * i.getPrecio();
    }

    factura.setTotal(total);
    facturaDao.save(factura);
    listaItems.clear();
}
}
