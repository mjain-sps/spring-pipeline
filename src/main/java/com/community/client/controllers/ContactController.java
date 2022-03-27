package com.community.client.controllers;
import com.community.client.models.Contact;
import com.community.client.services.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ContactController {
    //DI for the Service layer
    private final ContactService contactService;


    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    //API endpoint will be defined here
    //1. End Point is to save the user's information and enquires
    @PostMapping("/api/new-contact")
    public Contact saveNewContact(@RequestBody Contact contact){
        System.out.println("I am being hit");
        return contactService.saveContact(contact);
    }

    // End Point to get all the Contact details/queries
    @GetMapping("api/contact")
    private Set<Contact> getAllContact(){
        return contactService.getAllContact();
    }

}
