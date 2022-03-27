package com.community.client.repositories;

import com.community.client.models.UserObject;
import com.community.client.models.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile,Long> {
    //1. Save the user profile
    UserProfile save(UserProfile userProfile);
}
