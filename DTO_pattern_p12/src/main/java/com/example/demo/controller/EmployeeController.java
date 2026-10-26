package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepositroy;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepositroy employeeRepositroy;
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO dto)
	{
		// DTO → Entity
		Employee emp=EmployeeMapper.toEntity(dto);
		
		// Save Entity into database
		Employee savedEmployee = employeeRepositroy.save(emp);
		
		// Entity → DTO
		EmployeeDTO responseDto = EmployeeMapper.toDto(savedEmployee);

	    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> showEmp()
	{
		List<Employee> employees=employeeRepositroy.findAll();
		
		// Employee List → EmployeeDTO List
		//stream - proccess emp to empdto
		//map - take each item and convert it into something else.
		//employeemapper is class and toDto is method called method referencess
		//toList - convert to list
		List<EmployeeDTO> dtoList=employees.stream().map(EmployeeMapper::toDto).toList();
		
		return ResponseEntity.ok(dtoList);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmp(@RequestBody EmployeeDTO employeeDTO,@PathVariable Integer id)
	{
		Employee existemployees= employeeRepositroy.findById(id).orElse(null);
		
		if(existemployees==null)
		{
			return ResponseEntity.notFound().build();
		}
		existemployees.setEmpName(employeeDTO.getEmpName());
		existemployees.setDepartment(employeeDTO.getDepartment());

	    Employee updatedEmployee = employeeRepositroy.save(existemployees);

	    EmployeeDTO responseDTO = EmployeeMapper.toDto(updatedEmployee);

	    return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmp(@PathVariable Integer id)
	{
		Employee existemployees= employeeRepositroy.findById(id).orElse(null);
		if(existemployees==null)
		{
			return ResponseEntity.notFound().build();
		}
		employeeRepositroy.delete(existemployees);
		return ResponseEntity.noContent().build();
	}

}
