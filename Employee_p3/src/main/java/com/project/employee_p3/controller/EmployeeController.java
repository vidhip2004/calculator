package com.project.employee_p3.controller;

import com.project.employee_p3.entity.Employee;
import com.project.employee_p3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public List<Employee> addEmployee(@RequestBody List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    // find by department
    @GetMapping("/dept/{department}")
    public List<Employee> getEmployeeDepartment(@PathVariable String department) {
        return employeeRepository.findByDepartment(department);
    }

    // count by department
    @GetMapping("/count/{department}")
    public long countEmployee(@PathVariable String department) {
        return employeeRepository.countByDepartment(department);
    }

    // salary above
    @GetMapping("/salaryabove")
    public List<Employee> getEmployeesBySalar(@RequestParam double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    // salary range
    @GetMapping("/salaryrange")
    public List<Employee> getEmployeesBySalaryRange(
            @RequestParam double min,
            @RequestParam double max) {

        return employeeRepository.findBySalaryRange(min, max);
    }

    @PutMapping("/{id}")
    public Employee updateSalary(@PathVariable Integer id,
                                 @RequestBody Employee employee) {

        Employee emp = employeeRepository.findById(id).orElseThrow();

        emp.setSalary(employee.getSalary());

        return employeeRepository.save(emp);
    }

    @DeleteMapping("/{id}")
    public String deleteemploye(@PathVariable Integer id) {

        employeeRepository.deleteById(id);

        return "Employee by id delete success";
    }
}