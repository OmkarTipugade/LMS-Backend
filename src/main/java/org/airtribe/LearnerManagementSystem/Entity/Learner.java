package org.airtribe.LearnerManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String name;
    private  String email;
    private  int age;

    @ManyToMany(mappedBy = "learners")
    @JsonIgnore
    private List<Cohort> cohorts = new ArrayList<>();

    public Learner(Long id, String name, String email, int age, List<Cohort> cohorts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.cohorts = cohorts == null ? new ArrayList<>() : cohorts;
    }

    public Learner() {}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public List<Cohort> getCohorts() {
        return cohorts;
    }
    public void setCohorts(List<Cohort> cohorts) {
        this.cohorts = cohorts == null ? new ArrayList<>() : cohorts;
    }
}
