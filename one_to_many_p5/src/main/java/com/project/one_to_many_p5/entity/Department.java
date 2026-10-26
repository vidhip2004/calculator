package com.project.one_to_many_p5.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Dept")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;
    private  String deptName;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee> employees; //we write in list cause one department can have multiple employees

    public Department() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
