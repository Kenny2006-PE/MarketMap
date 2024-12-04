package com.tecsup.integradorbackend.servicio;

import com.tecsup.integradorbackend.dto.RegistroDTO;
import com.tecsup.integradorbackend.modelo.Usuario;
import com.tecsup.integradorbackend.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrarUsuario(RegistroDTO registroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.nombre());
        usuario.setApellido(registroDTO.apellido());
        usuario.setCorreo(registroDTO.correo());
        usuario.setPassword(passwordEncoder.encode(registroDTO.password()));
        usuario.setDni(registroDTO.dni());
        usuario.setNumero(registroDTO.numero());
        return usuarioRepositorio.save(usuario);
    }
}
