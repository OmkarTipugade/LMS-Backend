package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @PostMapping("/learners")
    public Learner learner(@RequestBody Learner learner) {
        return learnerService.createLearner(learner);
    }

//    @GetMapping("/learners")
//    public List<Learner> learners() {
//        return learnerService.findAllLearners();
//    }

    @GetMapping("learners/{id}")
    public Learner learner(@PathVariable String id) {
        return  learnerService.findLearnerById(id);
    }

    @GetMapping("/learners")
    public List<Learner> learners(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return learnerService.findAllLearners();
        }
        return learnerService.findLearnerByName(name);
    }

}
