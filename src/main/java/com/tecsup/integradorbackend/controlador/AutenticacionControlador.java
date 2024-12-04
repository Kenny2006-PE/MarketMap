package com.tecsup.integradorbackend.controlador;

import com.tecsup.integradorbackend.dto.LoginDTO;
import com.tecsup.integradorbackend.dto.RegistroDTO;
import com.tecsup.integradorbackend.modelo.Usuario;
import com.tecsup.integradorbackend.repositorio.UsuarioRepositorio;
import com.tecsup.integradorbackend.servicio.UsuarioServicio;
import com.tecsup.integradorbackend.configuracion.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioServicio usuarioServicio;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AutenticacionControlador(UsuarioRepositorio usuarioRepositorio,
                                    UsuarioServicio usuarioServicio,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioServicio = usuarioServicio;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        usuarioServicio.registrarUsuario(registroDTO);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioRepositorio.findByCorreo(loginDTO.correo())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Generar el token JWT
        String token = jwtTokenUtil.generateToken(usuario.getCorreo());

        // Devolver el token en la respuesta
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> obtenerUsuarioAutenticado(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            throw new RuntimeException("No se encontró un usuario autenticado");
        }

        Usuario usuario = usuarioRepositorio.findByCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(Map.of(
                "nombre", usuario.getNombre(),
                "apellido", usuario.getApellido(),
                "correo", usuario.getCorreo()
        ));
    }
}


