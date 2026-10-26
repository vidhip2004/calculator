package com.example.demo.controller;

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

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer)
	{
		Customer savCustomer=customerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savCustomer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> showCustomer(@PathVariable Integer id)
	{
		Customer customer=customerRepository.findById(id).orElse(null);
		if(customer==null)
		{
			return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.ok(customer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id,@Valid @RequestBody Customer customer)
	{
		Customer existcustomer=customerRepository.findById(id).orElse(null);
		if(existcustomer==null)
		{
			return ResponseEntity.notFound().build();
		}
		existcustomer.setName(customer.getName());
		existcustomer.setEmail(customer.getEmail());
		existcustomer.setAge(customer.getAge());
		
		Customer savecust=customerRepository.save(existcustomer);
		return ResponseEntity.ok(savecust);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		Customer existcustomer=customerRepository.findById(id).orElse(null);
		if(existcustomer==null)
		{
			return ResponseEntity.notFound().build();
		}
		customerRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
