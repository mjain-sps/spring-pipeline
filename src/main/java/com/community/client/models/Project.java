package com.community.client.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "project_table")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id",updatable = false,nullable = false)
    private Long id;

    @Column(name = "project_name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "funds_required")
    private BigDecimal fundsRequired;

    @Column(name = "funds_received")
    private BigDecimal fundsCollected;

    @Column(name = "creator_userid",nullable = false)
    private Long creatorUserId;

    @JsonIgnoreProperties("projectSet")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id",nullable = false)
    private Community community;

    @Column(name = "project_cover_image")
    private String projectCoverImage;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    private Set<Event> event;

    //constructor
    public Project() {
    }

    public Project(Long id, String name, String description, BigDecimal fundsRequired,BigDecimal fundsCollected,String projectCoverImage,Long creatorUserId,Community community) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fundsRequired = fundsRequired;
        this.fundsCollected = fundsCollected;
        this.projectCoverImage = projectCoverImage;
        this.creatorUserId = creatorUserId;
        this.community = community;
    }
    public Project (Long id) {
        this.id = id;
    }

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

    public BigDecimal getFundsRequired() {
        return fundsRequired;
    }

    public void setFundsRequired(BigDecimal fundsRequired) {
        this.fundsRequired = fundsRequired;
    }

    public BigDecimal getFundsCollected() {
        return fundsCollected;
    }

    public void setFundsCollected(BigDecimal fundsCollected) {
        this.fundsCollected = fundsCollected;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getProjectCoverImage() {
        return projectCoverImage;
    }

    public void setProjectCoverImage(String projectCoverImage) {
        this.projectCoverImage = projectCoverImage;
    }

    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fundsRequired=" + fundsRequired +
                ", fundsCollected=" + fundsCollected +
                ", creatorUserId=" + creatorUserId +
                ", community=" + community +
                ", projectCoverImage='" + projectCoverImage + '\'' +
                '}';
    }
}
