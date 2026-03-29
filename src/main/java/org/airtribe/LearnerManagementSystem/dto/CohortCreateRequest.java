package org.airtribe.LearnerManagementSystem.dto;

import org.airtribe.LearnerManagementSystem.Validation.constraints.NotBlank;

public record CohortCreateRequest(
        @NotBlank(message = "name is required") String name,
        String description,
        Long courseId
) {
}

