package com.tecsup.integradorbackend;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CloudinaryTest {

    @Autowired
    private Cloudinary cloudinary;

    @Test
    void testCloudinaryUpload() throws Exception {
        System.out.println("Probando carga en Cloudinary...");

        // Ruta a la imagen
        File file = new File("C:/Imagenes prueba/kenny.jpg");

        // Sube la imagen a Cloudinary
        Map<?, ?> resultado = cloudinary.uploader().upload(file, Map.of());
        System.out.println("Resultado de la carga: " + resultado);

        // Valida que la URL de la imagen subida no sea nula
        assertNotNull(resultado.get("secure_url"), "La URL de la imagen subida debe ser válida.");
    }
}
