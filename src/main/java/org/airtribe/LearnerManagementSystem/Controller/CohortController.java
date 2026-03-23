package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Exception.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CohortController {

    @Autowired
    private CohortService cohortService;

    @PostMapping("/cohorts")
    public Cohort createCohort(@RequestBody Cohort cohort) {
        return cohortService.createCohort(cohort);
    }

    @PostMapping("/assignLearnerToCohort")
    public Cohort assignLearnerToCohort(@RequestParam Long cohortId, @RequestParam Long learnerId) throws CohortNotFoundException, LearnerNotFoundException {
        return cohortService.assignLearnerToCohort(cohortId, learnerId);
    }

    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity<String> handleCohortNotFoundException(CohortNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<String> handleLearnerNotFoundException(LearnerNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
