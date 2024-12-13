package com.prueba.tecnica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.models.Contact;
import com.prueba.tecnica.repositories.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact); // Guardar el contacto en la base de datos
    }
    
    @Override
    public void updateContact(Contact contact) {
        if (contact.getId() != null && contactRepository.existsById(contact.getId())) {
        	
            contactRepository.save(contact); // Actualizar el contacto existente
        } else {
            throw new IllegalArgumentException("No se puede actualizar un contacto que no existe.");
        }
    }

	@Override
	public Contact findContactById(Long id) {		
		return contactRepository.findById(id).orElse(null);
	}

}
