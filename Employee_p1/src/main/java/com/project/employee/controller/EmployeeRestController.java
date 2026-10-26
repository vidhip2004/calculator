package com.project.employee.controller;
import com.project.employee.entity.Employee;
import com.project.employee.repository.EmployeeRepository;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/emp")
    public Employee addEmployee(@RequestBody Employee employee) { //Employee is a data type means your entitiy class employee is variable name
        return employeeRepository.save(employee);
    }

    @GetMapping("/emp")
    public Iterable<Employee> showEmployee()
    {
        return employeeRepository.findAll();
    }

    @GetMapping("/emp/{id}")
    public Optional<Employee> showEmployee(@PathVariable Integer id){
        return employeeRepository.findById(id);
    }

    @PutMapping("/emp/{id}")
    public Employee updateEmployee(@PathVariable Integer id,@RequestBody Employee employee){
        employee.setEmpid(id);
        return employeeRepository.save(employee);
    }
    @DeleteMapping("emp/{id}")
    public String deleteEmployee(@PathVariable Integer id){
         employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }


}
