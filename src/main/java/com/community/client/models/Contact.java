package com.community.client.models;
import javax.persistence.*;
//creating the columns for the 'contact_table'
@Entity
@Table(name = "contact_table")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactSubmission_id")
    private Long id;

    @Column(name = "contact_name", nullable = false)
    private String name;

    @Column(name = "contact_email", nullable = false)
    private String email;

    @Column(name = "contact_phone", nullable = false)
    private String phone;

    @Column(name = "contact_message", nullable = false)
    private String message;

    @Column(name = "contact_upload")
    private String uploadInput;

    // Creating constructor for each column within the table

    public Contact(Long id, String name, String email, String phone, String message, String uploadInput) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.uploadInput = uploadInput;
    }

    public Contact() {

    }


    //Creating Getters and setter for each column within the table


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUploadInput() {
        return uploadInput;
    }

    public void setUploadInput(String uploadInput) {
        this.uploadInput = uploadInput;
    }
}
