package com.tecsup.integradorbackend.servicio;

import com.tecsup.integradorbackend.dto.RegistroDTO;
import com.tecsup.integradorbackend.modelo.Usuario;

public interface UsuarioServicio {
    Usuario registrarUsuario(RegistroDTO registroDTO);
}
