package com.community.client.services;

import com.community.client.models.UserProfile;
import com.community.client.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile saveProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }
}
