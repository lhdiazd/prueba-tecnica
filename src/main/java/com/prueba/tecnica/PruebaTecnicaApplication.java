package com.prueba.tecnica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.prueba.tecnica.controllers.ContactController;
import com.prueba.tecnica.views.ContactView;

@SpringBootApplication
public class PruebaTecnicaApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(PruebaTecnicaApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
