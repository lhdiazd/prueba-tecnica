package com.prueba.tecnica;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Punto de entrada para la aplicación Spring Boot.
 */
@SpringBootApplication
public class PruebaTecnicaApplication {

    /**
     * Método principal que arranca la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        // Configura la aplicación para ejecutarse sin interfaz gráfica (headless)
        new SpringApplicationBuilder(PruebaTecnicaApplication.class)
                .headless(false)
                // Configura el tipo de aplicación como no web
                .web(WebApplicationType.NONE)
                // Ejecuta la aplicación con las configuraciones especificadas
                .run(args);
    }
}