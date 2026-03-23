package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Exception.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.CohortRepository;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CohortService {

        @Autowired
        private CohortRepository cohortRepository;
        @Autowired
        private LearnerRepository learnerRepository;

        public Cohort createCohort(Cohort cohort) {
            return cohortRepository.save(cohort);
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
}
