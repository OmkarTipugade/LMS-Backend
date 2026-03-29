package org.airtribe.LearnerManagementSystem.dto;

import java.util.List;

public record CohortResponse(
        Long id,
        String name,
        String description,
        CourseRef course,
        List<LearnerRef> learners
) {
}

