package org.airtribe.LearnerManagementSystem.dto;

import org.airtribe.LearnerManagementSystem.Validation.constraints.NotBlank;

public record CourseCreateRequest(
        @NotBlank(message = "name is required") String name,
        String description
) {
}

