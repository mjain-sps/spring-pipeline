package com.community.client.controllers;

import com.community.client.models.Address;
import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.models.UserProfile;
import com.community.client.requests.UserProfileRequest;
import com.community.client.services.AddressService;
import com.community.client.services.UserObjectService;
import com.community.client.services.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {
    private final UserObjectService userObjectService;
    private final UserProfileService userProfileService;
    private final AddressService addressService;

    public UserProfileController(UserObjectService userObjectService, UserProfileService userProfileService, AddressService addressService) {
        this.userObjectService = userObjectService;
        this.userProfileService = userProfileService;
        this.addressService = addressService;
    }

    @PutMapping("/api/update-profile/{userId}")
    private UserObject createProfile(@RequestBody UserProfileRequest userProfileRequest , @PathVariable Long userId){

        //steps

        //1 We want to get the UserObject by the ID which we have from the path variable
        UserObject userObject = userObjectService.getUserById(userId);
        //if user exists, then we create a new user profile object
        if(userObject != null){

            //If the profile exists, then load that profile otherwise create a new profile.
            UserProfile userProfile;
            Address address;
            //check if the userObject has a profile associated already
            UserProfile userProfileFound = userObject.getUserProfile();
            if(userProfileFound == null){
                userProfile = new UserProfile();
                address = new Address();
            }else{
                userProfile = userProfileFound;
                address = userProfile.getAddress();
                if(address ==null){
                    address = new Address();
                }
            }

            //We set the user profile fields with the payload we have received
            userProfile.setAboutYourSelf(userProfileRequest.getAboutYourSelf());
            userProfile.setAchievements(userProfileRequest.getAchievements());
            userProfile.setHobbies(userProfileRequest.getHobbies());
            userProfile.setPhone(userProfileRequest.getPhone());
            userProfile.setQualifications(userProfileRequest.getQualifications());
            userProfile.setUserObject(userObject);
            //userProfileService.saveProfile(userProfile);

            //update the address from the payload received in the form of request object
            address.setLine1Address(userProfileRequest.getLineOneAddress());
            address.setLine2Address(userProfileRequest.getLineTwoAddress());
            address.setCity(userProfileRequest.getCity());
            address.setPostcode(userProfileRequest.getPostCode());
            address.setCountry(userProfileRequest.getCountry());
            Address savedAddress = addressService.saveAddress(address);

            //update the user profile with the saved address
            userProfile.setAddress(savedAddress);

            //now we save the user profile
            userProfileService.saveProfile(userProfile);

            userObject.setName(userProfileRequest.getName());
            userObject.setEmail(userProfileRequest.getEmail());

            return userObjectService.saveUser(userObject);
        }else{
            return  null;
        }
    }

    //get an address by id
    @GetMapping("/api/get-address/{id}")
    private Address getAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }
}
