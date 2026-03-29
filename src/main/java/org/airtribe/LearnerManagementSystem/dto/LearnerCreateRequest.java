package org.airtribe.LearnerManagementSystem.dto;

import org.airtribe.LearnerManagementSystem.Validation.constraints.Email;
import org.airtribe.LearnerManagementSystem.Validation.constraints.Max;
import org.airtribe.LearnerManagementSystem.Validation.constraints.Min;
import org.airtribe.LearnerManagementSystem.Validation.constraints.NotBlank;

public record LearnerCreateRequest(
        @NotBlank(message = "name is required") String name,
        @NotBlank(message = "email is required") @Email(message = "email must be valid") String email,
        @Min(value = 1, message = "age must be at least 1") @Max(value = 120, message = "age must be at most 120") int age
) {
}

