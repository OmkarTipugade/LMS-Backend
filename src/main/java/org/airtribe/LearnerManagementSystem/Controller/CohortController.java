package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Mapper.ApiMapper;
import org.airtribe.LearnerManagementSystem.Service.CohortService;
import org.airtribe.LearnerManagementSystem.dto.CohortCreateRequest;
import org.airtribe.LearnerManagementSystem.dto.CohortResponse;
import org.airtribe.LearnerManagementSystem.dto.LearnerCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/cohorts", "/cohorts"})
public class CohortController {

    private final CohortService cohortService;
    private final ApiMapper apiMapper;

    public CohortController(CohortService cohortService, ApiMapper apiMapper) {
        this.cohortService = cohortService;
        this.apiMapper = apiMapper;
    }

    @PostMapping
    public ResponseEntity<CohortResponse> createCohort(@Valid @RequestBody CohortCreateRequest request) {
        Cohort created = cohortService.createCohort(apiMapper.toCohort(request), request.courseId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(apiMapper.toCohortResponse(created));
    }

    @GetMapping("/{id}")
    public CohortResponse getCohort(@PathVariable Long id) {
        return apiMapper.toCohortResponse(cohortService.fetchCohortById(id));
    }

    @GetMapping
    public List<CohortResponse> getCohorts() {
        return apiMapper.toCohortResponses(cohortService.fetchAllCohorts());
    }

    @PostMapping("/{cohortId}/learners/{learnerId}")
    public CohortResponse assignLearnerToCohort(@PathVariable Long cohortId, @PathVariable Long learnerId) {
        return apiMapper.toCohortResponse(cohortService.assignLearnerToCohort(cohortId, learnerId));
    }

    @PostMapping("/{cohortId}/learners")
    public CohortResponse assignAndCreateLearners(@PathVariable Long cohortId, @RequestBody List<LearnerCreateRequest> learners) {
        return apiMapper.toCohortResponse(cohortService.assignAndCreateLearners(cohortId, apiMapper.toLearners(learners)));
    }

    @Deprecated
    @PostMapping("/assignLearnerToCohort")
    public CohortResponse assignLearnerToCohortLegacy(@RequestParam Long cohortId, @RequestParam Long learnerId) {
        return apiMapper.toCohortResponse(cohortService.assignLearnerToCohort(cohortId, learnerId));
    }
}
