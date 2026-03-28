package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.CohortDTO;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Exception.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.CohortRepository;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CohortService {

        @Autowired
        private CohortRepository cohortRepository;
        @Autowired
        private LearnerRepository learnerRepository;

        public Cohort createCohort(Cohort cohort) {
            return cohortRepository.save(cohort);
        }

    public static CohortDTO convertToCohortDTO(Cohort cohort) {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setId(cohort.getId());
            cohortDTO.setName(cohort.getName());
            cohortDTO.setDescription(cohort.getDescription());
            return cohortDTO;
    }

    public static List<CohortDTO> convertToCohortDTO(List<Cohort> cohorts) {
            return cohorts.stream().map(CohortService::convertToCohortDTO).collect(Collectors.toList());
    }

    public Cohort assignLearnerToCohort(Long cohortId, Long learnerId) throws LearnerNotFoundException, CohortNotFoundException {
            Optional<Learner> LearnerOptional = learnerRepository.findById(learnerId);
            if (!LearnerOptional.isPresent()) {
                throw new RuntimeException("Learner with id " + learnerId + " not found");
            }

            Optional<Cohort> CohortOptional = cohortRepository.findById(cohortId);
            if (!CohortOptional.isPresent()) {
                throw new CohortNotFoundException("Cohort with id " + cohortId + " not found");
            }

            Cohort cohort = CohortOptional.get();
            Learner learner = LearnerOptional.get();

            cohort.getLearners().add(learner);
            return cohortRepository.save(cohort);

    }

    public List<Cohort> fetchAllCohorts() {
            return cohortRepository.findAll();
    }
}
