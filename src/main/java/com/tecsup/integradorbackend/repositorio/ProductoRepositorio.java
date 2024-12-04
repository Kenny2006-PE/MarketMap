package com.tecsup.integradorbackend.repositorio;

import com.tecsup.integradorbackend.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByVendedorId(Long vendedorId); // Método para buscar por vendedor
}
