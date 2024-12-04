package com.tecsup.integradorbackend.controlador;

import com.tecsup.integradorbackend.dto.ProductoDTO;
import com.tecsup.integradorbackend.modelo.Producto;
import com.tecsup.integradorbackend.servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarProductos() {
        List<Producto> productos = productoServicio.buscarProductos("");

        // Convertir los productos en una lista de respuestas personalizadas
        List<Map<String, Object>> respuesta = productos.stream()
                .map(producto -> Map.of(
                        "id", producto.getId(),
                        "nombre", producto.getNombre(),
                        "estado", producto.getEstado(),
                        "descripcion", producto.getDescripcion(),
                        "imagenUrl", producto.getImagenUrl(),
                        "precio", producto.getPrecio(),
                        "latitud", producto.getLatitud(),
                        "longitud", producto.getLongitud(),
                        "vendedor", Map.of(
                                "id", producto.getVendedor().getId(),
                                "nombre", producto.getVendedor().getNombre(),
                                "apellido", producto.getVendedor().getApellido(),
                                "correo", producto.getVendedor().getCorreo()
                        )
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<List<Map<String, Object>>> listarProductosPorVendedor(@PathVariable Long vendedorId) {
        List<Producto> productos = productoServicio.buscarProductosPorVendedor(vendedorId);

        // Convertir los productos en una lista de respuestas personalizadas usando HashMap
        List<Map<String, Object>> respuesta = productos.stream()
                .map(producto -> {
                    Map<String, Object> productoMap = new HashMap<>();
                    productoMap.put("id", producto.getId());
                    productoMap.put("nombre", producto.getNombre());
                    productoMap.put("estado", producto.getEstado());
                    productoMap.put("descripcion", producto.getDescripcion());
                    productoMap.put("imagenUrl", producto.getImagenUrl());
                    productoMap.put("precio", producto.getPrecio());
                    productoMap.put("latitud", producto.getLatitud());
                    productoMap.put("longitud", producto.getLongitud());
                    return productoMap;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> publicarProducto(
            @RequestBody ProductoDTO productoDTO,
            @RequestParam Long vendedorId // ID del vendedor como parámetro
    ) {
        Producto producto = productoServicio.crearProducto(productoDTO, vendedorId);

        // Crear una respuesta personalizada
        Map<String, Object> respuesta = Map.of(
                "id", producto.getId(),
                "nombre", producto.getNombre(),
                "estado", producto.getEstado(),
                "descripcion", producto.getDescripcion(),
                "imagenUrl", producto.getImagenUrl(),
                "precio", producto.getPrecio(),
                "latitud", producto.getLatitud(),
                "longitud", producto.getLongitud(),
                "vendedor", Map.of(
                        "id", producto.getVendedor().getId(),
                        "nombre", producto.getVendedor().getNombre(),
                        "apellido", producto.getVendedor().getApellido(),
                        "correo", producto.getVendedor().getCorreo()
                )
        );

        return ResponseEntity.ok(respuesta);
    }
}


