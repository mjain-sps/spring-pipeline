package com.community.client.models;

import javax.persistence.*;

@Entity
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "line1_address")
    private String line1Address;

    @Column(name = "line2_address")
    private String line2Address;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "country")
    private String country;

    @OneToOne
    private UserProfile userProfile;



    //Getters and Setters

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getLine1Address() {return line1Address;}

    public void setLine1Address(String line1Address) {this.line1Address = line1Address;}

    public String getLine2Address() {return line2Address;}

    public void setLine2Address(String line2Address) {this.line2Address = line2Address;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getPostcode() {return postcode;}

    public void setPostcode(String postcode) {this.postcode = postcode;}

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Address() {
    }

    public Address(Long id, String line1Address, String line2Address, String city, String postcode, String country) {
        this.id = id;
        this.line1Address = line1Address;
        this.line2Address = line2Address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1Address='" + line1Address + '\'' +
                ", line2Address='" + line2Address + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
