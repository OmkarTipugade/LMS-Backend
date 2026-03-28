package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Entity.LearnerDTO;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @PostMapping("/learners")
    public LearnerDTO learner(@RequestBody Learner learner) {
        return LearnerService.convertToLearnerDTO(learnerService.createLearner(learner));
    }

//    @GetMapping("/learners")
//    public List<Learner> learners() {
//        return learnerService.findAllLearners();
//    }

    @GetMapping("learners/{id}")
    public LearnerDTO learner(@PathVariable Long id) throws LearnerNotFoundException {
        return LearnerService.convertToLearnerDTO(learnerService.findLearnerById(id));
    }

    @GetMapping("/learners")
    public List<LearnerDTO> learners(@RequestParam(value = "name", required = false) String name) {
        List<Learner> learners;
        if (name == null) {
            learners = learnerService.findAllLearners();
        } else {
            learners = learnerService.findLearnerByName(name);
        }
        return LearnerService.convertToLearnerDTO(learners);
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<String> handleLearnerNotFoundException(LearnerNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
