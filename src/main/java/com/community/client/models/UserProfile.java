package com.community.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private long id;

    @Column(name = "about_yourself")
    private String aboutYourSelf;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "achievements")
    private String achievements;

    @Column(name = "phone")
    private int phone;

    @OneToOne(mappedBy = "userProfile")
    private Address address;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserObject userObject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAboutYourSelf() {
        return aboutYourSelf;
    }

    public void setAboutYourSelf(String aboutYourSelf) {
        this.aboutYourSelf = aboutYourSelf;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}
}

