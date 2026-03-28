package org.airtribe.LearnerManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cohort {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    private List<Learner> learners;

    @ManyToOne
    private Course course;

    public Cohort() {
    }


    public Cohort(Long id, String name, String description, List<Learner> learners) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.learners = learners;
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

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
