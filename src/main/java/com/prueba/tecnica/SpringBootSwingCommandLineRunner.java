package com.prueba.tecnica;

import java.awt.EventQueue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prueba.tecnica.controllers.ContactController;
import com.prueba.tecnica.views.ContactView;

@Component
public class SpringBootSwingCommandLineRunner implements CommandLineRunner {
	private final ContactController controller;
    private final ContactView contactView;

    public SpringBootSwingCommandLineRunner(ContactController controller, ContactView contactView) {
        this.controller = controller;
        this.contactView = contactView;
    }

    @Override
    public void run(String... args) {
        // Ejecuta el método showContactView en un hilo separado para inicializar la interfaz gráfica Swing
        EventQueue.invokeLater(() -> controller.showContactView(contactView));
    }
}
