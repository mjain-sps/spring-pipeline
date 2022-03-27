package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.requests.LoginRequest;
import com.community.client.services.UserObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Represents the REST controller for handling login requests by comparing user input
 * with user account information stored in the user_table database.
 */
@RestController
public class LoginController {


    // Dependency Injection. Use of dependency injection reduces coupling.
    private UserObjectService userObjectService;

    private LoginController(UserObjectService userObjectService) {

        this.userObjectService = userObjectService;
    }

    /**
     * Handles HTTP POST requests. Compares the email and password from a user login attempt
     * to emails and passwords stored within the user_table database. If both the email and
     * password match an existing user, login is successful and a cookie can be set using
     * the information returned by this method. A try-catch block was included to provide
     * an exception handing mechanism for bad requests so that a 400 error could be sent
     * from the backend to the frontend when a login request was incorrectly formatted
     * or corrupt.
     * @param loginRequest JSON containing email and password submitted from the frontend.
     * @return loggedInUser JSON containing user information from user_table database.
     */
    @PostMapping("/api/login-user")
    private Object loginUser(@RequestBody LoginRequest loginRequest){

        String emailFromLoginRequest = loginRequest.getEmail();
        String passwordFromLoginRequest = loginRequest.getPassword();

        UserObject loggedInUser = null;

        try {
            UserObject userObjectFound = userObjectService.getUserByEmail(emailFromLoginRequest);
            if (userObjectFound != null) {
                String passwordFromDB = userObjectFound.getPassword();
                if (passwordFromDB.equals(passwordFromLoginRequest)) {
                    loggedInUser = userObjectFound;
                }
            }
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
