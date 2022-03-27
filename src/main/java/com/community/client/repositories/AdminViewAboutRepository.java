package com.community.client.repositories;
import com.community.client.models.AdminViewAbout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface AdminViewAboutRepository extends CrudRepository<AdminViewAbout,Long>{
    //This line of code below will save the user's input (example: queries and user details)
    AdminViewAboutRepository save(AdminViewAboutRepository adminAbout);

    //Finding the data stored through the database for adminViewAbout
    Set<AdminViewAbout> findAll();
}
