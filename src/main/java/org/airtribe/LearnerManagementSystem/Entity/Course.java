package org.airtribe.LearnerManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "course")
    private List<Cohort> cohorts = new ArrayList<>();

    public Course(Long id, String name, String description, List<Cohort> cohorts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cohorts = cohorts == null ? new ArrayList<>() : cohorts;
    }

    public Course() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Cohort> getCohorts() {
        return cohorts;
    }
    public void setCohorts(List<Cohort> cohorts) {
        this.cohorts = cohorts == null ? new ArrayList<>() : cohorts;
    }
}
