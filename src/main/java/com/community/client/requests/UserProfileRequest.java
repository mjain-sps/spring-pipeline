package com.community.client.requests;

import javax.persistence.Column;

public class UserProfileRequest {
    private String name;
    private String email;
    private String aboutYourSelf;
    private String qualifications;
    private String hobbies;
    private String achievements;
    private int phone;
    private String lineOneAddress;
    private String lineTwoAddress;
    private String city;
    private String postCode;
    private String country;

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

    public String getLineOneAddress() {
        return lineOneAddress;
    }

    public void setLineOneAddress(String lineOneAddress) {
        this.lineOneAddress = lineOneAddress;
    }

    public String getLineTwoAddress() {
        return lineTwoAddress;
    }

    public void setLineTwoAddress(String lineTwoAddress) {
        this.lineTwoAddress = lineTwoAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserProfileRequest(String name, String email, String aboutYourSelf, String qualifications, String hobbies, String achievements, int phone, String lineOneAddress, String lineTwoAddress, String city, String postCode, String country) {
        this.name = name;
        this.email = email;
        this.aboutYourSelf = aboutYourSelf;
        this.qualifications = qualifications;
        this.hobbies = hobbies;
        this.achievements = achievements;
        this.phone = phone;
        this.lineOneAddress = lineOneAddress;
        this.lineTwoAddress = lineTwoAddress;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public UserProfileRequest() {
    }
}
