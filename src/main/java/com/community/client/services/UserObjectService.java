package com.community.client.services;

import com.community.client.models.UserObject;
import com.community.client.repositories.UserObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class UserObjectService {

    public UserObjectRepository userObjectRepository;

    public UserObjectService(UserObjectRepository userObjectRepository) {
        this.userObjectRepository = userObjectRepository;
    }

    //Implement the methods from repository
    //1. To save a User
    public UserObject saveUser(UserObject userObject) {

        return userObjectRepository.save(userObject);
    }

    //2. To get all users
    public Set<UserObject> getAllUsers() {
        return userObjectRepository.findAll();
    }

    //3. To get user by id
    public UserObject getUserById(Long id) {
        Optional<UserObject> userObjectOptional = userObjectRepository.findById(id);
        if (userObjectOptional.isPresent()) {
            return userObjectOptional.get();
        } else {
            return null;
        }
    }

    //4. To get user by email
    public UserObject getUserByEmail(String email) {
        Optional<UserObject> userObjectOptional = userObjectRepository.findUserObjectByEmail(email);
        if (userObjectOptional.isPresent()) {
            return userObjectOptional.get();
        } else {
            return null;
        }
    }

    //5. To get user by password
    public UserObject getUserByPassword(String password) {
        Optional<UserObject> userObjectOptional = userObjectRepository.findUserObjectByPassword(password);
        if (userObjectOptional.isPresent()) {
            return userObjectOptional.get();
        } else {
            return null;
        }
    }

}
