package com.tecsup.integradorbackend.servicio;

import com.tecsup.integradorbackend.modelo.Usuario;
import com.tecsup.integradorbackend.repositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioDetallesServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Converts the user from the database into a Spring Security UserDetails object
        return new User(
                usuario.getCorreo(),
                usuario.getPassword(),
                Collections.emptyList() // Add roles here if necessary
        );
    }
}
