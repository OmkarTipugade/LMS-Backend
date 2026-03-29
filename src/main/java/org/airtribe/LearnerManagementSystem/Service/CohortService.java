package org.airtribe.LearnerManagementSystem.Service;

import jakarta.transaction.Transactional;
import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.CohortDTO;
import org.airtribe.LearnerManagementSystem.Entity.Course;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.Exception.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.CourseNotFoundException;
import org.airtribe.LearnerManagementSystem.Exception.LearnerNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.CohortRepository;
import org.airtribe.LearnerManagementSystem.Repository.CourseRepository;
import org.airtribe.LearnerManagementSystem.Repository.LearnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CohortService {

        private final CohortRepository cohortRepository;
        private final LearnerRepository learnerRepository;
        private final CourseRepository courseRepository;

        public CohortService(CohortRepository cohortRepository, LearnerRepository learnerRepository, CourseRepository courseRepository) {
            this.cohortRepository = cohortRepository;
            this.learnerRepository = learnerRepository;
            this.courseRepository = courseRepository;
        }

        public Cohort createCohort(Cohort cohort) {
            return cohortRepository.save(cohort);
        }

    public Cohort createCohort(Cohort cohort, Long courseId) {
            if (courseId != null) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + " not found"));
                cohort.setCourse(course);
            }
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

    public Cohort assignLearnerToCohort(Long cohortId, Long learnerId) {
            Learner learner = learnerRepository.findById(learnerId)
                    .orElseThrow(() -> new LearnerNotFoundException("Learner with id " + learnerId + " not found"));

            Cohort cohort = cohortRepository.findById(cohortId)
                    .orElseThrow(() -> new CohortNotFoundException("Cohort with id " + cohortId + " not found"));

            boolean alreadyAssigned = cohort.getLearners().stream().anyMatch(existing -> existing.getId().equals(learner.getId()));
            if (!alreadyAssigned) {
                cohort.getLearners().add(learner);
            }
            return cohortRepository.save(cohort);

    }

    public List<Cohort> fetchAllCohorts() {
            return cohortRepository.findAll();
    }

    public Cohort fetchCohortById(Long cohortId) {
            return cohortRepository.findById(cohortId)
                    .orElseThrow(() -> new CohortNotFoundException("Cohort with id " + cohortId + " not found"));
    }

    @Transactional
    public Cohort assignAndCreateLearners(Long cohortId, List<Learner> learners) {
            List<Learner> managedLearners = new ArrayList<>();
            Cohort cohort = cohortRepository.findById(cohortId)
                    .orElseThrow(() -> new CohortNotFoundException("Cohort with id " + cohortId + " not found"));

            for (Learner learner : learners) {
                Optional<Learner> learnerOptional = learnerRepository.findByEmail(learner.getEmail());
                if (!learnerOptional.isPresent()) {
                    managedLearners.add(learnerRepository.save(learner));
                } else  {
                    managedLearners.add(learnerOptional.get());
                }
            }
            for (Learner managedLearner : managedLearners) {
                boolean alreadyAssigned = cohort.getLearners().stream().anyMatch(existing -> existing.getId().equals(managedLearner.getId()));
                if (!alreadyAssigned) {
                    cohort.getLearners().add(managedLearner);
                }
            }
            return cohortRepository.save(cohort);
    }
}
