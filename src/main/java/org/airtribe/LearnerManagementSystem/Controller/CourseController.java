package org.airtribe.LearnerManagementSystem.Controller;

import org.airtribe.LearnerManagementSystem.Entity.Course;
import org.airtribe.LearnerManagementSystem.Mapper.ApiMapper;
import org.airtribe.LearnerManagementSystem.Service.CourseService;
import org.airtribe.LearnerManagementSystem.dto.CourseCreateRequest;
import org.airtribe.LearnerManagementSystem.dto.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/courses", "/courses"})
public class CourseController {

    private final CourseService courseService;
    private final ApiMapper apiMapper;

    public CourseController(CourseService courseService, ApiMapper apiMapper) {
        this.courseService = courseService;
        this.apiMapper = apiMapper;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreateRequest request) {
        Course created = courseService.createCourse(apiMapper.toCourse(request));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(apiMapper.toCourseResponse(created));
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return apiMapper.toCourseResponses(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return apiMapper.toCourseResponse(courseService.getCourseById(id));
    }


}
