package org.airtribe.LearnerManagementSystem.dto;

import java.util.List;

public record LearnerResponse(
        Long id,
        String name,
        String email,
        int age,
        List<CohortRef> cohorts
) {
}

