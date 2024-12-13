package com.prueba.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prueba.tecnica.services.IContactService;
import com.prueba.tecnica.views.ContactView;

@Controller
public class ContactController {
    private final IContactService contactService;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Muestra la vista inicial de contactos.
     *
     * @param contactView Vista de contactos que se mostrará.
     */
    public void showContactView(ContactView contactView) {
        contactView.showView(); 
    }
}
