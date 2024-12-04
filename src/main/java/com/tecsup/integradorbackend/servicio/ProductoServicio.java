package com.tecsup.integradorbackend.servicio;

import com.tecsup.integradorbackend.dto.ProductoDTO;
import com.tecsup.integradorbackend.modelo.Producto;

import java.util.List;

public interface ProductoServicio {
    Producto crearProducto(ProductoDTO productoDTO, Long vendedorId);
    List<Producto> buscarProductos(String nombre);
    List<Producto> buscarProductosPorVendedor(Long vendedorId); // Nuevo método
    void eliminarProducto(Long productoId);
}
