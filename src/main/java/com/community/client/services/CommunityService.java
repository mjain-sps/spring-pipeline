package com.community.client.services;

import com.community.client.models.Community;
import com.community.client.repositories.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CommunityService {

    public CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    // Implement the methods from repository
    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Set<Community> getAllCommunities(String keyword) {
        Set<Community> communities;
        if (keyword == null) {
            communities = communityRepository.findAll();
        } else {
            communities = communityRepository.findByKeyword(keyword);
        }
        return communities;
    }

    public Set<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Set<Community> getCommunitySearchResults(String keyword) {
        return communityRepository.findByKeyword(keyword);
    }

    public Community getCommunityById(Long id) {
        Optional<Community> communityOptional = communityRepository.findCommunityById(id);
        if (communityOptional.isPresent()) {
            return communityOptional.get();
        } else {
            return null;
        }
    }
}
