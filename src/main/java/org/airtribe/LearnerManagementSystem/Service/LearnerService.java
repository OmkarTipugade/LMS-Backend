package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.CohortDTO;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Entity.LearnerDTO;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    public static LearnerDTO convertToLearnerDTO(Learner learner) {
        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setId(learner.getId());
        learnerDTO.setName(learner.getName());
        learnerDTO.setEmail(learner.getEmail());
        learnerDTO.setAge(learner.getAge());

        List<CohortDTO> cohortDTOs = new ArrayList<>();
        for (Cohort cohort : learner.getCohorts()) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setName(cohort.getName());
            cohortDTO.setDescription(cohort.getDescription());
            cohortDTOs.add(cohortDTO);
        }
        learnerDTO.setCohorts(cohortDTOs);
        return learnerDTO;
    }

    public static List<LearnerDTO> convertToLearnerDTO(List<Learner> learners) {
        List<LearnerDTO> learnerDTOs = new ArrayList<>();
        for (Learner learner : learners) {
            learnerDTOs.add(convertToLearnerDTO(learner));
        }

        return learnerDTOs;
    }

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
