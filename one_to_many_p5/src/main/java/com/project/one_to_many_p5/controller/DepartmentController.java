package com.project.one_to_many_p5.controller;

import com.project.one_to_many_p5.entity.Department;
import com.project.one_to_many_p5.entity.Employee;
import com.project.one_to_many_p5.repository.DepartmentRepository;
import com.project.one_to_many_p5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping
    public ResponseEntity<Department> createDept(@RequestBody Department department)
    {
        for(Employee employee:department.getEmployees()){
            employee.setDepartment(department);
        }
        Department saveDept= departmentRepository.save(department);
        return  ResponseEntity.status(HttpStatus.CREATED).body(saveDept);
    }

    //get by id
    @GetMapping("/{id}")
    public Optional<Department> showbyid(@PathVariable Integer id)
    {
        return departmentRepository.findById(id);
    }
    //get by department
    @GetMapping("/{id}/employees")
    public List<Employee> showByDepartment(@PathVariable Integer id)
    {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null) {
            return new ArrayList<>();
        }

        return department.getEmployees();
    }

    @PutMapping("/{deptId}/employees/{empId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                                   @PathVariable Integer deptId,
                                   @PathVariable Integer empId)
    {
        Department department = departmentRepository.findById(deptId).orElse(null);//Find Department 1 from the database.

        employee.setEmpId(empId);//Without this line, Hibernate thinks it is a new employee and inserts another row
        employee.setDepartment(department);//Employee 2 belongs to Department 1.

        return employeeRepository.save(employee);
        //Save the updated employee.
        //If Employee 2 already exists, Hibernate performs an UPDATE.
    }

    @DeleteMapping("/{deptId}/employees/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer deptId,@PathVariable Integer empId)
    {
        employeeRepository.deleteById(empId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Void> deleteEmplId(@PathVariable Integer deptId)
    {
        departmentRepository.deleteById(deptId);
        return ResponseEntity.noContent().build();
    }
}
