package com.tecsup.integradorbackend.dto;



public record RegistroDTO(
        String nombre,
        String apellido,
        String correo,
        String password,
        String confirmarContraseña,
        String dni,
        String numero
) {}
