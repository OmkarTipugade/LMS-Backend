package org.airtribe.LearnerManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LearnerCreateRequest(
        @NotBlank(message = "name is required") String name,
        @NotBlank(message = "email is required") @Email(message = "email must be valid") String email,
        @Min(value = 1, message = "age must be at least 1") @Max(value = 120, message = "age must be at most 120") int age
) {
}

