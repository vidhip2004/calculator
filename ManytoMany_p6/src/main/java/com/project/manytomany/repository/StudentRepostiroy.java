package com.project.manytomany.repository;

import com.project.manytomany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepostiroy extends JpaRepository<Student,Integer> {
}
