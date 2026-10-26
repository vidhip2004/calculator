package com.project.one_to_one.repository;

import com.project.one_to_one.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital,Integer> {
}
