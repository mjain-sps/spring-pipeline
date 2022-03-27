package com.community.client.repositories;
import com.community.client.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
    //This line of code below will save the user's input (example: queries and user details)
    Contact save(Contact contact);

    //Find all contact
    Set<Contact> findAll();
}
