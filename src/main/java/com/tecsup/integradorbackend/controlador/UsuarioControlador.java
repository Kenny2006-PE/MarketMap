package com.tecsup.integradorbackend.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        // Lógica para obtener un usuario por ID
        return ResponseEntity.ok("Información del usuario");
    }
}
