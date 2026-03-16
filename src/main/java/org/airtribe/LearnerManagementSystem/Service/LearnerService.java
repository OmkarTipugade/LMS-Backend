package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
public class LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    public Learner createLearner(Learner learner) {
        return  learnerRepository.save(learner);
    }

    public List<Learner> findAllLearners() {
        return  learnerRepository.findAll();
    }

    public Learner findLearnerById(String id) {
        return learnerRepository.findById(id).get();
    }

    public List<Learner> findLearnerByName(String name) {
        return learnerRepository.findLearnerByName(name);
    }
}
