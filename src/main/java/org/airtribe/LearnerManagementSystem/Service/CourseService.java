package org.airtribe.LearnerManagementSystem.Service;

import org.airtribe.LearnerManagementSystem.Entity.Course;
import org.airtribe.LearnerManagementSystem.Exception.CourseNotFoundException;
import org.airtribe.LearnerManagementSystem.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " not found"));
    }
}
