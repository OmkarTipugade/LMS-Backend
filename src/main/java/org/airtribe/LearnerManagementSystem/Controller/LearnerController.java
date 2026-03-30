package org.airtribe.LearnerManagementSystem.Controller;

import jakarta.validation.Valid;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Mapper.ApiMapper;
import org.airtribe.LearnerManagementSystem.Service.LearnerService;
import org.airtribe.LearnerManagementSystem.dto.LearnerResponse;
import org.springframework.web.bind.annotation.*;

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
    public Learner createLearner(@Valid @RequestBody Learner learner) {
        return learnerService.createLearner(learner);
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
