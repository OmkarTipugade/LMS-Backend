package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.CohortDTO;
import org.airtribe.LearnerManagementSystem.Exception.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Service.CohortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CohortController {

    @Autowired
    private CohortService cohortService;

    @PostMapping("/cohorts")
    public CohortDTO createCohort(@RequestBody Cohort cohort) {
        return CohortService.convertToCohortDTO(cohortService.createCohort(cohort));
    }

    @PostMapping("/assignLearnerToCohort")
    public CohortDTO assignLearnerToCohort(@RequestParam Long cohortId, @RequestParam Long learnerId) throws CohortNotFoundException, LearnerNotFoundException {
        return CohortService.convertToCohortDTO(cohortService.assignLearnerToCohort(cohortId, learnerId));
    }

    @GetMapping("/cohorts")
    public List<CohortDTO> getCohorts() {
        return CohortService.convertToCohortDTO(cohortService.fetchAllCohorts());
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
