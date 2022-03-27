package com.community.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community_table")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", updatable = false)
    private Long id;

    @Column(name = "community_name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", length = 800, nullable = false)
    private String description;

    @OneToMany(mappedBy = "community")
    @JsonIgnoreProperties("community")
    private Set<Event> eventSet = new HashSet<>();

    @JsonIgnoreProperties("communitySet")
    @ManyToMany(mappedBy = "communitySet", fetch = FetchType.EAGER)
    private Set<UserObject> userObjectSet = new HashSet<>();

    @OneToMany(mappedBy = "community", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("community")
    private Set<Project> projectSet = new HashSet<>();

    // Adding the field to handle the image name
    @Column(name = "community_image")
    private String communityImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserObject> getUserObjectSet() {
        return userObjectSet;
    }

    public void setUserObjectSet(Set<UserObject> userObjectSet) {
        this.userObjectSet = userObjectSet;
    }

    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

    public String getCommunityImage() {
        return communityImage;
    }

    public void setCommunityImage(String communityImage) {
        this.communityImage = communityImage;
    }


    public Set<Event> getEvent() {
        return this.eventSet;
    }

    public void setEvent(Set<Event> eventSet) {
        this.eventSet = eventSet;
    }
   



    public Community(Long id) {
        this.id = id;
    }

    //No Args Constructor
    public Community(){

    }


    public Community(Long id, String name, String description,  String communityImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.communityImage = communityImage;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", event='" + getEvent() + "'" +
                ", userObjectSet='" + getUserObjectSet() + "'" +
                ", projectSet='" + getProjectSet() + "'" +
                ", communityImage='" + getCommunityImage() + "'" +
                "}";
    }

    public void addUserToCommunity(UserObject userObject) {
        userObjectSet.add(userObject);
    }
}
