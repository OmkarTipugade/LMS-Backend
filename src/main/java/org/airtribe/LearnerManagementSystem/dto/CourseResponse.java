package org.airtribe.LearnerManagementSystem.dto;

import java.util.List;

public record CourseResponse(
        Long id,
        String name,
        String description,
        List<CohortRef> cohorts
) {
}

