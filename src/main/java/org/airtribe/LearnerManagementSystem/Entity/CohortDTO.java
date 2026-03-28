package org.airtribe.LearnerManagementSystem.Entity;

public class CohortDTO {
    private Long id;
    private String name;
    private String description;


    public CohortDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public CohortDTO() {}

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
}
