package org.airtribe.LearnerManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCreateRequest(
        @NotBlank(message = "name is required") String name,
        String description
) {
}

