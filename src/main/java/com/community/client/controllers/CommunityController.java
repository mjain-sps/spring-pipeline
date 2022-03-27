package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.services.CommunityService;
import com.community.client.services.UserObjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CommunityController {
    private final CommunityService communityService;
    private final UserObjectService userObjectService;

    public CommunityController(CommunityService communityService, UserObjectService userObjectService) {
        this.communityService = communityService;
        this.userObjectService = userObjectService;
    }

    // end point to create/update a community information
    @PostMapping("/api/add-community/{userId}")
    private ResponseEntity<Community> addCommunity(@RequestBody Community community, @PathVariable Long userId) {
        try {
            Community savedCommunity = communityService.saveCommunity(community);
            UserObject userObject = userObjectService.getUserById(userId);
            Set<Community> communitySet = userObject.getCommunitySet();
            communitySet.add(savedCommunity);
            userObject.setCommunitySet(communitySet);
            // the user who create this community will automatically join this community
            userObjectService.saveUser(userObject);
            return new ResponseEntity<>(savedCommunity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /// This method is for joining the community
    @PutMapping("/api/update-community/userid/{userId}/community-id/{communityId}")
    private ResponseEntity<UserObject> addUserToCommunity(@PathVariable Long userId, @PathVariable Long communityId) {
        try {
            UserObject userObject = userObjectService.getUserById(userId);
            Community community = communityService.getCommunityById(communityId);
            Set<Community> communitySet = userObject.getCommunitySet();
            communitySet.add(community);
            userObject.setCommunitySet(communitySet);
            UserObject updatedUser = userObjectService.saveUser(userObject);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // end point to get all communities
    @GetMapping("/api/communities")
    private ResponseEntity<Set<Community>> getAllCommunities() {
        try {
            Set<Community> communities = communityService.getAllCommunities();
            return new ResponseEntity<>(communities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/get-community/{id}")
    private ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(communityService.getCommunityById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
