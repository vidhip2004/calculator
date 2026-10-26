package com.project.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer studentId;
    private String studentName;

    @ManyToMany
    @JoinTable(name="student_course",
    joinColumns = @JoinColumn(name="student_Id"),
    inverseJoinColumns = @JoinColumn(name="course_Id"))
    // Initialize an empty Set to store Course objects.
    // HashSet is used because Set is an interface and cannot create objects directly.
   // Set<Course> → Can store multiple Course objects.
    //new HashSet<>() → Creates an empty collection so we can use add() later.
    //Without new HashSet<>(), courses would be null.
    @JsonProperty("course")
    private Set<Course> courses =new HashSet<>();
    public Student() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
