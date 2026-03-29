package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Mapper.ApiMapper;
import org.airtribe.LearnerManagementSystem.Service.LearnerService;
import org.airtribe.LearnerManagementSystem.dto.LearnerCreateRequest;
import org.airtribe.LearnerManagementSystem.dto.LearnerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/learners", "/learners"})
public class LearnerController {

    private final LearnerService learnerService;
    private final ApiMapper apiMapper;

    public LearnerController(LearnerService learnerService, ApiMapper apiMapper) {
        this.learnerService = learnerService;
        this.apiMapper = apiMapper;
    }

    @PostMapping
    public ResponseEntity<LearnerResponse> createLearner(@Valid @RequestBody LearnerCreateRequest request) {
        Learner created = learnerService.createLearner(apiMapper.toLearner(request));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(apiMapper.toLearnerResponse(created));
    }

    @GetMapping("/{id}")
    public LearnerResponse getLearner(@PathVariable Long id) {
        return apiMapper.toLearnerResponse(learnerService.findLearnerById(id));
    }

    @GetMapping
    public List<LearnerResponse> getLearners(@RequestParam(value = "name", required = false) String name) {
        List<Learner> learners;
        if (name == null) {
            learners = learnerService.findAllLearners();
        } else {
            learners = learnerService.findLearnerByName(name);
        }
        return apiMapper.toLearnerResponses(learners);
    }
}
