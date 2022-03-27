package com.community.client.services;
import com.community.client.models.Contact;
import com.community.client.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class ContactService {
    //DI the repository
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // This is the actual method which will be used in the controller
    public Contact saveContact(Contact contact){
        Contact saveContact = contactRepository.save(contact);
        return saveContact;
    }

    //create a method which gets you a set of contacts
    public Set<Contact> getAllContact(){
        return contactRepository.findAll();
    }
}
