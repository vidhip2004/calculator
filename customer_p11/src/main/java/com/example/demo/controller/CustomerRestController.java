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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody CustomerDTO customerDTO)
	{
		Customer customer=new Customer();
		customer.setName(customerDTO.getName());
		customer.setAge(customerDTO.getAge());
		customer.setCity(customerDTO.getCity());
		
		Customer savedCustomer=customerRepository.save(customer);
		
		return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(savedCustomer);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> show(@PathVariable Integer id)
	{
		Customer savedCustomer=customerRepository.findById(id).orElse(null);
		if(savedCustomer!=null)
		{
			return ResponseEntity.ok(savedCustomer);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Customer>> search(
			@RequestParam String city,
			@RequestParam(required=false,defaultValue = "0")int minAge)
	{
		System.out.println("city and minAge received using @RequestParam");
		List<Customer> customers=customerRepository.findByCityAndAgeGreaterThanEqual(city,minAge);
		return ResponseEntity.ok(customers);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@RequestBody CustomerDTO customerDTO,@PathVariable Integer id)
	{
		Customer existingcustomer=customerRepository.findById(id).orElse(null);
		
		if(existingcustomer!=null)
		{
			existingcustomer.setName(customerDTO.getName());
			existingcustomer.setCity(customerDTO.getCity());
			existingcustomer.setAge(customerDTO.getAge());
			
			Customer updatedCustomer =
	                customerRepository.save(existingcustomer);
		
			return ResponseEntity.ok(updatedCustomer);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		Customer existingcustomer=customerRepository.findById(id).orElse(null);
		if(existingcustomer!=null)
		{
			customerRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
