package com.tecsup.integradorbackend.servicio;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServicio {

    private final Cloudinary cloudinary;

    public CloudinaryServicio(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String subirImagen(byte[] archivo, String nombre) throws IOException {
        Map<String, Object> params = ObjectUtils.asMap(
                "public_id", nombre,
                "overwrite", true,
                "resource_type", "image"
        );
        Map<?, ?> resultado = cloudinary.uploader().upload(archivo, params);
        return (String) resultado.get("secure_url");
    }

    public String eliminarImagen(String publicId) throws IOException {
        Map<?, ?> resultado = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return (String) resultado.get("result");
    }
}
