package com.tecsup.integradorbackend.dto;


public record ProductoDTO(
        String nombre,
        String estado,
        String descripcion,
        String imagenUrl,
        double precio,
        double latitud,
        double longitud
) {}
