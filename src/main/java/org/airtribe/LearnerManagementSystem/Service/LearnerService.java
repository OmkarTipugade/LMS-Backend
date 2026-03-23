package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Learner findLearnerById(Long id) throws LearnerNotFoundException {
        if(learnerRepository.findById(id).isPresent()) {
            return learnerRepository.findById(id).get();
        }
        throw new LearnerNotFoundException("Learner with id " + id + " not found");
    }

    public List<Learner> findLearnerByName(String name) {
        return learnerRepository.findLearnerByName(name);
    }
}
