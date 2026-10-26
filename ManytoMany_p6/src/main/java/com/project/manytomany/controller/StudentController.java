package com.project.manytomany.controller;


import com.project.manytomany.entity.Course;
import com.project.manytomany.entity.Student;
import com.project.manytomany.repository.CourseRepostiroy;
import com.project.manytomany.repository.StudentRepostiroy;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepostiroy studentRepostiroy;
    @Autowired
    private CourseRepostiroy courseRepostiroy;
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        Student savestudent=studentRepostiroy.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savestudent);
    }
    @PostMapping("/{studentId}/enroll/{courseId}")
    public Student addbyidStudent(@PathVariable Integer studentId,
                                            @PathVariable Integer courseId)
    {
        //get is always return orElseThrow exception not optional
       Student student= studentRepostiroy.findById(studentId).orElseThrow();
       Course course= courseRepostiroy.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        return  studentRepostiroy.save(student);
    }

    @GetMapping("/{studentId}")
    public Optional<Student> showStudent(@PathVariable Integer studentId)
    {
            return studentRepostiroy.findById(studentId);
    }

    @DeleteMapping("/{studentId}/unenroll/{courseId}")
    public Student deleteStud(@PathVariable Integer studentId,@PathVariable Integer courseId)
    {
        Student student=studentRepostiroy.findById(studentId).orElseThrow();
        Course course=courseRepostiroy.findById(courseId).orElseThrow();

        student.getCourses().remove(course);
        return studentRepostiroy.save(student);
    }

}
