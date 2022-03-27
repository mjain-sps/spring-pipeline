package com.community.client.community;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.community.client.models.Community;

import org.junit.jupiter.api.Test;

public class UnitTests {

    // Tests for getters 
    @Test
    public void CommunityGettersTests(){
        Community community = new Community(1L,"test-community", "test-community-description", "test-community-image" );
        assertEquals(1L, community.getId());
        assertEquals("test-community", community.getName());
        assertEquals("test-community-description", community.getDescription());
        assertEquals("test-community-image", community.getCommunityImage());
    }

    // Tests for setters 
    @Test
    public void CommunitySettersTests(){
        Community community = new Community();
        community.setId(1L);
        community.setName("test-community");
        community.setDescription("test-community-description");
        community.setCommunityImage("test-community-image");

        assertEquals(1L, community.getId());
        assertEquals("test-community", community.getName());
        assertEquals("test-community-description", community.getDescription());
        assertEquals("test-community-image", community.getCommunityImage());
    }
    
}
