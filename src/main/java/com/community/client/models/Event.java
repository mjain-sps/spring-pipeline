package com.community.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;


@Entity
@Table(name = "event_table")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "community_id")
    @JsonIgnoreProperties("event")
    private Community community;

    @ManyToOne
    @JoinColumn (name = "project_id")
    @JsonIgnoreProperties("event")
    private Project project;

    @Column (name = "event_date", nullable = false)
    private String date;

    @Column (name = "event_name", nullable = false)
    private String name;

    @Column (name = "event_address", nullable = false)
    private String address;

    @Lob
    @Column (name = "about_section", length = 800, nullable = false)
    private String aboutSection;

    @Column (name = "event_contributors", nullable = false)
    private String contributors;

    @Column(name = "creator_userid",nullable = false)
    private String creatorUserId;

    @Column(name = "event_image")
    private String eventImage;

    public Event(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutSection() {
        return aboutSection;
    }

    public void setAboutSection(String aboutSection) {
        this.aboutSection = aboutSection;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getEventImage() { return eventImage; }

    public void setEventImage(String eventImage) { this.eventImage = eventImage; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Event(Long id, Community community, Project project, String date, String name, String address, String aboutSection, String contributors, String creatorUserId, String eventImage) {
        this.id = id;
        this.community = community;
        this.project = project;
        this.date = date;
        this.name = name;
        this.address = address;
        this.aboutSection = aboutSection;
        this.contributors = contributors;
        this.creatorUserId = creatorUserId;
        this.eventImage = eventImage;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", community=" + community +
                ", project=" + project +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", aboutSection='" + aboutSection + '\'' +
                ", contributors='" + contributors + '\'' +
                ", creatorUserId='" + creatorUserId + '\'' +
                ", eventImage='" + eventImage + '\'' +
                '}';
    }
}
