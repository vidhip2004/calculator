package com.project.manytomany.controller;

import com.project.manytomany.entity.Course;
import com.project.manytomany.entity.Student;
import com.project.manytomany.repository.CourseRepostiroy;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    private CourseRepostiroy courseRepostiroy;

    @PostMapping
    public ResponseEntity<Course> addcourse(@RequestBody Course course)
    {
            Course savecourse=courseRepostiroy.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savecourse);
    }
    @GetMapping("/{courseId}/student")
    public Set<Student> showcourse(@PathVariable Integer courseId)
    {
        Course course=courseRepostiroy.findById(courseId).orElseThrow();
        return course.getStudents();
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(@RequestBody Course course,@PathVariable Integer courseId)
    {
        Course course1 =courseRepostiroy.findById(courseId).orElse(null);
        course1.setCourseName(course.getCourseName());
        return  courseRepostiroy.save(course1);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deletecourse(@PathVariable Integer courseId)
    {
        courseRepostiroy.deleteById(courseId);
        return new ResponseEntity<>("Content deleted",HttpStatus.OK);
    }

}
