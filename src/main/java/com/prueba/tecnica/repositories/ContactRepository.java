package com.prueba.tecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.tecnica.models.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {

}
