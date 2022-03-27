package com.community.client.models;
import javax.persistence.*;
//creating the columns for the 'adminAbout_table'
@Entity
@Table(name = "admin_View_About_table")
public class AdminViewAbout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientQuery_id")
    private Long id;

    @Column(name = "about_Title")
    private String aboutTitle;

    @Column(name = "about_Body")
    private String aboutBody;

    //Creating Getters and setter for each column within the table
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAboutTitle() {
        return aboutTitle;
    }

    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    public String getAboutBody() {
        return aboutBody;
    }

    public void setAboutBody(String aboutBody) {
        this.aboutBody = aboutBody;
    } // this section is completed
}


