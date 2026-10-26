package com.project.one_to_one.controller;

import com.project.one_to_one.entity.Employee;
import com.project.one_to_one.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/emp")
public class EmployeeCOntroller {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody  Employee employee)
    {
        Employee SavedEmployee= employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(SavedEmployee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> showEmployee(@PathVariable Integer id)
    {
        return employeeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable Integer id)
    {
        employee.setEmpId(id);
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id)
    {
        employeeRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
