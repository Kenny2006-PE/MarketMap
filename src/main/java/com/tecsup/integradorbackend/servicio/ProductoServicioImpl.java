package com.tecsup.integradorbackend.servicio;

import com.tecsup.integradorbackend.dto.ProductoDTO;
import com.tecsup.integradorbackend.excepcion.ProductoNoEncontradoException;
import com.tecsup.integradorbackend.modelo.Producto;
import com.tecsup.integradorbackend.modelo.Usuario;
import com.tecsup.integradorbackend.repositorio.ProductoRepositorio;
import com.tecsup.integradorbackend.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.productoRepositorio = productoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Producto crearProducto(ProductoDTO productoDTO, Long vendedorId) {
        Usuario vendedor = usuarioRepositorio.findById(vendedorId)
                .orElseThrow(() -> new ProductoNoEncontradoException("Vendedor no encontrado"));
        Producto producto = new Producto();
        producto.setNombre(productoDTO.nombre());
        producto.setEstado(productoDTO.estado());
        producto.setDescripcion(productoDTO.descripcion());
        producto.setImagenUrl(productoDTO.imagenUrl());
        producto.setPrecio(productoDTO.precio());
        producto.setLatitud(productoDTO.latitud());
        producto.setLongitud(productoDTO.longitud());
        producto.setVendedor(vendedor);
        return productoRepositorio.save(producto);
    }

    @Override
    public List<Producto> buscarProductos(String nombre) {
        return productoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Producto> buscarProductosPorVendedor(Long vendedorId) { // Implementación del nuevo método
        return productoRepositorio.findByVendedorId(vendedorId);
    }

    @Override
    public void eliminarProducto(Long productoId) {
        if (!productoRepositorio.existsById(productoId)) {
            throw new ProductoNoEncontradoException("Producto no encontrado");
        }
        productoRepositorio.deleteById(productoId);
    }
}

