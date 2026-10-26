package com.example.demo.mapper;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

public class EmployeeMapper {
	// Entity -> DTO
	public static EmployeeDTO toDto(Employee e)
	{
		EmployeeDTO dto=new EmployeeDTO();
		dto.setEmpId(e.getEmpId());
		dto.setEmpName(e.getEmpName());
		dto.setDepartment(e.getDepartment());
		
		return dto;
	}
	
	// DTO -> Entity
	public static Employee toEntity(EmployeeDTO d)
	{
		Employee emp=new Employee();
		emp.setEmpId(d.getEmpId());
		emp.setEmpName(d.getEmpName());
		emp.setDepartment(d.getDepartment());
		
		return emp;
	}

}
