package com.prueba.tecnica.services;

import java.util.List;

import com.prueba.tecnica.models.Contact;

public interface IContactService {

    List<Contact> getAllContacts();
    void saveContact(Contact contact);
    void updateContact(Contact contact);
    Contact findContactById(Long id);
}
