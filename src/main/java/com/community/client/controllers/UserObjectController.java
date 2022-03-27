package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.services.UserObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserObjectController {
    //Dependency Injection of user object service
    private final UserObjectService userObjectService;

    public UserObjectController(UserObjectService userObjectService) {
        this.userObjectService = userObjectService;
    }

    //end point to create a user
    @PostMapping("/api/add-user")
    private ResponseEntity<UserObject> addUser(@RequestBody UserObject userObject) {

        try {
            UserObject savedUser = userObjectService.saveUser(userObject);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception handlerException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    ///end point to get all the users(NOT TO BE USED WITHOUT AUTHORIZATION)
    @GetMapping("/api/users")
    private ResponseEntity<Set> getAllUsers() {
        try {
            Set<UserObject> userObjectsSet = userObjectService.getAllUsers();
            return new ResponseEntity<>(userObjectsSet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Get a user by ID
    @GetMapping("/api/get-user/{id}")
    private ResponseEntity<UserObject> getUserById(@PathVariable Long id) {
        try {
            UserObject userObject = userObjectService.getUserById(id);
            return new ResponseEntity<>(userObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //Get a User By Email
    @GetMapping("/api/get-user-email/{email}")
    private ResponseEntity<UserObject> getUserByEmail(@PathVariable String email) {
        try {
            UserObject userObject = userObjectService.getUserByEmail(email);
            return new ResponseEntity<>(userObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
