package com.community.client.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;


@Entity
@Table(name = "user_table")
@NamedQueries ({
        @NamedQuery(name = "UserObject.getEmail",
        query = "SELECT b FROM UserObject b WHERE b.name = :name")
        })

public class UserObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "userObject", cascade = CascadeType.ALL)
    private UserProfile userProfile;



    //create a join table to implement the ManyToMany relations between user and community
    @JsonIgnoreProperties("userObjectSet")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_relates_community",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id"))
    private Set<Community> communitySet = new HashSet<>();



    //Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Community> getCommunitySet() {
        return communitySet;
    }

    public void setCommunitySet(Set<Community> communitySet) {
        this.communitySet = communitySet;
    }
    //No Args Constructor

    public UserObject() {
    }

    public UserObject(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }



    @Override
    public String toString() {
        return "UserObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userProfile=" + userProfile +
                ", communitySet=" + communitySet +
                '}';
    }
}
