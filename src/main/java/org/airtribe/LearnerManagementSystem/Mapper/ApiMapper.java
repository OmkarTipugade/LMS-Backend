package org.airtribe.LearnerManagementSystem.Mapper;

import org.airtribe.LearnerManagementSystem.Entity.Cohort;
import org.airtribe.LearnerManagementSystem.Entity.Course;
import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.airtribe.LearnerManagementSystem.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiMapper {

    public Learner toLearner(LearnerCreateRequest request) {
        Learner learner = new Learner();
        learner.setName(request.name());
        learner.setEmail(request.email());
        learner.setAge(request.age());
        return learner;
    }

    public List<Learner> toLearners(List<LearnerCreateRequest> requests) {
        return requests.stream().map(this::toLearner).toList();
    }

    public Cohort toCohort(CohortCreateRequest request) {
        Cohort cohort = new Cohort();
        cohort.setName(request.name());
        cohort.setDescription(request.description());
        return cohort;
    }

    public Course toCourse(CourseCreateRequest request) {
        Course course = new Course();
        course.setName(request.name());
        course.setDescription(request.description());
        return course;
    }

    public LearnerResponse toLearnerResponse(Learner learner) {
        List<CohortRef> cohorts = new ArrayList<>();
        for (Cohort cohort : learner.getCohorts()) {
            cohorts.add(new CohortRef(cohort.getId(), cohort.getName(), cohort.getDescription()));
        }
        return new LearnerResponse(learner.getId(), learner.getName(), learner.getEmail(), learner.getAge(), cohorts);
    }

    public CohortResponse toCohortResponse(Cohort cohort) {
        List<LearnerRef> learners = new ArrayList<>();
        for (Learner learner : cohort.getLearners()) {
            learners.add(new LearnerRef(learner.getId(), learner.getName(), learner.getEmail()));
        }

        CourseRef courseRef = null;
        if (cohort.getCourse() != null) {
            courseRef = new CourseRef(cohort.getCourse().getId(), cohort.getCourse().getName(), cohort.getCourse().getDescription());
        }

        return new CohortResponse(cohort.getId(), cohort.getName(), cohort.getDescription(), courseRef, learners);
    }

    public CourseResponse toCourseResponse(Course course) {
        List<CohortRef> cohorts = new ArrayList<>();
        for (Cohort cohort : course.getCohorts()) {
            cohorts.add(new CohortRef(cohort.getId(), cohort.getName(), cohort.getDescription()));
        }
        return new CourseResponse(course.getId(), course.getName(), course.getDescription(), cohorts);
    }

    public List<LearnerResponse> toLearnerResponses(List<Learner> learners) {
        return learners.stream().map(this::toLearnerResponse).toList();
    }

    public List<CohortResponse> toCohortResponses(List<Cohort> cohorts) {
        return cohorts.stream().map(this::toCohortResponse).toList();
    }

    public List<CourseResponse> toCourseResponses(List<Course> courses) {
        return courses.stream().map(this::toCourseResponse).toList();
    }
}


