package com.tecsup.integradorbackend.excepcion;

public class UsuarioNoAutorizadoException extends RuntimeException {
    public UsuarioNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}
